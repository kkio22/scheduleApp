package com.example.scheduleapp.service;


import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveService(ScheduleRequestDto dto);

    List<ScheduleResponseDto> checkService ();

    ScheduleResponseDto oneCheckService (long id);

    void deleteService (long id);

}
