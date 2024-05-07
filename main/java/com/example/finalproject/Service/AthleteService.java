package com.example.finalproject.Service;

import com.example.finalproject.API.ApiException;
import com.example.finalproject.DTO.AthleteDTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AthleteService {
    private final AthleteRepository athleteRepository;
    private final TeamAdminRepository teamAdminRepository;
    private final TeamAdminRequestRepository teamAdminRequestRepository;
    private final AuthRepository authRepository;
    private  final SportRepository sportRepository;

    public List<Athlete> getAllAthletes(){
        return athleteRepository.findAll();
    }


// GENERATE PASSWORD !!
    public void RegisterAthlete(AthleteDTO athleteDTO) {
        String hashPassword = new BCryptPasswordEncoder().encode(athleteDTO.getPassword());
        athleteDTO.setPassword(hashPassword);

        Athlete athlete = new Athlete(athleteDTO.getId() , athleteDTO.getUsername() , athleteDTO.getPassword() ,
                athleteDTO.getEmail() ,athleteDTO.getAge() ,athleteDTO.getWeight(),athleteDTO.getHeight() , athleteDTO.getGender()
                ,athleteDTO.getSport() ,athleteDTO.getCategory(), athleteDTO.getAchievements(),
                athleteDTO.getLicenseNumber(), athleteDTO.getRank(), null, null ,null);


        User user = new User(null , athleteDTO.getUsername() , athleteDTO.getPassword() ,"ATHLETE" ,null ,
                null , null ,athlete );

        athleteRepository.save(athlete);
        authRepository.save(user);

    }


    public void TeamAdminRegisterAthlete(User user, AthleteDTO athleteDTO) {
        User u = new User();
        u.setUsername(user.getUsername());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        user.setRole("ATHLETE");

        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);

        Athlete a = new Athlete();
        a.setAthleteId(athleteDTO.getId());
        a.setEmail(athleteDTO.getEmail());
        a.setAge(athleteDTO.getAge());
        a.setGender(athleteDTO.getGender());
        a.setSport(athleteDTO.getSport());
        a.setWeight(athleteDTO.getWeight());
        a.setHeight(athleteDTO.getHeight());
        a.setCategory(athleteDTO.getCategory());

        u.setAthlete(a);

        authRepository.save(u);
        athleteRepository.save(a);
    }

    public void updateAthlete(Integer athleteId ,  AthleteDTO athleteDTO){
        Athlete athlete = athleteRepository.findAthleteByAthleteId(athleteId);
        athlete.setUsername(athleteDTO.getUsername());
        athlete.setPassword(athleteDTO.getPassword());
        athlete.setEmail(athleteDTO.getEmail());
        athlete.setAge(athleteDTO.getAge());
        athlete.setGender(athleteDTO.getGender());
        athlete.setSport(athleteDTO.getSport());
        athlete.setWeight(athleteDTO.getWeight());
        athlete.setHeight(athleteDTO.getHeight());
        athlete.setCategory(athleteDTO.getCategory());
        athleteRepository.save(athlete);
    }

    public void deleteAthlete(Integer athleteId){
        Athlete athlete1 = athleteRepository.findAthleteByAthleteId(athleteId);
        athleteRepository.delete(athlete1);
    }


//    endpoint

    public String AthleteLogIn(Integer AthleteId , String password){
        Athlete athlete = athleteRepository.findAthleteByAthleteId(AthleteId);
        if (athlete == null || !athlete.getPassword().equals(password)){
            throw new ApiException("invalid user id or password");
        }

        return "Welcome " + athlete.getUsername() + "To Olympic Sphere";
    }

// name or sport name ??
    public List<Athlete>findAthleteBySportName(String sportname){
        return athleteRepository.findAthleteBySport(sportname);
    }

    public List<Athlete> top5Athletes(){
        return athleteRepository.findAthleteByRankBetween(1,5);
    }


    public void askJoinToTeam (Athlete athlete,String teamName){
        TeamAdmin teamAdmin=teamAdminRepository.findTeamAdminByteamName(teamName);
        if (teamAdmin == null){
            throw new ApiException("Invalid Team Name");
        }
        TeamAdminRequest teamAdminRequest = new TeamAdminRequest();
        teamAdminRequest.setAthlete(athlete);
        teamAdminRequest.getTeamAdminRequests().add(teamAdmin);
        teamAdminRequest.setStatus("Pending");
        teamAdminRequestRepository.save(teamAdminRequest);

        athleteRepository.save(athlete);
    }

    public Athlete viewDetails(Integer athleteId){
        Athlete athlete=athleteRepository.findAthleteByAthleteId(athleteId);
        return athlete;
    }

    public List<String> getAllTeams(){
        List<TeamAdmin>teamAdmins=teamAdminRepository.findAll();
        List<String>teams=new ArrayList<>();
        for(TeamAdmin team:teamAdmins){
            teams.add("Name: "+team.getTeamName()+" Number of Athletes: "+team.getAthleteSet().size());
        }
        return teams;
    }

    public Integer getAchievements(Integer athleteId){
        Athlete athlete=athleteRepository.findAthleteByAthleteId(athleteId);
        return athlete.getAchievements();
    }


    public String SearchAthleteByName(String AthleteName){
        Athlete athlete=athleteRepository.findAthleteByUsername(AthleteName);
        return " Name: "+athlete.getUsername()+" Sport: "+athlete.getSport()+" Weight: "+athlete.getWeight()+
                " Height: "+athlete.getHeight()+" Category: "+athlete.getCategory()+" "+athlete.getSport()+
                " Achievements: "+athlete.getAchievements()+" Gender:  "+athlete.getGender()+
                " Age: "+athlete.getAge()+" LicenseNumber: "+athlete.getLicenseNumber();
    }

    public List<Sport> viewAllSports(){
        return sportRepository.findAll();
    }


    public String athelteAtchevments(String ahthelteName){
       Athlete athlete=athleteRepository.findAthleteByUsername(ahthelteName);
        if(athlete==null){
            throw new ApiException("Athlete Not Found");
        }
        return "Athlete Atchevments is: "+athlete.getAchievements();
    }



}



