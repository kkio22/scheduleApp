package com.example.scheduleapp.controller;


import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @RequestBody ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.saveService(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> checkSchedule() {
        return scheduleService.checkService();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> oneCheckSchedule(
            @PathVariable long id
    ) {
        return new ResponseEntity<>(scheduleService.oneCheckService(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> modifySchedule(
            @PathVariable long id,
            @RequestBody ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.modifyService(id, dto ), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable long id,
            @RequestBody ScheduleRequestDto dto

    ) {
        scheduleService.deleteService(id, dto);

        return ResponseEntity.ok("일정이 삭제되었습니다.");

    }

}

