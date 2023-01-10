package com.example.calendarservice.service;

import com.example.calendarservice.Entity.Team;
import com.example.calendarservice.dto.TeamDto;
import com.example.calendarservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Long saveTeam(TeamDto teamDto) {
        ModelMapper mapper = new ModelMapper();
        Team team = mapper.map(teamDto, Team.class);
        teamRepository.save(team);
        return team.getId();
    }

    public Team findByName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }
}
