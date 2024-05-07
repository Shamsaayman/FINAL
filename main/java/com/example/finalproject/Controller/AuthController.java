package com.example.finalproject.Controller;


import com.example.finalproject.Model.User;
import com.example.finalproject.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid User user){
        authService.login(user.getUsername(), user.getPassword());
        return ResponseEntity.status(200).body("Login successful");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        authService.logout();
        return ResponseEntity.status(200).body("Logout successful");
    }
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }
    @PutMapping("/update/{username}")
    public ResponseEntity updateUser( @AuthenticationPrincipal User user , @PathVariable String username){
        authService.updateUser(username, user);
        return ResponseEntity.status(200).body("Update successful");
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser( @AuthenticationPrincipal User user){
        authService.deleteUser(user.getId());
        return ResponseEntity.status(200).body("Delete successful");
    }
}
