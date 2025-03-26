package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import com.example.scheduleapp.repository.ScheduleAppRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ScheduleAppServiceImpl implements ScheduleAppService {

    private final ScheduleAppRepository scheduleAppRepository;

    public ScheduleAppServiceImpl(ScheduleAppRepository scheduleAppRepository) {
        this.scheduleAppRepository = scheduleAppRepository;
    }
   


    @Override
    public ScheduleAppResponseDto scheduleSave(ScheduleAppRequestDto dto) {
        ScheduleAppResponseDto savedSchedule = scheduleAppRepository.saveSchedule(dto);
        
        return savedSchedule;
    }
    

   @Override
   public List<ScheduleAppResponseDto> findAllSchedule() {
       List<ScheduleAppResponseDto> allSchedule = scheduleAppRepository.findAllSchedules();

       return allSchedule;
   }
   

   @Override
   public ScheduleAppResponseDto findScheduleById(Long scheduleId) {
       ScheduleApp scheduleApp = scheduleAppRepository.findScheduleById(scheduleId);
       if (scheduleApp == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + scheduleId);
       }
       return new ScheduleAppResponseDto(scheduleApp);
   } 

   @Override
   public ScheduleAppResponseDto updateTask(Long scheduleId, String task, String author_name) {
       ScheduleApp scheduleApp = scheduleAppRepository.findScheduleById(scheduleId);

       LocalDateTime now = LocalDateTime.now();

   }

   @Override
   public void deleteSchedule(Long scheduleId) {

   }

}



