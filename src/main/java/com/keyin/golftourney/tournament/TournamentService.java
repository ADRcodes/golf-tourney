package com.keyin.golftourney.tournament;

import com.keyin.golftourney.member.Member;
import com.keyin.golftourney.member.MemberRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tr;
    private final MemberRepository mr;

    public TournamentService(TournamentRepository tr, MemberRepository mr) {
        this.tr = tr; this.mr = mr;
    }

    public Tournament save(Tournament t) { return tr.save(t); }
    public List<Tournament> findAll() { return tr.findAll(); }
    public Tournament findById(Long id) { return tr.findById(id).orElse(null); }
    public List<Tournament> findByStartDate(LocalDate date) { return tr.findByStartDate(date); }
    public List<Tournament> findByLocation(String loc) { return tr.findByLocationContainingIgnoreCase(loc); }

    public Tournament addMember(Long tId, Long mId) {
        Tournament t = findById(tId);
        Member m = mr.findById(mId).orElse(null);
        if (t != null && m != null) {
            t.getParticipants().add(m);
            return tr.save(t);
        }
        return null;
    }

    public List<Member> getParticipants(Long tId) {
        Tournament t = findById(tId);
        return t != null ? List.copyOf(t.getParticipants()) : List.of();
    }
}