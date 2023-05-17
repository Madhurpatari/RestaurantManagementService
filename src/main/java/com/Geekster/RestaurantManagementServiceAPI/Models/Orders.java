package com.Geekster.RestaurantManagementServiceAPI.Models;

import com.Geekster.RestaurantManagementServiceAPI.Models.ENUM.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

   @Id
   @GeneratedValue(strategy =GenerationType.IDENTITY)
   private Long orderID;

   @ManyToOne
   @JoinColumn(name = "food_item_id")
   private FoodItem foodItem;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private Users user;

   private Integer orderQuantity;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   @JsonIgnore
   private OrderStatus status;

   public Long getOrderID() {
      return orderID;
   }

   public void setOrderID(Long orderID) {
      this.orderID = orderID;
   }

   public FoodItem getFoodItem() {
      return foodItem;
   }

   public void setFoodItem(FoodItem foodItem) {
      this.foodItem = foodItem;
   }

   public Users getUser() {
      return user;
   }

   public void setUser(Users user) {
      this.user = user;
   }

   public Integer getOrderQuantity() {
      return orderQuantity;
   }

   public void setOrderQuantity(Integer orderQuantity) {
      this.orderQuantity = orderQuantity;
   }

   public OrderStatus getStatus() {
      return status;
   }

   public void setStatus(OrderStatus status) {
      this.status = status;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public LocalDateTime getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
   }
}
