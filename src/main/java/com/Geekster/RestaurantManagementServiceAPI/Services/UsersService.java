package com.Geekster.RestaurantManagementServiceAPI.Services;

import com.Geekster.RestaurantManagementServiceAPI.DTO.SignInRequest;
import com.Geekster.RestaurantManagementServiceAPI.DTO.UserData;
import com.Geekster.RestaurantManagementServiceAPI.DTO.VisitorData;
import com.Geekster.RestaurantManagementServiceAPI.Models.AuthenticationToken;
import com.Geekster.RestaurantManagementServiceAPI.Models.Users;
import com.Geekster.RestaurantManagementServiceAPI.Repositories.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private AuthenticationService authenticationService;

    //save admin without sign-up and sign-in
    public ResponseEntity<String> createAdmin(UserData userData) {
        Users admin = usersRepository.findFirstByEmail(userData.getEmail());
        if(admin != null){
            throw new IllegalStateException("Admin with this email already exist..!!");
        }
        //Encrypt admin password
        String hashedPassword = DigestUtils.md5DigestAsHex(userData.getPassword().getBytes());

        //save in database
         admin = new Users(
                null,
                userData.getFirstName(),
                userData.getLastName(),
                userData.getEmail(),
                hashedPassword,
                "Admin");
          usersRepository.save(admin);

          return new ResponseEntity<>("Admin saved successfully..!!", HttpStatus.OK);
    }

    //signup
    public ResponseEntity<String> signUp(UserData userData) {
        //check if user already exist or not
        Users user =usersRepository.findFirstByEmail(userData.getEmail());

        //if user is  not null this means user with this email already exists in the database
        if(user != null){
            throw new IllegalStateException("User with this email already exist..try sign-in");
        }
        //Encryption of password
        String hashedPassword= DigestUtils.md5DigestAsHex(userData.getPassword().getBytes());

        Users verifiedUser = new Users(
                null,
                userData.getFirstName(),
                userData.getLastName(),
                userData.getEmail(),
                hashedPassword,
                "Verified User");
        usersRepository.save(verifiedUser);
        return new ResponseEntity<>("User registered successfully..!!",HttpStatus.OK);
    }

    //sign-in
    public ResponseEntity<String> signIn(SignInRequest signInRequest){
        //Get user email and check if it's already there in the database or not
        Users user = usersRepository.findFirstByEmail(signInRequest.getEmail());

        //if user is null it means there is no patient registered with this email
        if(user == null){
            throw new IllegalStateException("No User is registered with this email..try sign-up!!");
        }

        //Encrypt the user password to check if this encrypted password is exists in the database
        String encryptedPass = DigestUtils.md5DigestAsHex(signInRequest.getPassword().getBytes());

        //Match it with already existing passwords
        boolean isPasswordValid = encryptedPass.equals(user.getPassword());

        //if it is not valid throw exception
        if(!isPasswordValid){
            throw new IllegalStateException("User Invalid..try sign-in again!!");
        }
        //Create and save token
        AuthenticationToken token = new AuthenticationToken(user);
        authenticationService.saveToken(token);
        //figure out the token
        AuthenticationToken authenticationToken = authenticationService.getToken(user);

        return new ResponseEntity<>(authenticationToken.getToken(), HttpStatus.ACCEPTED);

    }

    public  ResponseEntity<String> createVisitor(VisitorData visitorData) {
        //check if user already exist or not
        Users user =usersRepository.findFirstByEmail(visitorData.getEmail());

        //if user is  not null this means user with this email already exists in the database
        if(user != null){
            throw new IllegalStateException("User with this email already exists..!!");
        }
        Users visitor = new Users(
                null,
                visitorData.getFirstName(),
                visitorData.getLastName(),
                visitorData.getEmail(),
                null,
                "Visitor");
        usersRepository.save(visitor);
        return new ResponseEntity<>("you're added as visitor!!",HttpStatus.OK);
    }

    public List<Users> fetchAllUsers() {
        return usersRepository.findAll();
    }

    public ResponseEntity<String> removeUserByUserId(Long userId) {
        boolean isUserExists = usersRepository.existsById(userId);
        if(isUserExists){
             usersRepository.deleteById(userId);
             return new ResponseEntity<>("User deleted Successfully..!!",HttpStatus.NO_CONTENT);
        }else{
            throw new IllegalStateException("No user found with this user Id");
        }
    }
}
