package ru.otus.de.project.consumergeo.validation;

import org.springframework.stereotype.Service;
import ru.otus.de.project.consumergeo.model.Geo;

@Service
public class Validator {

    private String regexStringNumber = "\\d{10}";

    private boolean isNumberValid(String number) {
        return !(number == null || number.isEmpty()) && number.matches(regexStringNumber);
    }

    private boolean isCityValid(String city) {
        return !(city == null || city.isEmpty());
    }

    public boolean isGeoValid(Geo geo) {
        return isNumberValid(geo.getNumber()) && isCityValid(geo.getCity());
    }
}
