package ru.otus.de.project.bdinvalidwriterpayment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.de.project.bdinvalidwriterpayment.entity.PaymentInvalid;

public interface PaymentInvalidRepository extends CrudRepository<PaymentInvalid, Long> {

}
