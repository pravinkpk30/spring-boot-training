package com.dc.pharmacy.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.dc.pharmacy.entity.UserEntity;
import com.dc.pharmacy.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Invalid Login or User not found : " + username);
        }
        String[] roles = userEntity.getRoles().split(",");
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword()) 
                .roles(roles) 
                .build();
    }
    
    // private Collection<? extends GrantedAuthority> getAuthorities(User user) {
    //     return user.getRoles().stream()
    //             .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
    //             .collect(Collectors.toList());
    // }
}

