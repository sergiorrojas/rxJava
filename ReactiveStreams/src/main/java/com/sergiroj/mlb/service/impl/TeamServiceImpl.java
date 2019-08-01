package com.sergiroj.mlb.service.impl;

import com.sergiroj.mlb.dto.request.service.CreateTeamRequest;
import com.sergiroj.mlb.dto.request.service.UpdateTeamRequest;
import com.sergiroj.mlb.dto.response.service.TeamResponse;
import com.sergiroj.mlb.entity.Team;
import com.sergiroj.mlb.repository.TeamRepository;
import com.sergiroj.mlb.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Override
    public String createTeam(CreateTeamRequest createTeamRequest) {
        return saveTeam(createTeamRequest);
    }

    @Override
    public boolean updateTeam(UpdateTeamRequest updateTeamRequest) {
        return updateTeamRepository(updateTeamRequest);
    }

    @Override
    public TeamResponse getTeamDetails(String id) {
        return getTeam(id);
    }

    @Override
    public boolean removeTeam(String id) {
        return removeTeamRepository(id);
    }

    @Override
    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(this::convertToTeamResponse)
                .collect(Collectors.toList());
    }

    private String saveTeam(CreateTeamRequest createTeamRequest){
        return teamRepository.save(convertToTeam(createTeamRequest)).getId();
    }

    private Team convertToTeam(CreateTeamRequest createTeamRequest){
        Team team = new Team();
        BeanUtils.copyProperties(createTeamRequest, team);
        team.setId(UUID.randomUUID().toString());
        return team;
    }

    private TeamResponse convertToTeamResponse(Team team){
        TeamResponse teamResponse = new TeamResponse();
        BeanUtils.copyProperties(team, teamResponse);
        return teamResponse;
    }

    private boolean updateTeamRepository(UpdateTeamRequest updateTeamRequest) {
        Optional<Team> optionalTeam = teamRepository.findById(updateTeamRequest.getId());
        if(!optionalTeam.isPresent()){
            return false;
        }
        else{
            Team team = optionalTeam.get();
            team.setName(updateTeamRequest.getName());
            teamRepository.save(team);
            return true;
        }
    }

    private boolean removeTeamRepository(String id){
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if(!optionalTeam.isPresent()){
            return false;
        }
        else{
            teamRepository.delete(optionalTeam.get());
            return true;
        }
    }

    private TeamResponse getTeam(String id){
        Optional<Team> optionalTeam = teamRepository.findById(id);
        return convertToTeamResponse(optionalTeam.get());
    }
}
