package com.sergiroj.mlb.controller;


import com.sergiroj.mlb.dto.request.service.CreateTeamRequest;
import com.sergiroj.mlb.dto.request.service.UpdateTeamRequest;
import com.sergiroj.mlb.dto.response.service.TeamResponse;
import com.sergiroj.mlb.dto.response.web.BaseWebResponse;
import com.sergiroj.mlb.exception.ErrorCode;
import com.sergiroj.mlb.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse> createTeam(@RequestBody CreateTeamRequest createTeamRequest){
        String id = teamService.createTeam(createTeamRequest);
        return ResponseEntity.ok(BaseWebResponse.successWithData(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse<List<TeamResponse>>> getAllTeams(){
        List<TeamResponse> teams = teamService.getAllTeams();
        return ResponseEntity.ok(BaseWebResponse.successWithData(teams));
    }

    @PutMapping(value = "/{teamId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse> updateTeam(
            @PathVariable(value = "teamId") String teamId,
            @RequestBody UpdateTeamRequest updateTeamRequest){
if(teamService.updateTeam(updateTeamRequest)){
            return ResponseEntity.ok(BaseWebResponse.successNoData());
        }
        else{
            return ResponseEntity.created(URI.create("/api/v1/books/" + teamId))
                    .body(BaseWebResponse.error(ErrorCode.ENTITY_NOT_FOUND));
        }
    }

    @GetMapping(value = "/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse<TeamResponse>> getTeam(@PathVariable(value = "teamId") String teamId){
        TeamResponse teamResponse = teamService.getTeamDetails(teamId);
        return ResponseEntity.ok(BaseWebResponse.successWithData(teamResponse));
    }

    @DeleteMapping(value = "/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse> deleteTeam(@PathVariable(value = "teamId") String teamId){
        if(teamService.removeTeam(teamId)){
            return ResponseEntity.ok(BaseWebResponse.successNoData());
        }
        else{
            return ResponseEntity.created(URI.create("/api/v1/books" + teamId))
                    .body(BaseWebResponse.error(ErrorCode.ENTITY_NOT_FOUND));
        }
    }
}
