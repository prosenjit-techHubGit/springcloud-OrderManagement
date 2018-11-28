package io.cts.msa.om.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cts.msa.om.order.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
