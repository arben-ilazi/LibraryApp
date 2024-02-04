package com.example.LibraryApp;

import com.example.LibraryApp.pojo.Member;
import com.example.LibraryApp.repository.MemberRepository;
import com.example.LibraryApp.service.DefaultMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultMemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private DefaultMemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMembers() {
        // Given
        Member member1 = new Member();
        member1.setMemberId(1);
        member1.setName("John");
        member1.setEmail("john@example.com");
        member1.setPhone("123456789");
        member1.setAddress("123 Main St");

        Member member2 = new Member();
        member2.setMemberId(2);
        member2.setName("Alice");
        member2.setEmail("alice@example.com");
        member2.setPhone("987654321");
        member2.setAddress("456 Oak St");

        List<Member> members = Arrays.asList(member1, member2);
        when(memberRepository.findAll()).thenReturn(members);

        // When
        List<Member> result = memberService.getAllMembers();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void getMemberById() {
        // Given
        int memberId = 1;
        Member member = new Member();
        member.setMemberId(memberId);
        member.setName("John");
        member.setEmail("john@example.com");
        member.setPhone("123456789");
        member.setAddress("123 Main St");

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        // When
        Member result = memberService.getMemberById(memberId);

        // Then
        assertEquals(member, result);
    }

    @Test
    void createMember() {
        // Given
        Member member = new Member();
        member.setName("John");
        member.setEmail("john@example.com");
        member.setPhone("123456789");
        member.setAddress("123 Main St");

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        // When
        Member result = memberService.createMember(member);

        // Then
        assertEquals(member, result);
    }

    @Test
    void updateMember() {
        // Given
        int memberId = 1;
        Member existingMember = new Member();
        existingMember.setMemberId(memberId);
        existingMember.setName("John");
        existingMember.setEmail("john@example.com");
        existingMember.setPhone("123456789");
        existingMember.setAddress("123 Main St");

        Member updatedMember = new Member();
        updatedMember.setName("John Doe");
        updatedMember.setEmail("johndoe@example.com");
        updatedMember.setPhone("987654321");
        updatedMember.setAddress("456 Oak St");

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(existingMember));
        when(memberRepository.save(existingMember)).thenReturn(existingMember);

        // When
        Member result = memberService.updateMember(memberId, updatedMember);

        // Then
        assertEquals(updatedMember.getName(), result.getName());
        assertEquals(updatedMember.getEmail(), result.getEmail());
        assertEquals(updatedMember.getPhone(), result.getPhone());
        assertEquals(updatedMember.getAddress(), result.getAddress());
    }

    @Test
    void deleteMember() {
        // Given
        int memberId = 1;

        // When
        memberService.deleteMember(memberId);

        // Then
        verify(memberRepository, times(1)).deleteById(memberId);
    }
}
