package ru.otus.de.project.bdvalidwritergeo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.de.project.bdvalidwritergeo.Entity.GeoValid;
import ru.otus.de.project.bdvalidwritergeo.model.Geo;
import ru.otus.de.project.bdvalidwritergeo.repository.GeoValidRepository;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Properties;

@Service
public class ConsumerGeo {

    @Autowired
    public ConsumerGeo(GeoValidRepository geoValidRepository) {
        this.geoValidRepository = geoValidRepository;
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

    private GeoValidRepository geoValidRepository;

    private ObjectMapper objectMapper = new ObjectMapper();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

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
                    Geo geo = objectMapper.readValue(consumerRecord.value(), Geo.class);
                    GeoValid geoValid = new GeoValid();
                    geoValid.setNumber(Long.decode(geo.getNumber()));
                    geoValid.setCity(geo.getCity());
                    geoValid.setDateTime(LocalDateTime.parse(geo.getDatetime(), formatter));
                    geoValid.setLat(Double.parseDouble(geo.getLat()));
                    geoValid.setLng(Double.parseDouble(geo.getLng()));
                    geoValidRepository.save(geoValid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        consumer.close();
        consumer = null;
    }
}
