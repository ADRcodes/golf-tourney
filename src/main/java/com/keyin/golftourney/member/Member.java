package com.keyin.golftourney.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.keyin.golftourney.tournament.Tournament;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String address;

    @Email
    private String email;

    private String phone;
    private LocalDate startDate;
    private Integer duration; // in months

    @ManyToMany
    @JoinTable(
            name = "member_tournaments",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private Set<Tournament> tournaments = new HashSet<>();

    // No-arg constructor for JPA
    public Member() {
    }

    // All-args constructor (excluding id and tournaments)
    public Member(String name, String address, String email, String phone, LocalDate startDate, Integer duration) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    // Convenience methods
    public void enrollIn(Tournament tournament) {
        this.tournaments.add(tournament);
        tournament.getParticipants().add(this);
    }

    public void withdrawFrom(Tournament tournament) {
        this.tournaments.remove(tournament);
        tournament.getParticipants().remove(this);
    }
}
