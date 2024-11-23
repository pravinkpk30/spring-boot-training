package com.dc.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.pharmacy.dto.AuthRequest;
import com.dc.pharmacy.dto.UserInfo;
import com.dc.pharmacy.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public")
@Validated
public class AuthController {
    
    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public UserInfo signup(@Valid @RequestBody UserInfo userInfo) {
        return userService.signUp(userInfo);
    }


    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            System.out.println("Authentication successful: " + authentication);
            return "Login Successfull";
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("Invalid username");
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid password");
        } catch (Exception e) {
            throw new RuntimeException("Authentication Failed");
        }
    }

    @PostMapping("/logout")
    public void logout() {

    }
}
