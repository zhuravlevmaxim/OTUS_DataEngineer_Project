package ru.otus.de.project.bdvalidwriterpayment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.de.project.bdvalidwriterpayment.entity.PaymentValid;

public interface PaymentValidRepository extends CrudRepository<PaymentValid, Long> {

}
