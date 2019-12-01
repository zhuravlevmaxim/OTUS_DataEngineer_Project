package ru.otus.de.project.servicesms.data;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataGenerator {

    @Value("${cities}")
    private String[] cities;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private StringBuilder generateNumber() {
        StringBuilder result = new StringBuilder("number:");
        return result.append(random.nextLong(99_999_999l, 999_999_99_99l));
    }

    private StringBuilder generateCity() {
        StringBuilder result = new StringBuilder("city:");
        return result.append(cities[random.nextInt(cities.length)]);
    }

    private StringBuilder getDateTime() {
        StringBuilder result = new StringBuilder("datetime:");
        return result.append(dateTimeFormatter.format(LocalDateTime.now()));
    }

    private StringBuilder generateSms() {
        StringBuilder result = new StringBuilder("sms:");
        int randomSmsLength = random.nextInt(5, 101);
        return result
                .append(RandomStringUtils.random(randomSmsLength, true, true));
    }

    public String getData() {
        String result = generateNumber()
                .append(":")
                .append(generateCity())
                .append(":")
                .append(getDateTime())
                .append(":")
                .append(generateSms()).toString();
        System.out.println(result);
        return result;
    }

}
