package com.example.LibraryApp.service;

import com.example.LibraryApp.pojo.Member;

import java.util.List;

public interface MemberService {

    List<Member> getAllMembers();
    Member getMemberById(Integer memberId);
    Member createMember(Member member);
    Member updateMember(Integer memberId, Member UpdatedMember);
    void deleteMember(Integer memberId);
}