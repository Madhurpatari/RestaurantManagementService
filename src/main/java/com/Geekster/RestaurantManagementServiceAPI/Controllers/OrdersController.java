package com.Geekster.RestaurantManagementServiceAPI.Controllers;

import com.Geekster.RestaurantManagementServiceAPI.Models.ENUM.OrderStatus;
import com.Geekster.RestaurantManagementServiceAPI.Models.Orders;
import com.Geekster.RestaurantManagementServiceAPI.Models.Users;
import com.Geekster.RestaurantManagementServiceAPI.Repositories.IUsersRepository;
import com.Geekster.RestaurantManagementServiceAPI.Services.AuthenticationService;
import com.Geekster.RestaurantManagementServiceAPI.Services.OrdersService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private IUsersRepository usersRepository;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestParam String email, @RequestParam String token, @RequestBody Orders orders){
        if(token == null && email != null){
            Users user = usersRepository.findFirstByEmail(email);
            if(user != null && user.getRole().equals("Visitor")){
                throw new IllegalStateException("You're visitor you need to register..try sign-up");
            }else{
                throw new IllegalStateException("User Invalid..try again!!");
            }
        }else {
            if (authenticationService.authenticate(email, token)) {
                return ordersService.createOrder(orders);
            } else {
                throw new IllegalStateException("User Invalid..try again!!");
            }
        }
    }
    @GetMapping
    public ResponseEntity<List<Orders>> getOrder(@RequestParam String email, @RequestParam String token){
        List<Orders> allOrders = null;
        HttpStatus status;
        if(authenticationService.authenticate(email,token)){
            allOrders = ordersService.getOrder();
            status =HttpStatus.OK;
        }else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>(allOrders, status);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String email,@RequestParam OrderStatus orderStatus){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";

        if (email.matches(emailRegex)){
            Users admin = usersRepository.findFirstByEmail(email);
            if(admin == null){
                throw new IllegalStateException("There is no admin found with this emailId...register as admin first");
            }else {
                return ordersService.updateStatus(orderId,orderStatus);
            }
        }else{
            throw new IllegalStateException("Invalid email address for admin...Only admin can update status.!!");
        }
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Long orderId ,@RequestParam String email, @RequestParam String token){
        OrderStatus orderStatus = null;
        HttpStatus status;
        if(authenticationService.authenticate(email,token)){
            orderStatus = ordersService.getOrderStatus(orderId);
            status=HttpStatus.OK;
        }else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>("your order status is : "+ orderStatus, status);
    }
}
