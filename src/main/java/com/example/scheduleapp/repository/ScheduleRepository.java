package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveRepository(Schedule schedule);

    List<ScheduleResponseDto> checkRepository();

    Schedule oneCheckRepository(long id);

    int modifyRepository(long id, Schedule schedule);

    int deleteRepository(long id);

    String matchPassword(long id);

}