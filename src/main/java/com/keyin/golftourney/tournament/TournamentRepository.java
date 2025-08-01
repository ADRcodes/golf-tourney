package com.keyin.golftourney.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByStartDate(LocalDate date);
    List<Tournament> findByLocationContainingIgnoreCase(String location);
}