package ru.otus.de.project.bdvalidwriterpayment.model;

import java.util.Objects;

public class Payment {

    private String number;

    private String city;

    private String datetime;

    private String payment;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment1 = (Payment) o;
        return Objects.equals(number, payment1.number) &&
                Objects.equals(city, payment1.city) &&
                Objects.equals(datetime, payment1.datetime) &&
                Objects.equals(payment, payment1.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, city, datetime, payment);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", datetime='" + datetime + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}