package com.example.finalproject.Service;

import com.example.finalproject.API.ApiException;
import com.example.finalproject.DTO.SportDTO;
import com.example.finalproject.Model.Sport;
import com.example.finalproject.Model.SportAdmin;
import com.example.finalproject.Repository.SportAdminRepository;
import com.example.finalproject.Repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SportService {
    private final SportRepository sportRepository;
    private final SportAdminRepository sportAdminRepository;

    public void addSport(SportDTO sportDTO) {
        SportAdmin sportAdmin=sportAdminRepository.findSportAdminByAdminId(sportDTO.getSport_admin_admin_id());
        if(sportAdmin==null){
            throw  new ApiException("Id not found");
        }

        Sport sport1=new Sport(null,sportDTO.getName(),sportAdmin);
        sportRepository.save(sport1);
    }

    public List<Sport> getSports() {
        return sportRepository.findAll();
    }


    public void updateSport(SportDTO sportDTO) {
        Sport sport= sportRepository.findSportById(sportDTO.getSport_admin_admin_id());
        if(sport==null){
            throw  new ApiException("Id not found");
        }
        sport.setName(sportDTO.getName());
        sportRepository.save(sport);
    }

    public void deleteSport(Integer id) {
        Sport sport= sportRepository.findSportById(id);
        if (sport == null) {
            throw  new ApiException("Id not found");
        }
        sportRepository.delete(sport);
    }
}
