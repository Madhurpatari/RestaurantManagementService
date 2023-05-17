package com.Geekster.RestaurantManagementServiceAPI.Controllers;

import com.Geekster.RestaurantManagementServiceAPI.Models.FoodItem;
import com.Geekster.RestaurantManagementServiceAPI.Models.Users;
import com.Geekster.RestaurantManagementServiceAPI.Repositories.IUsersRepository;
import com.Geekster.RestaurantManagementServiceAPI.Services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private IUsersRepository usersRepository;

    @PostMapping
    public ResponseEntity<String> addFood(@RequestParam String email , @RequestBody FoodItem foodItem){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";
        if (email.matches(emailRegex)) {
            Users admin = usersRepository.findFirstByEmail(email);
            if(admin == null){
                throw new IllegalStateException("There is no admin found with this emailId...register as admin first");
            }else {
                return foodItemService.addFood(foodItem);
            }
        } else {
            throw new IllegalStateException("Invalid email address for admin...Only admin can add food.!!");
        }
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> viewAvailableFoods(){
        List<FoodItem> allFoods = foodItemService.getAllFoods();
        return new ResponseEntity<>(allFoods, HttpStatus.OK);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long foodId,@RequestParam String email,@RequestBody FoodItem updateFooDItemRequest){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";
        if (email.matches(emailRegex)){
            Users admin = usersRepository.findFirstByEmail(email);
            if(admin == null){
                throw new IllegalStateException("There is no admin found with this emailId...register as admin first");
            }else {
                return foodItemService.updateFoodItem(foodId,updateFooDItemRequest);
            }
        } else {
            throw new IllegalStateException("Invalid email address for admin...Only admin can add food.!!");
        }
    }
}
