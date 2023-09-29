package com.example.opd.services;

import com.example.opd.models.User;
import com.example.opd.models.enums.Role;
import com.example.opd.repositories.QualityRepository;
import com.example.opd.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {} ", user.getEmail());
        userRepository.save(user);
        return true;
    }
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }
    public void banUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            user.setActive(false);
            log.info("{} was banned",user.getEmail());
        }
        userRepository.save(user);
    }
    public void unBanUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null && user.isEnabled()==false){
            user.setActive(true);
            log.info("{} was unbanned",user.getEmail());
        }
        userRepository.save(user);
    }
    public void changeRole(User user, Map<String, String> form){
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key : form.keySet()){
                if(roles.contains(key)){
                    user.getRoles().add(Role.valueOf(key));
                }
        }
        userRepository.save(user);
    }
    public User getUserByPrincipal(Principal principal){
        if (principal == null ) return new User();
        return userRepository.findByEmail(principal.getName());
    }

}
