package com.example.calendarservice.repository;

import com.example.calendarservice.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByTeamName(String teamName);
}
