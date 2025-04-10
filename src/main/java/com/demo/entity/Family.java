package com.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "family")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private UUID adminUserId;

    @NotNull
    @Column(name = "invite_code" ,nullable = false, unique = true)
    private UUID inviteCode;
}