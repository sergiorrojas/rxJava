package com.sergiroj.mlb.repository;

import com.sergiroj.mlb.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
