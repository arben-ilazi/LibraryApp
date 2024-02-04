package com.example.LibraryApp;

import com.example.LibraryApp.pojo.Book;
import com.example.LibraryApp.pojo.Loan;
import com.example.LibraryApp.pojo.Member;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.LoanRepository;
import com.example.LibraryApp.repository.MemberRepository;
import com.example.LibraryApp.service.DefaultLoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DefaultLoanServiceTest {

    @InjectMocks
    private DefaultLoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLoans() {
        // Mock data
        Loan mockLoan = new Loan();
        when(loanRepository.findAll()).thenReturn(Collections.singletonList(mockLoan));

        // Call the service method
        var result = loanService.getAllLoans();

        // Verify the result
        assertEquals(1, result.size());
        verify(loanRepository, times(1)).findAll();
    }

    @Test
    void testGetLoanById() {
        // Mock data
        Loan mockLoan = new Loan();
        mockLoan.setLoanId(1);
        when(loanRepository.findById(anyInt())).thenReturn(Optional.of(mockLoan));

        // Call the service method
        var result = loanService.getLoanById(1);

        // Verify the result
        assertNotNull(result);
        assertEquals(1, result.getLoanId());
        verify(loanRepository, times(1)).findById(anyInt());
    }

    @Test
    void testCreateLoan() {
        // Mock data
        Loan mockLoan = new Loan();
        Member mockMember = new Member();
        Book mockBook = new Book();

        // Set valid Member and Book for the Loan
        mockMember.setMemberId(1); // Set the Member ID
        mockBook.setBookId(2); // Set the Book ID
        mockLoan.setMember(mockMember);
        mockLoan.setBook(mockBook);

        // Mock repository behavior
        when(memberRepository.findById(anyInt())).thenReturn(Optional.of(mockMember));
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(mockBook));
        when(loanRepository.save(any())).thenReturn(mockLoan);

        // Call the service method
        var result = loanService.createLoan(mockLoan);

        // Verify the result
        assertNotNull(result, "The created loan should not be null");
        assertSame(mockLoan, result, "The created loan should be the same instance as the mockLoan");

        verify(memberRepository, times(1)).findById(anyInt());
        verify(bookRepository, times(1)).findById(anyInt());
        verify(loanRepository, times(1)).save(any());
    }

    @Test
    void testUpdateLoan() {
        // Mock existing loan
        Loan existingLoan = new Loan();
        existingLoan.setLoanId(1);
        existingLoan.setLoanDate("2023-01-20");
        existingLoan.setDueDate("2023-01-27");

        // Mock updated loan with the correct loanId and associated Book and Member
        Loan updatedLoan = new Loan();
        updatedLoan.setLoanId(1);  // Set the loanId to match the existingLoan
        updatedLoan.setLoanDate("2023-01-21");
        updatedLoan.setDueDate("2023-01-28");

        // Mock member
        Member member = new Member();
        member.setMemberId(1);

        // Mock book
        Book book = new Book();
        book.setBookId(1);

        // Set associated Book and Member in updatedLoan
        updatedLoan.setMember(member);
        updatedLoan.setBook(book);

        // Mock behavior of repositories
        when(loanRepository.findById(eq(1))).thenReturn(Optional.of(existingLoan));
        when(memberRepository.findById(eq(1))).thenReturn(Optional.of(member));
        when(bookRepository.findById(eq(1))).thenReturn(Optional.of(book));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the service method
        Loan result = loanService.updateLoan(1, updatedLoan);

        // Verify the result
        assertNotNull(result);
        assertEquals("2023-01-21", result.getLoanDate());
        assertEquals("2023-01-28", result.getDueDate());

        // Verify interactions with repositories
        verify(loanRepository, times(1)).findById(eq(1));
        verify(memberRepository, times(1)).findById(eq(1));
        verify(bookRepository, times(1)).findById(eq(1));
        verify(loanRepository, times(1)).save(any(Loan.class));
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    void testDeleteLoan() {
        // Mock repository behavior
        doNothing().when(loanRepository).deleteById(anyInt());

        // Call the service method
        loanService.deleteLoan(1);

        // Verify the repository method was called
        verify(loanRepository, times(1)).deleteById(anyInt());
    }

    // Add more tests as needed for various scenarios

}