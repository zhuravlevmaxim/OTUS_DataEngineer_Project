package ru.otus.de.project.bdvalidwriterpayment.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payment", schema = "valid_data")
public class PaymentValid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private Long number;
    @Column(name = "city")
    private String city;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "payment")
    private Double payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentValid)) return false;
        PaymentValid paymentValid = (PaymentValid) o;
        return Objects.equals(id, paymentValid.id) &&
                Objects.equals(number, paymentValid.number) &&
                Objects.equals(city, paymentValid.city) &&
                Objects.equals(dateTime, paymentValid.dateTime) &&
                Objects.equals(payment, paymentValid.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, city, dateTime, payment);
    }

    @Override
    public String toString() {
        return "PaymentValid{" +
                "id=" + id +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", dateTime=" + dateTime +
                ", payment='" + payment + '\'' +
                '}';
    }
}
