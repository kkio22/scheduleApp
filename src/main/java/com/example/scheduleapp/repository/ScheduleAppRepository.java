package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;

import java.util.List;

public interface ScheduleAppRepository {
 ScheduleAppResponseDto saveSchedule(ScheduleAppRequestDto dto);

 //List<ScheduleAppResponseDto> findAllSchedules();

 //ScheduleApp findScheduleById(Long scheduleId);


}
