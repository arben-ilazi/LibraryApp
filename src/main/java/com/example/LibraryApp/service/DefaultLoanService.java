package com.example.LibraryApp.service;


import com.example.LibraryApp.pojo.Book;
import com.example.LibraryApp.pojo.Loan;
import com.example.LibraryApp.pojo.Member;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.LoanRepository;
import com.example.LibraryApp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultLoanService implements LoanService{
    @Autowired LoanRepository loanRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired BookRepository bookRepository;

    @Override
    public List<Loan> getAllLoans() {
        return (List<Loan>) loanRepository.findAll();
    }

    @Override
    public Loan getLoanById(Integer loanId) {
        return loanRepository.findById(loanId).orElse(null);
    }

    @Override
    public Loan createLoan(Loan loan) {
        if (loan.getMember() == null || loan.getMember().getMemberId() == null) {
            throw new IllegalArgumentException("Member ID cannot be null");
        }

        if (loan.getBook() == null || loan.getBook().getBookId() == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        // Check if the specified member and book IDs exist
        Member member = memberRepository.findById(loan.getMember().getMemberId())
                .orElseThrow(() -> new NoSuchElementException("Member with ID " + loan.getMember().getMemberId() + " not found"));

        Book book = bookRepository.findById(loan.getBook().getBookId())
                .orElseThrow(() -> new NoSuchElementException("Book with ID " + loan.getBook().getBookId() + " not found"));

        if (member.getLoans() != null && member.getLoans().size() >= 3) {
            // Handle the case where the member has reached the maximum allowed loans
            throw new IllegalArgumentException("Member with ID " + member.getMemberId() + " has reached the maximum allowed loans.");
        }

        // Set the member and book for the new loan
        loan.setMember(member);
        loan.setBook(book);

        // Save and return the new loan
        return loanRepository.save(loan);
    }

    @Override
    public Loan updateLoan(Integer loanId, Loan updatedLoan) {
        Loan existingLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan with ID " + loanId + " not found"));

        // Update loan attributes
        if (updatedLoan.getLoanDate() != null) {
            existingLoan.setLoanDate(updatedLoan.getLoanDate());
        }

        if (updatedLoan.getDueDate() != null) {
            existingLoan.setDueDate(updatedLoan.getDueDate());
        }

        // Update associated member
        if (updatedLoan.getMember() != null && updatedLoan.getMember().getMemberId() != null) {
            Member newMember = memberRepository.findById(updatedLoan.getMember().getMemberId())
                    .orElseThrow(() -> new NoSuchElementException("Member with ID " + updatedLoan.getMember().getMemberId() + " not found"));

            // Check if the new member has already loaned three or more books
            if (newMember.getLoans() != null && newMember.getLoans().size() >= 3) {
                // Handle the case where the new member has reached the maximum allowed loans
                throw new IllegalArgumentException("Member with ID " + newMember.getMemberId() + " has reached the maximum allowed loans.");
            }

            existingLoan.setMember(newMember);

            // Save the updated member explicitly
            memberRepository.save(newMember);
        }

        // Update associated book
        if (updatedLoan.getBook() != null && updatedLoan.getBook().getBookId() != null) {
            Book book = bookRepository.findById(updatedLoan.getBook().getBookId())
                    .orElseThrow(() -> new NoSuchElementException("Book with ID " + updatedLoan.getBook().getBookId() + " not found"));

            existingLoan.setBook(book);
        }

        // Save and return the updated loan
        return loanRepository.save(existingLoan);
    }

    @Override
    public void deleteLoan(Integer loanId) {
        loanRepository.deleteById(loanId);
    }
}
