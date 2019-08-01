package com.sergiroj.mlb.dto.request.service;

import com.sergiroj.mlb.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private Team team;
}
