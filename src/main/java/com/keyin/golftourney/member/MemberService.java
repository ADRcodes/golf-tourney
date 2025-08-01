package com.keyin.golftourney.member;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repo;
    public MemberService(MemberRepository repo) { this.repo = repo; }

    public Member save(Member m) { return repo.save(m); }
    public List<Member> findAll() { return repo.findAll(); }
    public Member findById(Long id) { return repo.findById(id).orElse(null); }
    public List<Member> findByName(String name) { return repo.findByNameContainingIgnoreCase(name); }
    public List<Member> findByPhone(String phone) { return repo.findByPhone(phone); }
    public List<Member> findByTournamentDate(LocalDate date) {
        return repo.findByTournaments_StartDate(date);
    }
}