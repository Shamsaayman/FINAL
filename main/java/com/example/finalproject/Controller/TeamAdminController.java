package com.example.finalproject.Controller;


import com.example.finalproject.API.ApiResponse;
import com.example.finalproject.DTO.TeamAdminDTO;
import com.example.finalproject.Model.TeamAdmin;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.TeamAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/team-admin")
@RequiredArgsConstructor
public class TeamAdminController {

    private final TeamAdminService teamAdminService;


    @GetMapping("/get")
    public ResponseEntity getAllTeamAdmins(){
        return ResponseEntity.status(200).body(teamAdminService.getAllTeamAdmins());
    }

    @PutMapping("/update")
    public ResponseEntity updateTeamAdmin(@AuthenticationPrincipal User user, @RequestBody @Valid TeamAdmin teamAdmin){
        teamAdminService.updateTeamAdmin(user.getTeamAdmin().getTeamId() ,teamAdmin);
        return ResponseEntity.status(200).body(new ApiResponse("Team Admin updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteTeamAdmin(@AuthenticationPrincipal User user ){
        teamAdminService.deleteTeamAdmin(user.getTeamAdmin().getTeamId());
        return ResponseEntity.status(200).body(new ApiResponse("Team Admin deleted successfully"));
    }


//    endpoint

    @GetMapping("/logIn")
    public ResponseEntity TeamAdminLogIn(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(teamAdminService.TeamAdminLogIn(user.getTeamAdmin().getTeamId() ,user.getTeamAdmin().getPassword()));
    }



    @PostMapping("/register")
    public ResponseEntity RegisterTeamAdmin(@RequestBody @Valid TeamAdminDTO teamAdminDTO){
        teamAdminService.RegisterTeamAdmin(teamAdminDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Team Admin added successfully"));
    }


    @PutMapping("/approve/{requestId}")
    public ResponseEntity teamAdminApprovesAthlete(@AuthenticationPrincipal User user , @PathVariable Integer requestId){
        teamAdminService.teamAdminApprovesAthlete(user.getTeamAdmin().getTeamId() ,requestId);
        return ResponseEntity.status(200).body(new ApiResponse("Team Admin approved athlete successfully"));
    }


    @PutMapping("/declines/{requestId}")
    public ResponseEntity teamAdminDeclinesAthlete(@AuthenticationPrincipal User user ,@PathVariable Integer requestId){
        teamAdminService.teamAdminDeclinesAthlete(user.getTeamAdmin().getTeamId() ,requestId);
        return ResponseEntity.status(200).body(new ApiResponse("Team Admin Declines athlete successfully"));
    }


    @GetMapping("/get-all-request")
    public ResponseEntity getAllTeamAdminRequests(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(teamAdminService.getAllTeamAdminRequests(user.getTeamAdmin().getTeamId()));
    }


    @GetMapping("/top-teams")
    public ResponseEntity getTop5Teams(){
        return ResponseEntity.status(200).body(teamAdminService.top5Teams());
    }

    @GetMapping("/team-name/{name}")
    public ResponseEntity getTeamByName(@PathVariable String name ){
        return ResponseEntity.status(200).body(teamAdminService.findTeamByName(name));
    }
    @GetMapping("/set-athlete/{athleteId}/{rank}")
    public ResponseEntity setAthleteRank(@AuthenticationPrincipal User user ,@PathVariable Integer athleteId , @PathVariable Integer rank){
        teamAdminService.setAthleteRank(user.getTeamAdmin().getTeamId() ,athleteId,rank);
        return ResponseEntity.status(200).body(new ApiResponse("Athlete Rank Set Successfully "));
    }
    @GetMapping("/logout")
    public ResponseEntity TeamAdminLogout(){
        return ResponseEntity.status(200).body(new ApiResponse("Team Admin logged out"));
    }

    @PutMapping("/add-achievements/{AthleteId}")
    public ResponseEntity addAchievements(@AuthenticationPrincipal User user ,@PathVariable Integer AthleteId){
        teamAdminService.addAthleteAchievements(user.getTeamAdmin().getTeamId(),AthleteId);
        return ResponseEntity.status(200).body(new ApiResponse("Achievements Successfully"));
    }


    @GetMapping("/search-all-sports")
    public ResponseEntity searchAllSportsInTeam(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body("Sports: "+teamAdminService.searchAllSportsInTeam(user.getTeamAdmin().getTeamId()));
    }

    @GetMapping("/all-sports")
    public ResponseEntity ViewAllSports(){
        return ResponseEntity.status(200).body(teamAdminService.viewAllSports());
    }

    @GetMapping("/view-atchevments/{athelteName}")
    public ResponseEntity athelteAtchevments(@PathVariable String athelteName){
        return ResponseEntity.status(200).body(teamAdminService.athelteAtchevments(athelteName));
    }


    @GetMapping("/view-teems")
    public ResponseEntity getAllTeams(){
        return ResponseEntity.status(200).body("The Teams is:"+teamAdminService
                .getAllTeams());
    }

}
