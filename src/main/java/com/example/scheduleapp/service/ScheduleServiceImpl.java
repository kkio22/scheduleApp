package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service

public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository; //부모클래스를 쓰면 확장성과 DI가 좋음


    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveService(ScheduleRequestDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule(dto.getId(), dto.getTodo(), dto.getName(),
                dto.getPassword(), now, now);
        ScheduleResponseDto saveSchedule = scheduleRepository.saveRepository(schedule);
        return saveSchedule;
    }


    @Override
    public List<ScheduleResponseDto> checkService() {

        return scheduleRepository.checkRepository();
    }


    @Override
    public ScheduleResponseDto oneCheckService(long id) {

        Schedule schedule = scheduleRepository.oneCheckRepository(id);
        return new ScheduleResponseDto(schedule.getId(), schedule.getTodo(), schedule.getName(), schedule.getPassword(), schedule.getCreate(), schedule.getUpdate());
    }

    @Override
    public ScheduleResponseDto modifyService(long id, ScheduleRequestDto dto) {


    }

    @Override
    public void deleteService(long id, ScheduleRequestDto dto) {
        if (dto.getPassword().equals(scheduleRepository.matchPassword(id))) {
            int deleteRow = scheduleRepository.deleteRepository(id);

            if (deleteRow == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Do not exist id" + id);
            }
        }

    }


}