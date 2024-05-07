package com.example.finalproject.Controller;

import com.example.finalproject.API.ApiResponse;
import com.example.finalproject.DTO.AthleteDTO;
import com.example.finalproject.Model.Athlete;
import com.example.finalproject.Model.User;

import com.example.finalproject.Service.AthleteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/athlete")
@RequiredArgsConstructor
public class AthleteController {
    private final AthleteService athleteService;
    @GetMapping("/get")
    public ResponseEntity getAllAthletes(){
        return ResponseEntity.status(200).body(athleteService.getAllAthletes());
    }

    @PutMapping("/update")
    public ResponseEntity updateAthlete(@AuthenticationPrincipal User user , @RequestBody @Valid AthleteDTO athleteDTO){
        athleteService.updateAthlete(user.getId(), athleteDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Athlete updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAthlete(@AuthenticationPrincipal User user ){
        athleteService.deleteAthlete(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Athlete deleted"));
    }


    //ENDPOINTS

    @PostMapping("/register-athlete")
    public ResponseEntity RegisterAthlete( @RequestBody @Valid AthleteDTO athleteDTO){
        athleteService.RegisterAthlete(athleteDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Athlete Register successful"));
    }


    @PostMapping("/register-team-athlete")
    public ResponseEntity TeamAdminRegisterAthlete(@AuthenticationPrincipal User user, @RequestBody @Valid AthleteDTO athleteDTO){
        athleteService.TeamAdminRegisterAthlete(user, athleteDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Team Admin Athlete Registration successful"));
    }

    @GetMapping("/logIn/{athleteId}/{password}")
    public ResponseEntity AthleteLogIn(@AuthenticationPrincipal User user){
        athleteService.AthleteLogIn(user.getId(), user.getPassword());
        return ResponseEntity.status(200).body(new ApiResponse("Athlete Login Successful"));
    }

    @GetMapping("/logout")
    public ResponseEntity AthleteLogOut(){
        return ResponseEntity.status(200).body(new ApiResponse("Athlete LogOut Successful"));
    }

    @GetMapping("/find-athlete/{name}")
    public ResponseEntity findAthleteByName(@PathVariable String name){
        return ResponseEntity.status(200).body(athleteService.findAthleteBySportName(name));
    }
    @GetMapping("/get-top5")
    public ResponseEntity top5Athletes(){
        return ResponseEntity.status(200).body(athleteService.top5Athletes());
    }

    @GetMapping("/view-details")
    public ResponseEntity viewDetails(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(athleteService.viewDetails(user.getId()));
    }

    @GetMapping("/view-teems")
    public ResponseEntity getAllTeams(){
        return ResponseEntity.status(200).body("The Teams is:"+athleteService.getAllTeams());
    }


    @GetMapping("/search-by-name/{name}")
    public ResponseEntity SearchAthleteByName(@PathVariable String name){
        return ResponseEntity.status(200).body(athleteService.SearchAthleteByName(name));
    }


    @GetMapping("/get-achievements")
    public ResponseEntity getAchievements(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(athleteService.getAchievements(user.getId()));
    }

    @PostMapping("/join/{teamName}")
    public ResponseEntity askJoin(@RequestBody @Valid Athlete athlete,@PathVariable String teamName){
        athleteService.askJoinToTeam(athlete,teamName);
        return ResponseEntity.status(200).body(new ApiResponse("Join Request Sent to Team Admin Successfully"));
    }

    @GetMapping("/all-sports")
    public ResponseEntity ViewAllSports(){
        return ResponseEntity.status(200).body(athleteService.viewAllSports());
    }

    @GetMapping("/view-atchevments/{athelteName}")
    public ResponseEntity athelteAtchevments(@PathVariable String athelteName){
        return ResponseEntity.status(200).body(athleteService.athelteAtchevments(athelteName));
    }

}
