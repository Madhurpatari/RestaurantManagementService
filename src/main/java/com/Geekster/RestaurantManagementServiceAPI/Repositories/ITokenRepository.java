package com.Geekster.RestaurantManagementServiceAPI.Repositories;

import com.Geekster.RestaurantManagementServiceAPI.Models.AuthenticationToken;
import com.Geekster.RestaurantManagementServiceAPI.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepository extends JpaRepository<AuthenticationToken, Long> {
     AuthenticationToken findFirstByToken(String token);

    AuthenticationToken findByUser(Users user);
}
