package com.sergiroj.mlb.service;

import com.sergiroj.mlb.dto.request.service.CreatePlayerRequest;
import com.sergiroj.mlb.dto.request.service.CreateTeamRequest;
import com.sergiroj.mlb.dto.request.service.UpdatePlayerRequest;
import com.sergiroj.mlb.dto.request.service.UpdateTeamRequest;
import com.sergiroj.mlb.dto.response.service.PlayerResponse;
import com.sergiroj.mlb.dto.response.service.TeamResponse;
import rx.Completable;
import rx.Observable;
import rx.Single;

import java.util.List;

public interface PlayerService {
    String createPlayer(CreatePlayerRequest createPlayerRequest);
    Single<String> createPlayerV2(CreatePlayerRequest createPlayerRequest);
    List<PlayerResponse> getAllPlayers();
    Single<List<PlayerResponse>> getAllPlayersV2();
    Observable<List<PlayerResponse>> getAllPlayersV3();
    Single<PlayerResponse> getPlayerDetailsV2(String id);
    boolean updatePlayerV1(UpdatePlayerRequest updatePlayerRequest);
    Completable updatePlayerV2(UpdatePlayerRequest updatePlayerRequest);
    Completable deletePlayerV2(String id);
    
 }
