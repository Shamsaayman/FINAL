package com.example.finalproject.Service;

import com.example.finalproject.API.ApiException;
import com.example.finalproject.Model.User;
import com.example.finalproject.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public List<User> getAllUsers(){
            return authRepository.findAll();
    }
    public void register(User user){
        user.setRole("CUSTOMER");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }
    public void updateUser(String username, User user){
        User u = authRepository.findUserByUsername(username);
        if(u==null){
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        authRepository.save(u);
    }

    public void deleteUser(Integer userId){
        User u = authRepository.findUserById(userId);
        if(u==null){
            throw new ApiException("User not found");
        }
        if(!(u.getRole().equals("ADMIN"))){
            throw new ApiException("User not authorized");
        }
        authRepository.delete(u);
    }

    public User login(String username, String password){
        User user = null;
        return user;
    }

    public void logout(){
    }



}