package com.Geekster.RestaurantManagementServiceAPI.Models;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public AuthenticationToken(Users user) {
        this.tokenCreationDate = LocalDate.now();
        this.token = UUID.randomUUID().toString();
        this.user = user;
    }
}
