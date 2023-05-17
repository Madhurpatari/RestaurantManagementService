package com.Geekster.RestaurantManagementServiceAPI.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FoodId;
    @NotEmpty
    private String foodName;
    private Double foodPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String dummyImageUrl;


}
