package ru.otus.de.project.counterservices.counter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CounterTopic {

    @Autowired
    public CounterTopic(MeterRegistry registry) {
        this.registry = registry;
        this.counterTopicMap = new ConcurrentHashMap<>();
    }

    private MeterRegistry registry;
    private Map<String, Counter> counterTopicMap;

    public void countTopic(ConsumerRecord<String, String> record) {
        System.out.println(record.topic());
        counterTopicMap.computeIfPresent(record.topic(), (topic, counter) -> {
            counter.increment();
            return counter;
        });
        counterTopicMap.computeIfAbsent(record.topic(), (count) -> {
            Counter counter = Counter.builder(record.topic()).register(registry);
            counter.increment();
            return counter;
        });
    }
}