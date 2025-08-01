package com.keyin.golftourney.tournament;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.keyin.golftourney.member.Member;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private Double entryFee;
    private Double cashPrizeAmount;

    @ManyToMany(mappedBy = "tournaments")
    private Set<Member> participants = new HashSet<>();

    // No-arg constructor for JPA
    public Tournament() {
    }

    // All-args constructor (excluding id and participants)
    public Tournament(LocalDate startDate, LocalDate endDate, String location, Double entryFee, Double cashPrizeAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }

    public Double getCashPrizeAmount() {
        return cashPrizeAmount;
    }

    public void setCashPrizeAmount(Double cashPrizeAmount) {
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public Set<Member> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Member> participants) {
        this.participants = participants;
    }

    // Convenience methods
    public void addParticipant(Member member) {
        this.participants.add(member);
        member.getTournaments().add(this);
    }

    public void removeParticipant(Member member) {
        this.participants.remove(member);
        member.getTournaments().remove(this);
    }
}
