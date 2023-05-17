package com.Geekster.RestaurantManagementServiceAPI.Services;

import com.Geekster.RestaurantManagementServiceAPI.Models.FoodItem;
import com.Geekster.RestaurantManagementServiceAPI.Repositories.IFoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {
    @Autowired
    private IFoodItemRepository foodItemRepository;
    public ResponseEntity<String> addFood(FoodItem foodItem) {
        foodItem.setCreatedAt(LocalDateTime.now());
        foodItem.setUpdatedAt(LocalDateTime.now());
        foodItemRepository.save(foodItem);
        return new ResponseEntity<>("Food item added successfully..!!", HttpStatus.ACCEPTED);
    }

    public List<FoodItem> getAllFoods() {
        return foodItemRepository.findAll();
    }

    public ResponseEntity<FoodItem> updateFoodItem(Long foodId,FoodItem updateFooDItemRequest) {
        Optional<FoodItem> optionalFoodItem =foodItemRepository.findById(foodId);
        if(optionalFoodItem.isPresent()){
            FoodItem foodItem = optionalFoodItem.get();
            foodItem.setUpdatedAt(LocalDateTime.now());
            foodItem.setFoodName(updateFooDItemRequest.getFoodName());
            foodItem.setFoodPrice(updateFooDItemRequest.getFoodPrice());
            foodItem.setDummyImageUrl(updateFooDItemRequest.getDummyImageUrl());
            foodItem.setCreatedAt(LocalDateTime.now());
            foodItemRepository.save(foodItem);
            return new ResponseEntity<>(foodItem,HttpStatus.ACCEPTED);
        }else{
            throw new IllegalStateException("Food item with : " + foodId + " not found");
        }

    }
}
