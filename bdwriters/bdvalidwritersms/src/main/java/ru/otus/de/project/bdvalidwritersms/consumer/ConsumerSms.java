package ru.otus.de.project.bdvalidwritersms.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.de.project.bdvalidwritersms.model.Sms;
import ru.otus.de.project.bdvalidwritersms.entity.SmsValid;
import ru.otus.de.project.bdvalidwritersms.repository.SmsValidRepository;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Properties;

@Service
public class ConsumerSms {

    @Autowired
    public ConsumerSms(SmsValidRepository smsValidRepository) {
        this.smsValidRepository = smsValidRepository;
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

    private SmsValidRepository smsValidRepository;

    private ObjectMapper objectMapper = new ObjectMapper();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private static KafkaConsumer<String, String> consumer;
    private static Thread threadConsumer;

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

    public String getMessage(boolean isGetMessage) {

        if (consumer == null) {
            consumer = getConsumer();
        }

        if(!isGetMessage && threadConsumer != null) {
            threadConsumer.interrupt();
            threadConsumer = null;
            return "valid_bd_writer_sms is stoped!";
        }

        if(!isGetMessage && threadConsumer == null) {
            return "valid_bd_writer_sms was stoped!";
        }

        if (isGetMessage && threadConsumer == null) {
            threadConsumer = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(2));
                    consumerRecords.forEach(consumerRecord -> {
                        try {
                            System.out.println(consumerRecord.value());
                            Sms sms = objectMapper.readValue(consumerRecord.value(), Sms.class);
                            SmsValid smsValid = new SmsValid();
                            smsValid.setNumber(Long.decode(sms.getNumber()));
                            smsValid.setCity(sms.getCity());
                            smsValid.setDateTime(LocalDateTime.parse(sms.getDatetime(), formatter));
                            smsValid.setSms(sms.getSms());
                            smsValidRepository.save(smsValid);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                consumer.close();
                consumer = null;
            });
            threadConsumer.start();
            return "valid_bd_writer_sms is start!";
        }

        return "valid_bd_writer_sms was started!";
    }
}