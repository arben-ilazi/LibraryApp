package com.example.LibraryApp.controller;

import com.example.LibraryApp.pojo.Loan;
import com.example.LibraryApp.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    @Autowired LoanService loanService;
    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/loans/{loanId}")
    public Loan getLoanById(@PathVariable Integer loanId) {
        return loanService.getLoanById(loanId);
    }

    @PostMapping("/loans")
    public Loan createLoan(@RequestBody Loan newLoan) {
        return loanService.createLoan(newLoan);
    }

    @PutMapping("/loans/{loanId}")
    public Loan updateLoan(@PathVariable Integer loanId, @RequestBody Loan updatedLoan) {
        return loanService.updateLoan(loanId, updatedLoan);
    }

    @DeleteMapping("/loans/{loanId}")
    public void deleteLoan(@PathVariable Integer loanId) {
        loanService.deleteLoan(loanId);
    }

}
