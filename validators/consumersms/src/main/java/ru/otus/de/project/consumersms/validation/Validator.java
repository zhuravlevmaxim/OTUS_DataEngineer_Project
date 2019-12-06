package ru.otus.de.project.consumersms.validation;

import org.springframework.stereotype.Service;
import ru.otus.de.project.consumersms.model.Sms;

@Service
public class Validator {

    private String regexStringNumber = "\\d{10}";

    private boolean isNumberValid(String number) {
        return !(number == null || number.isEmpty()) && number.matches(regexStringNumber);
    }

    private boolean isCityValid(String city) {
        return !(city == null || city.isEmpty());
    }

    public boolean isSmsValid(Sms sms) {
        return isNumberValid(sms.getNumber()) && isCityValid(sms.getCity());
    }
}
