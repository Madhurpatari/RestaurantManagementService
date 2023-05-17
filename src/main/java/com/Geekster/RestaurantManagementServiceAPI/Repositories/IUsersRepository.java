package com.Geekster.RestaurantManagementServiceAPI.Repositories;

import com.Geekster.RestaurantManagementServiceAPI.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
    Users findFirstByEmail(String email);
}
