package com.example.LibraryApp.service;


import com.example.LibraryApp.pojo.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> getAllLoans();
    Loan getLoanById(Integer loanId);
    Loan createLoan(Loan loan);
    Loan updateLoan(Integer loanId, Loan updatedLoan);
    void deleteLoan(Integer loanId);

}
