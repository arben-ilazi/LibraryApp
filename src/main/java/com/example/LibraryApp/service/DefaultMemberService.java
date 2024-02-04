package com.example.LibraryApp.service;


import com.example.LibraryApp.pojo.Member;
import com.example.LibraryApp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class DefaultMemberService implements MemberService{
    @Autowired MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Integer memberId, Member UpdatedMember) {
        // Check if the member with the given ID exists
        Set<Member> members = Collections.singleton(memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member with ID " + memberId + " not found")));

        Member existingMember = members.iterator().next();
        existingMember.setName(UpdatedMember.getName());
        existingMember.setEmail(UpdatedMember.getEmail());
        existingMember.setPhone(UpdatedMember.getPhone());
        existingMember.setAddress(UpdatedMember.getAddress());
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
    }
}
