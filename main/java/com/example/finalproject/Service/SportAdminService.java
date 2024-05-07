package com.example.finalproject.Service;


import com.example.finalproject.API.ApiException;
import com.example.finalproject.DTO.SportAdminDTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportAdminService {

    private final SportAdminRepository sportAdminRepository;
    private final SportAdminRequestRepository sportAdminRequestRepository;
    private final TeamAdminRepository teamAdminRepository;
    private final SportRepository sportRepository;
    private final AuthRepository authRepository;




    public List<SportAdmin> getAllSportAdmin() {
        return sportAdminRepository.findAll();
    }


    public void RegisterSportAdmin(SportAdminDTO sportAdminDTO) {

        User u = new User(null, sportAdminDTO.getSportAdminName(), sportAdminDTO.getPassword(), "SPORT-ADMIN",null,null,null,null);
        String hashPassword = new BCryptPasswordEncoder().encode(sportAdminDTO.getPassword());
        u.setPassword(hashPassword);
        authRepository.save(u);

        SportAdmin sportAdmin= new SportAdmin(null,sportAdminDTO.getSportAdminName(),sportAdminDTO.getPassword(),sportAdminDTO.getLicenseNumber(),sportAdminDTO.getPhoneNumber(),sportAdminDTO.getEmail(),sportAdminDTO.getSportType(),null,u,null,null);
        sportAdminRepository.save(sportAdmin);
    }


    public void updateSportAdmin(Integer sportAdminId ,SportAdminDTO sportAdminDTO) {
        SportAdmin sportAdmin1 = sportAdminRepository.findSportAdminByAdminId(sportAdminId);
        if(sportAdmin1 == null) {
            throw new ApiException("Invalid SportAdmin ID");
        }

        sportAdmin1.setSportAdminName(sportAdminDTO.getSportAdminName());
        sportAdmin1.setSportType(sportAdminDTO.getSportType());
        sportAdmin1.setEmail(sportAdminDTO.getEmail());
        sportAdmin1.setPassword(sportAdminDTO.getPassword());
        sportAdmin1.setLicenseNumber(sportAdminDTO.getLicenseNumber());
        sportAdmin1.setPhoneNumber(sportAdminDTO.getPhoneNumber());
        sportAdminRepository.save(sportAdmin1);

    }



    public void deleteSportAdmin(Integer sportAdminId) {
        SportAdmin sportAdmin = sportAdminRepository.findSportAdminByAdminId(sportAdminId);
        if(sportAdmin == null) {
            throw new ApiException("Invalid SportAdmin ID");
        }
        sportAdminRepository.delete(sportAdmin);
    }


    public String SportAdminLogIn(Integer sportAdminId , String password){
        SportAdmin sportAdmin = sportAdminRepository.findSportAdminByAdminId(sportAdminId);
        if (sportAdmin == null || !sportAdmin.getPassword().equals(password)){
            throw new ApiException("invalid user id or password");
        }

        return "Welcome " + sportAdmin.getSportAdminName() + "To Olympic Sphere";
    }

    public void sportAdminDeclinesTeamAdmin(Integer sportAdminId , Integer requestId){
        SportAdmin sportAdmin = sportAdminRepository.findSportAdminByAdminId(sportAdminId);
        SportAdminRequest sportAdminRequest = sportAdminRequestRepository.findSportAdminRequestByRequestId(requestId);
        if (sportAdmin == null || sportAdminRequest == null){
            throw new ApiException("invalid request id or sport admin id");
        }
        sportAdminRequest.setStatus("Declined");
    }

    public Sport searchSportByName(String sportName){
        return sportRepository.findSportByName(sportName);
    }

    public List<Sport> viewAllSports(){
        return sportRepository.findAll();
    }

    public void setTeamRank(Integer sportAdminId , Integer teamAdminId , Integer rank){
        SportAdmin sportAdmin = sportAdminRepository.findSportAdminByAdminId(sportAdminId);
        TeamAdmin teamAdmin= teamAdminRepository.findTeamAdminByTeamId(teamAdminId);
        if (sportAdmin == null || teamAdmin == null){
            throw new ApiException("invalid request team admin id or sport admin id");
        }
        teamAdmin.setTeamRank(rank);
        teamAdminRepository.save(teamAdmin);
    }


    public void sportAdminAcceptTeamAdmin(Integer sportAdminId , String teamName){
        SportAdmin sportAdmin = sportAdminRepository.findSportAdminByAdminId(sportAdminId);
        SportAdminRequest sportAdminRequest = sportAdminRequestRepository.findSportAdminRequestByTeamName(teamName);
        if (sportAdmin == null || sportAdminRequest == null){
            throw new ApiException("invalid request id or sport admin id");
        }
        sportAdminRequest.setStatus("Accepted");
    }

    public List<SportAdminRequest> vieweRequest(Integer sportAdminId){
        SportAdmin sportAdmin = sportAdminRepository.findSportAdminByAdminId(sportAdminId);
        List<SportAdminRequest>requests=sportAdminRequestRepository.findSportAdminRequestByStatus("Pending");
        return requests;
    }

}
