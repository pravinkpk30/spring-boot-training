package com.dc.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.pharmacy.dto.AuthRequest;
import com.dc.pharmacy.dto.UserInfo;
import com.dc.pharmacy.service.IUserService;
import com.dc.pharmacy.utility.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public")
@Validated
public class AuthController {
    
    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public UserInfo signup(@Valid @RequestBody UserInfo userInfo) {
        return userService.signUp(userInfo);
    }


    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            // String jwtToken = jwtUtil.generateToken(authentication.getName()); // If you remove this, it could be basic authentication
            String jwtToken = jwtUtil.generateToken(authentication.getPrincipal());
            System.out.println("Authentication successful: " + authentication);
            return jwtToken;
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("Invalid username");
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid password");
        } catch (Exception e) {
            throw new RuntimeException("Authentication Failed");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}    
        return ResponseEntity.ok("Logout Successfull");   
    }
}
