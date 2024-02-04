package com.example.LibraryApp.controller;

import com.example.LibraryApp.pojo.Member;
import com.example.LibraryApp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired MemberService memberService;

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/members/{memberId}")
    public Member findMemberById(@PathVariable Integer memberId) {
        return memberService.getMemberById(memberId);
    }

    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @PutMapping("/members/{memberId}")
    public Member updateMember(@PathVariable Integer memberId, @RequestBody Member member) {
        return memberService.updateMember(memberId,member);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMemberById(@PathVariable Integer memberId) {
        memberService.deleteMember(memberId);
    }
}
