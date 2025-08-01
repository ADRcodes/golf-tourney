package com.keyin.golftourney.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhone(String phone);
    List<Member> findByTournaments_StartDate(LocalDate date);
}