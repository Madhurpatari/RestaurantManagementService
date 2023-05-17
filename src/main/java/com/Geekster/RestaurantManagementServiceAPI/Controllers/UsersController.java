package com.Geekster.RestaurantManagementServiceAPI.Controllers;

import com.Geekster.RestaurantManagementServiceAPI.DTO.SignInRequest;
import com.Geekster.RestaurantManagementServiceAPI.DTO.UserData;
import com.Geekster.RestaurantManagementServiceAPI.DTO.VisitorData;
import com.Geekster.RestaurantManagementServiceAPI.Models.Users;
import com.Geekster.RestaurantManagementServiceAPI.Repositories.IUsersRepository;
import com.Geekster.RestaurantManagementServiceAPI.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private IUsersRepository usersRepository;

    //For admin
    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin(@RequestBody UserData userData){
        String email = userData.getEmail();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";

        if (email.matches(emailRegex)) {
            return usersService.createAdmin(userData);
        } else {
            throw new IllegalStateException("Invalid email address for admin.!!");
        }
    }

    //For normal user signup
    @PostMapping("/sign-up")
    public ResponseEntity<String> signup( @RequestBody UserData userData){
        String email = userData.getEmail();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";

        if (email.matches(emailRegex)) {
            Users admin = usersRepository.findFirstByEmail(email);
            if(admin != null){
                throw new IllegalStateException("This email address already exists as admin you don't need to sign-up!!");
            }else{
                throw new IllegalStateException("Invalid email address for user....try again!!");
            }
        } else {
            return usersService.signUp(userData);
        }

    }

    //sign in
    @PostMapping("/sign-in")
    public ResponseEntity<String> signup(@RequestBody SignInRequest signInRequest){
        String email = signInRequest.getEmail();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";

        if (email.matches(emailRegex)) {
            Users admin = usersRepository.findFirstByEmail(email);
            if(admin != null){
                throw new IllegalStateException("This email address already exists as admin you don't need to sign-in!!");
            }else{
                throw new IllegalStateException("Invalid email address for user....try again!!");
            }
        } else {
            return usersService.signIn(signInRequest);
        }

    }

    @PostMapping("/visitor")
    public  ResponseEntity<String> addVisitor(@RequestBody VisitorData visitorData){
        return usersService.createVisitor(visitorData);
    }

    @GetMapping("/admin")
    ResponseEntity<List<Users>> getAllUsers(@RequestParam String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";

        if (email.matches(emailRegex)){
            Users admin = usersRepository.findFirstByEmail(email);
            if(admin == null){
                throw new IllegalStateException("There is no admin found with this emailId...register as admin first");
            }else {
                List<Users> allUsers = usersService.fetchAllUsers();
                return new ResponseEntity<>(allUsers, HttpStatus.OK);
            }
        }else{
            throw new IllegalStateException("Invalid email address for admin...Only admin can update status.!!");
        }
    }
    @DeleteMapping("/admin/{userId}")
    public ResponseEntity<String> removeUserByUserId(@PathVariable Long userId, @RequestParam String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@admin\\.com$";

        if (email.matches(emailRegex)){
            Users admin = usersRepository.findFirstByEmail(email);
            if(admin == null){
                throw new IllegalStateException("There is no admin found with this emailId...register as admin first");
            }else {
               return usersService.removeUserByUserId(userId);
            }
        }else{
            throw new IllegalStateException("Invalid email address for admin...Only admin can update status.!!");
        }
    }
}
