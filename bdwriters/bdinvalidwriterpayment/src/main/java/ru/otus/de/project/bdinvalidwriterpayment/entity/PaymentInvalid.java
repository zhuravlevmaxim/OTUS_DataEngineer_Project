package ru.otus.de.project.bdinvalidwriterpayment.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payment", schema = "invalid_data")
public class PaymentInvalid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "city")
    private String city;
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "payment")
    private String payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
        if (!(o instanceof PaymentInvalid)) return false;
        PaymentInvalid paymentInvalid = (PaymentInvalid) o;
        return Objects.equals(id, paymentInvalid.id) &&
                Objects.equals(number, paymentInvalid.number) &&
                Objects.equals(city, paymentInvalid.city) &&
                Objects.equals(dateTime, paymentInvalid.dateTime) &&
                Objects.equals(payment, paymentInvalid.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, city, dateTime, payment);
    }

    @Override
    public String toString() {
        return "PaymentInvalid{" +
                "id=" + id +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", dateTime=" + dateTime +
                ", payment='" + payment + '\'' +
                '}';
    }
}
