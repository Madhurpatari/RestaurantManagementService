package com.Geekster.RestaurantManagementServiceAPI.Services;

import com.Geekster.RestaurantManagementServiceAPI.Models.AuthenticationToken;
import com.Geekster.RestaurantManagementServiceAPI.Models.Users;
import com.Geekster.RestaurantManagementServiceAPI.Repositories.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private ITokenRepository tokenRepository;
    public void saveToken(AuthenticationToken token) {
        tokenRepository.save(token);
    }

    public AuthenticationToken getToken(Users user) {
        return tokenRepository.findByUser(user);
    }

    public boolean authenticate(String email, String token) {
        AuthenticationToken authenticationToken = tokenRepository.findFirstByToken(token);
        if(authenticationToken == null) {
            return false;
        }
        String expectedEmail = authenticationToken.getUser().getEmail();
        if(expectedEmail == null) return false;
        return expectedEmail.equals(email);
    }
}
