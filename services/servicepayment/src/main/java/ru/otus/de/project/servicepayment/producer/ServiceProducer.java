package ru.otus.de.project.servicepayment.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.de.project.servicepayment.data.DataGenerator;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ServiceProducer {

    @Autowired
    public ServiceProducer(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }
    @Value("${kafka.brokers}")
    private String kafkaBrokers;
//    @Value("${kafka.client.id}")
//    private String kafkaClientId;
    @Value("${kafka.group.id.config}")
    private String kafkaGroupIdConfig;
    @Value("${kafka.topic}")
    private String kafkaTopic;
    @Value("${kafka.partition}")
    private int kafkaPartition;

    private DataGenerator dataGenerator;
    private static KafkaProducer<String, String> producer;

    private KafkaProducer<String, String> getProducer() {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> result = new KafkaProducer(producerProperties);
        return result;
    }

    public void sendData(boolean isSendData) {

        if(producer == null) {
            producer = getProducer();
        }
        while(isSendData) {

            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(kafkaTopic, kafkaPartition, kafkaGroupIdConfig, dataGenerator.getData());
            producer.send(producerRecord);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 900));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.close();
        producer = null;
    }
}
