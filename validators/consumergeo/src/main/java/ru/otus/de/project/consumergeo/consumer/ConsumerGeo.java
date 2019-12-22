package ru.otus.de.project.consumergeo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.de.project.consumergeo.model.Geo;
import ru.otus.de.project.consumergeo.validation.Validator;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class ConsumerGeo {

    @Autowired
    public ConsumerGeo(Validator validator) {
        this.validator = validator;
    }

    @Value("${kafka.brokers}")
    private String kafkaBrokers;
    @Value("${kafka.client.id}")
    private String kafkaClientId;
    @Value("${kafka.group.id.config}")
    private String kafkaGroupIdConfig;
    @Value("${kafka.topic}")
    private String kafkaTopic;
    @Value("${kafka.partition}")
    private int kafkaPartition;

    @Value("${kafka.producer.valid.topic}")
    private String producerValidTopic;
    @Value("${kafka.producer.invalid.topic}")
    private String producerInvalidTopic;

    private Validator validator;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static KafkaConsumer<String, String> consumer;
    private static KafkaProducer<String, String> producer;

    private static Thread threadProducerConsumer;

    private KafkaConsumer<String, String> getConsumer() {
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, kafkaClientId);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupIdConfig);
        KafkaConsumer<String, String> result = new KafkaConsumer(consumerProperties);
        TopicPartition topicPartition = new TopicPartition(kafkaTopic, kafkaPartition);
        result.assign(Arrays.asList(topicPartition));
        return result;
    }

    private KafkaProducer<String, String> getProducer() {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> result = new KafkaProducer(producerProperties);
        return result;
    }

    public String getMessage(boolean isGetMessage) {

        if (consumer == null) {
            consumer = getConsumer();
        }
        if (producer == null) {
            producer = getProducer();
        }

        if(!isGetMessage && threadProducerConsumer != null) {
            threadProducerConsumer.interrupt();

            producer.close();
            producer = null;

            consumer.close();
            consumer = null;

            threadProducerConsumer = null;
            return "validator_geo is stoped!";
        }

        if(!isGetMessage && threadProducerConsumer == null) {
            return "validator_geo was stoped!";
        }

        if (isGetMessage && threadProducerConsumer == null) {
            threadProducerConsumer = new Thread(() -> {
                while(!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(2));
                    consumerRecords.forEach(consumerRecord -> {
                        try {
                            String kafkaValue = consumerRecord.value();
                            Geo geo = objectMapper.readValue(kafkaValue, Geo.class);
                            if (validator.isGeoValid(geo)) {
                                System.out.println(producerValidTopic);
                                ProducerRecord<String, String> producerRecord =
                                        new ProducerRecord<>(
                                                producerValidTopic, kafkaPartition, kafkaGroupIdConfig, kafkaValue
                                        );
                                producer.send(producerRecord);
                            } else {
                                System.out.println(producerInvalidTopic);
                                ProducerRecord<String, String> producerRecord =
                                        new ProducerRecord<>(
                                                producerInvalidTopic, kafkaPartition, kafkaGroupIdConfig, kafkaValue
                                        );
                                producer.send(producerRecord);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
            threadProducerConsumer.start();
            return "validator_geo is start!";
        }

        return "validator_geo was started!";
    }
}
