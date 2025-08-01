package com.keyin.golftourney.member;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        Member saved = service.save(member);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Member>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@PathVariable Long id) {
        Member found = service.findById(id);
        return (found != null) ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Member>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tournamentDate
    ) {
        if (name != null) return ResponseEntity.ok(service.findByName(name));
        if (phone != null) return ResponseEntity.ok(service.findByPhone(phone));
        if (tournamentDate != null) return ResponseEntity.ok(service.findByTournamentDate(tournamentDate));
        return ResponseEntity.ok(service.findAll());
    }
}