package ru.otus.de.project.bdinvalidwriterpayment.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.de.project.bdinvalidwriterpayment.entity.PaymentInvalid;
import ru.otus.de.project.bdinvalidwriterpayment.model.Payment;
import ru.otus.de.project.bdinvalidwriterpayment.repository.PaymentInvalidRepository;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class ConsumerPayment {

    @Autowired
    public ConsumerPayment(PaymentInvalidRepository paymentInvalidRepository) {
        this.paymentInvalidRepository = paymentInvalidRepository;
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

    private PaymentInvalidRepository paymentInvalidRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

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
            return "invalid_bd_writer_payment is stoped!";
        }

        if(!isGetMessage && threadConsumer == null) {
            return "invalid_bd_writer_payment was stoped!";
        }

        if (isGetMessage && threadConsumer == null) {
            threadConsumer = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(2));
                    consumerRecords.forEach(consumerRecord -> {
                        try {
                            System.out.println(consumerRecord.value());
                            Payment payment = objectMapper.readValue(consumerRecord.value(), Payment.class);
                            PaymentInvalid paymentValid = new PaymentInvalid();
                            paymentValid.setNumber(payment.getNumber());
                            paymentValid.setCity(payment.getCity());
                            paymentValid.setDateTime(payment.getDatetime());
                            paymentValid.setPayment(payment.getPayment());
                            paymentInvalidRepository.save(paymentValid);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                consumer.close();
                consumer = null;
            });
            threadConsumer.start();
            return "invalid_bd_writer_payment is start!";
        }

        return "invalid_bd_writer_payment was started!";
    }
}