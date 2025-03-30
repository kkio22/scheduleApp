package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service

public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;


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
    public List<ScheduleResponseDto> checkReService(String name, LocalDateTime modifiedDateTime) {
        if(name==null && modifiedDateTime==null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This request wrong");
        }

        if(name==null && modifiedDateTime !=null){
            return scheduleRepository.findScheduleByDate(modifiedDateTime);
        }
        else if(modifiedDateTime==null && name !=null){
            return scheduleRepository.findScheduleByName(name);
        }

        return scheduleRepository.checkReRepository(name, modifiedDateTime);
    }


    @Override
    public ScheduleResponseDto oneCheckService(long id) {

        Schedule schedule = scheduleRepository.oneCheckRepository(id);
        return new ScheduleResponseDto(schedule.getId(), schedule.getTodo(), schedule.getName(), schedule.getPassword(), schedule.getCreate(), schedule.getUpdate());
    }

    @Override
    public ScheduleResponseDto modifyService(long id, ScheduleRequestDto dto) {

        if (dto.getPassword().equals(scheduleRepository.matchPassword(id))) {

            Schedule schedule = new Schedule(dto.getTodo(), dto.getName());

            if (dto.getTodo() == null || dto.getName() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The todo and name are required values.");
            }

            int modifyRow = scheduleRepository.modifyRepository(id, schedule);

            if (modifyRow == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
            }


        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is wrong");
        }

        Schedule checkedSchedule = scheduleRepository.oneCheckRepository(id);


        return new ScheduleResponseDto(id, checkedSchedule.getTodo(), checkedSchedule.getName(), checkedSchedule.getCreate(), checkedSchedule.getUpdate());


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