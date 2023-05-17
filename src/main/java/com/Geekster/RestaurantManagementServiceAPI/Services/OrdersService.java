package com.Geekster.RestaurantManagementServiceAPI.Services;

import com.Geekster.RestaurantManagementServiceAPI.Models.ENUM.OrderStatus;
import com.Geekster.RestaurantManagementServiceAPI.Models.Orders;
import com.Geekster.RestaurantManagementServiceAPI.Repositories.IOrdersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    private IOrdersRepository ordersRepository;
    public ResponseEntity<String> createOrder(Orders orders) {
        orders.setCreatedAt(LocalDateTime.now());
        orders.setUpdatedAt(LocalDateTime.now());
        ordersRepository.save(orders);
        return new ResponseEntity<>("Order placed successfully..!!", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> updateStatus(Long orderId,OrderStatus orderStatus) {
        Optional<Orders> optionalOrders = ordersRepository.findById(orderId);
        if(optionalOrders.isPresent()){
            Orders orders =optionalOrders.get();
            orders.setStatus(orderStatus);
            ordersRepository.save(orders);
            return new ResponseEntity<>("Order status updated to : "+orderStatus, HttpStatus.OK);
        }else{
            throw new EntityNotFoundException("There are no orders with this orderID");
        }
    }



    public List<Orders> getOrder() {
        return ordersRepository.findAll();
    }

    public OrderStatus getOrderStatus(Long orderId ) {
        Optional<Orders> optionalOrders = ordersRepository.findById(orderId);
        if(optionalOrders.isPresent()){
            Orders order  = optionalOrders.get();
            return order.getStatus();
        }else{
            throw new IllegalStateException("No order present with this orderID");
        }


    }
}
