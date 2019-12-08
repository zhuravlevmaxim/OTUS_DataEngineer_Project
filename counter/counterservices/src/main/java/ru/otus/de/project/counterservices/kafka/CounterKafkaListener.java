package ru.otus.de.project.counterservices.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.otus.de.project.counterservices.counter.CounterTopic;

@Service
public class CounterKafkaListener {

    private CounterTopic counterTopic;

    @Autowired
    public CounterKafkaListener(CounterTopic counterTopic) {
        this.counterTopic = counterTopic;
        System.out.println("CounterKafkaListener");
    }

    @KafkaListener(autoStartup = "${auto.startup}", id = "counter", topics = "#{'${kafka.topic}'.split(',')}")
    public void countMessage(ConsumerRecord<String, String> record) {
        counterTopic.countTopic(record);
    }

}
