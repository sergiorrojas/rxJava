package com.sergiroj.mlb.service;

import com.sergiroj.mlb.dto.request.service.CreateTeamRequest;
import com.sergiroj.mlb.dto.request.service.UpdateTeamRequest;
import com.sergiroj.mlb.dto.response.service.TeamResponse;

import java.util.List;

public interface TeamService {
    String createTeam(CreateTeamRequest createTeamRequest);
    boolean updateTeam(UpdateTeamRequest updateTeamRequest);
    TeamResponse getTeamDetails(String id);
    boolean removeTeam(String id);
    List<TeamResponse> getAllTeams();
 }
