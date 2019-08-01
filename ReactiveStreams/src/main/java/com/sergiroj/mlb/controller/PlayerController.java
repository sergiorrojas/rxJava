package com.sergiroj.mlb.controller;

import com.sergiroj.mlb.dto.request.service.CreatePlayerRequest;
import com.sergiroj.mlb.dto.request.service.UpdatePlayerRequest;
import com.sergiroj.mlb.dto.response.service.PlayerResponse;
import com.sergiroj.mlb.dto.response.web.BaseWebResponse;
import com.sergiroj.mlb.exception.ErrorCode;
import com.sergiroj.mlb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.schedulers.Schedulers;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping(value = "/v1/players", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse> createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest){
        String id = playerService.createPlayer(createPlayerRequest);
        return ResponseEntity.ok(BaseWebResponse.successWithData(id));
    }

    @GetMapping(value = "/v1/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse<List<PlayerResponse>>> getAllPlayers(){
        List<PlayerResponse> players = playerService.getAllPlayers();
        return ResponseEntity.ok(BaseWebResponse.successWithData(players));
    }

    @PostMapping(value = "/v2/players", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse>> createPlayerV2(@RequestBody CreatePlayerRequest createPlayerRequest){
        return playerService.createPlayerV2(createPlayerRequest)
                .subscribeOn(Schedulers.io())
                .map(id -> ResponseEntity.created(URI.create("/api/v1/players/" + id))
                        .body(BaseWebResponse.successWithData(id)));
    }

    @GetMapping(value = "/v2/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse<List<PlayerResponse>>>> getAllPlayersV2(){
        return playerService.getAllPlayersV2()
                .subscribeOn(Schedulers.io())
                .map(playerResponses -> ResponseEntity.ok(BaseWebResponse.successWithData(playerResponses)));
    }

    @GetMapping(value = "/v3/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<ResponseEntity<BaseWebResponse<List<PlayerResponse>>>>getAllPlayersV3(){
        return playerService.getAllPlayersV3()
                .subscribeOn(Schedulers.io())
                .map(playerResponses -> ResponseEntity.ok(BaseWebResponse.successWithData(playerResponses)));

    }

    @GetMapping(value = "/v1/players/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<BaseWebResponse<PlayerResponse>>> getPlayerDetails(@PathVariable(value = "playerId") String playerId){
        return playerService.getPlayerDetailsV2(playerId)
                .subscribeOn(Schedulers.io())
                .map(playerResponse -> ResponseEntity.ok(BaseWebResponse.successWithData(playerResponse)));
    }

    @PutMapping(value = "/v1/players/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse> updatePlayer(@PathVariable(value = "playerId") String playerId, @RequestBody UpdatePlayerRequest updatePlayerRequest){
        if(playerService.updatePlayerV1(updatePlayerRequest)){
            return ResponseEntity.ok(BaseWebResponse.successNoData());
        }
        else{
            return ResponseEntity.created(URI.create("/api/v1/players/" + playerId))
                    .body(BaseWebResponse.error(ErrorCode.ENTITY_NOT_FOUND));
        }
    }

    @PutMapping(value = "/v2/players/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Completable updatePlayerV2(@PathVariable(value = "playerId") String playerId, @RequestBody UpdatePlayerRequest updatePlayerRequest){
        return playerService.updatePlayerV2(updatePlayerRequest).subscribeOn(Schedulers.io());
    }

    @DeleteMapping(value = "/v2/players/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Completable deletePlayer(@PathVariable(value = "playerId") String playerId){
        return playerService.deletePlayerV2(playerId);
    }


}
