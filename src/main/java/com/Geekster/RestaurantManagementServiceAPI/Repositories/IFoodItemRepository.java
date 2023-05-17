package com.Geekster.RestaurantManagementServiceAPI.Repositories;

import com.Geekster.RestaurantManagementServiceAPI.Models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodItemRepository extends JpaRepository<FoodItem, Long> {
}
