package com.sergiroj.mlb.beans;

import com.sergiroj.mlb.dto.request.service.CreatePlayerRequest;
import com.sergiroj.mlb.dto.response.service.TeamResponse;
import com.sergiroj.mlb.entity.Team;
import com.sergiroj.mlb.service.PlayerService;
import com.sergiroj.mlb.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanExampleBean implements InitializingBean {

    @Autowired
    private Environment environment;

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerService playerService;

    @Override
    public void afterPropertiesSet() throws Exception {

        Team team = new Team();
        TeamResponse teamResp = teamService.getTeamDetails("d8343306-19b2-4b46-9dd9-69cad0b1338a");
        BeanUtils.copyProperties(teamResp, team);
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        for(int i = 0; i<100000; i ++){
            createPlayerRequest.setFirstName("Sergio");
            createPlayerRequest.setLastName("Rojas");
            createPlayerRequest.setTeam(team);
            playerService.createPlayer(createPlayerRequest);
        }

    }
}
