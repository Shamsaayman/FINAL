package com.example.finalproject.Service;


import com.example.finalproject.API.ApiException;
import com.example.finalproject.DTO.TeamAdminDTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import com.example.finalproject.Model.SportAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamAdminService {

    private final TeamAdminRepository teamAdminRepository;
    private final AthleteRepository athleteRepository;
    private final TeamAdminRequestRepository teamAdminRequestRepository;
    private final SportAdminRepository sportAdminRepository;
    private final  AuthRepository authRepository ;
    private  final SportRepository sportRepository;


    public List<TeamAdmin> getAllTeamAdmins() {
        return teamAdminRepository.findAll();
    }


    public void updateTeamAdmin(Integer teamAdminId ,TeamAdmin teamAdmin) {
        TeamAdmin teamAdmin1 = teamAdminRepository.findTeamAdminByTeamId(teamAdminId);

        teamAdmin1.setTeamName(teamAdmin.getTeamName());
        teamAdmin1.setTeamAdminName(teamAdmin.getTeamAdminName());
        teamAdmin1.setTeamEmail(teamAdmin.getTeamEmail());
        teamAdmin1.setPassword(teamAdmin.getPassword());
        teamAdmin1.setPhoneNumber(teamAdmin.getPhoneNumber());

        teamAdminRepository.save(teamAdmin1);

    }



    public void deleteTeamAdmin(Integer teamAdminId){
        TeamAdmin teamAdmin1 = teamAdminRepository.findTeamAdminByTeamId(teamAdminId);
        teamAdminRepository.delete(teamAdmin1);
    }



//    endpoint


    public String TeamAdminLogIn(Integer teamAdminId , String password){
        TeamAdmin teamAdmin1 = teamAdminRepository.findTeamAdminByTeamId(teamAdminId);
        if (!teamAdmin1.getPassword().equals(password)){
            throw new ApiException("Invalid Id or password");
        }
        return "Welcome " + teamAdmin1.getTeamAdminName() + "To Olympic Sphere";
    }

    public void RegisterTeamAdmin(TeamAdminDTO teamAdminDTO) {

        User user = new User(null, teamAdminDTO.getTeamAdminName(), teamAdminDTO.getPassword(), "TEAM-ADMIN",null,null,null,null);
        String hashPassword = new BCryptPasswordEncoder().encode(teamAdminDTO.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);

        TeamAdmin teamAdmin = new TeamAdmin(null , teamAdminDTO.getTeamName() , teamAdminDTO.getPassword() , teamAdminDTO.getTeamAdminName() , teamAdminDTO.getTeamEmail() , teamAdminDTO.getPhoneNumber() , teamAdminDTO.getTeamRank() , user, null ,null , null );
        teamAdminRepository.save(teamAdmin);
    }




    public void teamAdminApprovesAthlete(Integer teamAdminId ,Integer requestId){
        TeamAdmin teamAdmin = teamAdminRepository.findTeamAdminByTeamId(teamAdminId);
        TeamAdminRequest teamAdminRequest = teamAdminRequestRepository.findTeamAdminRequestById(requestId);

        if (teamAdmin == null || teamAdminRequest == null){
            throw new ApiException("Invalid team admin id or request id");
        }
        if(teamAdminRequest.getStatus().equals("Declined")){
            teamAdminRequest.setStatus("Approved");
        } else {
            throw new ApiException("request status not declined");
        }

    }





    public void teamAdminDeclinesAthlete(Integer teamAdminId ,Integer requestId){
        TeamAdmin teamAdmin = teamAdminRepository.findTeamAdminByTeamId(teamAdminId);
        TeamAdminRequest teamAdminRequest = teamAdminRequestRepository.findTeamAdminRequestById(requestId);

        if (teamAdmin == null || teamAdminRequest == null){
            throw new ApiException("Invalid team admin id or request id");
        }
        if(teamAdminRequest.getStatus().equals("Approved")){
            teamAdminRequest.setStatus("Declined");
        } else {
            throw new ApiException("request status not approved");
        }

    }



    public List<TeamAdminRequest> getAllTeamAdminRequests(Integer TeamAdminId) {
        TeamAdmin teamAdmin = teamAdminRepository.findTeamAdminByTeamId(TeamAdminId);
        if (teamAdmin == null){
            throw new ApiException("Invalid Id");
        } else if(!teamAdmin.getTeamName().equals(teamAdmin.getTeamAdminName())){
            throw new ApiException("Team Admin does not match");
        }
        return teamAdminRequestRepository.findAll();
    }



    public List<TeamAdmin> top5Teams(){
        return teamAdminRepository.findTeamAdminByTeamRankBetween(1,5);
    }

    public List<TeamAdmin> findTeamByName(String teamName){
        return teamAdminRepository.findTeamAdminByTeamName(teamName);
    }

    public void setAthleteRank(Integer teamAdminId ,Integer athleteId , Integer rank){
        TeamAdmin teamAdmin = teamAdminRepository.findTeamAdminByTeamId(teamAdminId);
        Athlete athlete = athleteRepository.findAthleteByAthleteId(athleteId);
        if (athlete == null|| teamAdmin == null){
            throw new ApiException("invalid athlete id or team admin id");
        }
        athlete.setRank(rank);
        athleteRepository.save(athlete);
    }



    public void addAthleteAchievements(Integer teamAdminId ,Integer AthleteId){
        Athlete athlete=athleteRepository.findAthleteByLicenseNumber(AthleteId);
        TeamAdmin teamAdmin=teamAdminRepository.findTeamAdminByTeamId(athlete.getTeamAdmin().getTeamId());
        if(teamAdminId!=teamAdmin.getTeamId()){
            throw new ApiException("Invalid");
        }else
            athlete.setAchievements(athlete.getAchievements()+1);
        athleteRepository.save(athlete);
    }

    public List searchAllSportsInTeam(Integer teamAdminId){
        TeamAdmin teamAdmin=teamAdminRepository.findTeamAdminByTeamId(teamAdminId);
        List<SportAdmin> sport=sportAdminRepository.findAll();
        List<String> sports=new ArrayList<>();
        if(teamAdmin==null){
            throw new ApiException("Invalid");
        }else
            for(SportAdmin sport1:sport){
                sports.add(sport1.getSportType());
            }return sports;
    }

    public List<Sport> viewAllSports(){
        return sportRepository.findAll();
    }



    public String athelteAtchevments(String ahthelteName){
        Athlete athlete= athleteRepository.findAthleteByUsername(ahthelteName);
        if(athlete==null){
            throw new ApiException("Athelte Not Found");
        }
        return "Athlete Atchevments is: "+athlete.getAchievements();
    }


    public List<String> getAllTeams(){
        List<TeamAdmin>teamAdmins=teamAdminRepository.findAll();
        List<String>teams=new ArrayList<>();
        for(TeamAdmin team:teamAdmins){
            teams.add("Name: "+team.getTeamName()+" Number of Athletes: "+team.getAthleteSet().size());
        }
        return teams;
    }

}
