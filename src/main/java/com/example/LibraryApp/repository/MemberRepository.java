package com.example.LibraryApp.repository;

import com.example.LibraryApp.pojo.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
}

