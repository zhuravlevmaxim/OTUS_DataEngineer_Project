package ru.otus.de.project.consumerpayment.validation;

import org.springframework.stereotype.Service;
import ru.otus.de.project.consumerpayment.model.Payment;

@Service
public class Validator {

    private String regexStringNumber = "\\d{10}";
    private boolean paymentValid = false;

    private boolean isNumberValid(String number) {
        return !(number == null || number.isEmpty()) && number.matches(regexStringNumber);
    }

    private boolean isCityValid(String city) {
        return !(city == null || city.isEmpty());
    }

    private boolean isPaymentValid(String payment) {
        try {
            Double paymentDouble = Double.parseDouble(payment);
            paymentValid = paymentDouble > 0 ? true : false;
        } catch (NumberFormatException ex) {
            return false;
        }
        return paymentValid;
    }

    public boolean isPaymentValid(Payment payment) {
        return isNumberValid(payment.getNumber())
                && isCityValid(payment.getCity())
                && isPaymentValid(payment.getPayment());
    }
}
