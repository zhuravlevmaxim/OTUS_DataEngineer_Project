package ru.otus.de.project.servicepayment.data;

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

    private StringBuilder generatePayment() {
        StringBuilder result = new StringBuilder("payment:");
        return result
                .append(random.nextInt(-11, 100_000));
    }

    public String getData() {
        String result = generateNumber()
                .append(":")
                .append(generateCity())
                .append(":")
                .append(getDateTime())
                .append(":")
                .append(generatePayment()).toString();
        System.out.println(result);
        return result;
    }

}
