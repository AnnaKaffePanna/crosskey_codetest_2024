package com.example.crosskey_codetest_spring.db.repositories;

import com.example.crosskey_codetest_spring.db.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long>{

}
