package com.keyin.golftourney.tournament;

import com.keyin.golftourney.member.Member;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Tournament> create(@RequestBody Tournament tournament) {
        Tournament saved = service.save(tournament);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getById(@PathVariable Long id) {
        Tournament found = service.findById(id);
        return (found != null) ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tournament>> search(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String location
    ) {
        if (startDate != null) return ResponseEntity.ok(service.findByStartDate(startDate));
        if (location != null) return ResponseEntity.ok(service.findByLocation(location));
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/{tid}/members/{mid}")
    public ResponseEntity<Tournament> addMember(
            @PathVariable Long tid,
            @PathVariable Long mid
    ) {
        Tournament updated = service.addMember(tid, mid);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{tid}/members")
    public ResponseEntity<List<Member>> participants(@PathVariable Long tid) {
        List<Member> list = service.getParticipants(tid);
        return ResponseEntity.ok(list);
    }
}