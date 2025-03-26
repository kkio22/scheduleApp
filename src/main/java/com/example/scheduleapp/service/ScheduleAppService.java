package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;

import java.util.List;

public interface ScheduleAppService {
    ScheduleAppResponseDto scheduleSave(ScheduleAppRequestDto dto);
    
   List<ScheduleAppResponseDto> findAllSchedule();

   ScheduleAppResponseDto findScheduleById(Long scheduleId);

   ScheduleAppResponseDto updateTask(Long scheduleId, String task, String author_name);

   void deleteSchedule(Long scheduleId);


}
