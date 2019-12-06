package ru.otus.de.project.bdinvalidwritersms.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.de.project.bdinvalidwritersms.entity.SmsInvalid;
import ru.otus.de.project.bdinvalidwritersms.model.Sms;
import ru.otus.de.project.bdinvalidwritersms.repository.SmsInvalidRepository;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class ConsumerSms {

    @Autowired
    public ConsumerSms(SmsInvalidRepository smsInvalidRepository) {
        this.smsInvalidRepository = smsInvalidRepository;
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

    private SmsInvalidRepository smsInvalidRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static KafkaConsumer<String, String> consumer;

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

    public void getMessage(boolean isGetMessage) {

        if (consumer == null) {
            consumer = getConsumer();
        }

        while(isGetMessage) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(2));
            consumerRecords.forEach(consumerRecord -> {
                System.out.println("KEY: " + consumerRecord.key());
                try {
                    Sms sms = objectMapper.readValue(consumerRecord.value(), Sms.class);
                    SmsInvalid smsValid = new SmsInvalid();
                    smsValid.setNumber(sms.getNumber());
                    smsValid.setCity(sms.getCity());
                    smsValid.setDateTime(sms.getDatetime());
                    smsValid.setSms(sms.getSms());
                    smsInvalidRepository.save(smsValid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        consumer.close();
        consumer = null;
    }
}