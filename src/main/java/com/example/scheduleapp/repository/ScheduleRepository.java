package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveRepository(Schedule schedule);

    List<ScheduleResponseDto> checkRepository();

   Schedule Schedule oneCheckRepository(long id);

    int deleteRepository(long id);

    String matchPassword(long id);
}
