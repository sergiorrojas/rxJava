package com.sergiroj.mlb.service.impl;

import com.sergiroj.mlb.dto.request.service.CreatePlayerRequest;
import com.sergiroj.mlb.dto.request.service.UpdatePlayerRequest;
import com.sergiroj.mlb.dto.response.service.PlayerResponse;
import com.sergiroj.mlb.entity.Player;
import com.sergiroj.mlb.repository.PlayerRepository;
import com.sergiroj.mlb.service.PlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Completable;
import rx.Observable;
import rx.Single;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public String createPlayer(CreatePlayerRequest createPlayerRequest) {
        return savePlayer(createPlayerRequest);
    }

    @Override
    public Single<String> createPlayerV2(CreatePlayerRequest createPlayerRequest) {
        return Single.create(subscriber -> {
            String playerId = savePlayer(createPlayerRequest);
            subscriber.onSuccess(playerId);
        });
    }

    @Override
    public List<PlayerResponse> getAllPlayers() {
        return findAllPlayers().stream()
                .map(this::convertToPlayerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Single<List<PlayerResponse>> getAllPlayersV2() {
        return Single.create(subscriber -> {
            List<PlayerResponse> players = findAllPlayers()
                    .stream()
                    .map(this::convertToPlayerResponse)
                    .collect(Collectors.toList());
            subscriber.onSuccess(players);
        });
    }

    @Override
    public Observable<List<PlayerResponse>> getAllPlayersV3() {
        return Observable.create(observer -> {
            List<PlayerResponse> players = findAllPlayers()
                    .stream()
                    .map(this::convertToPlayerResponse)
                    .collect(Collectors.toList());
            observer.onNext(players);
            observer.onCompleted();
        });
    }

    @Override
    public Single<PlayerResponse> getPlayerDetailsV2(String id) {
        return findPlayerDetails(id);
    }

    @Override
    public boolean updatePlayerV1(UpdatePlayerRequest updatePlayerRequest) {
        return updatePlayerRepository(updatePlayerRequest);
    }

    @Override
    public Completable updatePlayerV2(UpdatePlayerRequest updatePlayerRequest) {
        return updatePlayerRepositoryV2(updatePlayerRequest);
    }

    @Override
    public Completable deletePlayerV2(String id) {
        return removePlayerInRepository(id);
    }

    private String savePlayer(CreatePlayerRequest createPlayerRequest){
        return playerRepository.save(convertToPlayer(createPlayerRequest)).getId();
    }

    private Player convertToPlayer(CreatePlayerRequest createPlayerRequest){
        Player player = new Player();
        BeanUtils.copyProperties(createPlayerRequest, player);
        player.setId(UUID.randomUUID().toString());
        return player;
    }

    private PlayerResponse convertToPlayerResponse(Player player){
        PlayerResponse playerResponse = new PlayerResponse();
        BeanUtils.copyProperties(player, playerResponse);
        return playerResponse;
    }

    private boolean updatePlayerRepository(UpdatePlayerRequest updatePlayerRequest) {
        Optional<Player> optionalPlayer = playerRepository.findById(updatePlayerRequest.getId());
        if(!optionalPlayer.isPresent()){
            return false;
        }
        else{
            Player player = new Player();
            BeanUtils.copyProperties(updatePlayerRequest, player);
            playerRepository.save(player);
            return true;
        }
    }

    private Completable updatePlayerRepositoryV2(UpdatePlayerRequest updatePlayerRequest){
        return Completable.create(completableSubscriber -> {
            Optional<Player> optionalPlayer = playerRepository.findById(updatePlayerRequest.getId());
            if(optionalPlayer.isPresent()){
                Player player = new Player();
                BeanUtils.copyProperties(updatePlayerRequest, player);
                playerRepository.save(player);
                completableSubscriber.onCompleted();
            }

            else{
                completableSubscriber.onError(new EntityNotFoundException());
            }
        });
    }

    private boolean removePlayerRepository(String id){
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if(!optionalPlayer.isPresent()){
            return false;
        }
        else{
            playerRepository.delete(optionalPlayer.get());
            return true;
        }
    }

    private Completable removePlayerInRepository(String id){
        return Completable.create(completableSubscriber -> {
            Optional<Player> optionalPlayer = playerRepository.findById(id);
            if(optionalPlayer.isPresent()) {
                playerRepository.delete(optionalPlayer.get());
                completableSubscriber.onCompleted();
            }
            else {
                completableSubscriber.onError(new EntityNotFoundException());
            }
        });
    }

    private PlayerResponse getPlayer(String id){
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        return convertToPlayerResponse(optionalPlayer.get());
    }

    private Single<PlayerResponse> findPlayerDetails(String id) {
        return Single.create(singleSubscriber -> {
            Optional<Player> optionalPlayer = playerRepository.findById(id);
            if(optionalPlayer.isPresent()) {
                singleSubscriber.onSuccess(optionalPlayer.map(this::convertToPlayerResponse).get());
            }
            else{
                singleSubscriber.onError(new EntityNotFoundException());
            }
        });
    }


    private List<Player> findAllPlayers(){
        return playerRepository.findAll();
    }


}
