package com.example.calendarservice.service;

import com.example.calendarservice.Entity.Team;
import com.example.calendarservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public Team findByName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }
}
