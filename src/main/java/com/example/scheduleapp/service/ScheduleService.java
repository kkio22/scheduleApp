package com.example.scheduleapp.service;


import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveService(ScheduleRequestDto dto);

    List<ScheduleResponseDto> checkService();

    List<ScheduleResponseDto> checkReService(String name, LocalDateTime modifiedDateTime);

    ScheduleResponseDto oneCheckService(long id);

    ScheduleResponseDto modifyService(long id, ScheduleRequestDto dto);

    void deleteService(long id, ScheduleRequestDto dto);

}

