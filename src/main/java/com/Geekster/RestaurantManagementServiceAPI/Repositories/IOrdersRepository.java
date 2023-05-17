package com.Geekster.RestaurantManagementServiceAPI.Repositories;

import com.Geekster.RestaurantManagementServiceAPI.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {
}
