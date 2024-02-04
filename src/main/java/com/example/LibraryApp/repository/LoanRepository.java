package com.example.LibraryApp.repository;

import com.example.LibraryApp.pojo.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan,Integer> {
}