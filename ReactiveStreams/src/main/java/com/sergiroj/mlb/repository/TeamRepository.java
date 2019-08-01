package com.sergiroj.mlb.repository;

import com.sergiroj.mlb.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
