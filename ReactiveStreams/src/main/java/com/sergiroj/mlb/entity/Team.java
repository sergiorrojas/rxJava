package com.sergiroj.mlb.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="teams")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
