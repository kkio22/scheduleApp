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
    private final ScheduleService scheduleService; //응답에 필요

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule (
            ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.saveService(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> checkSchedule (
            @RequestParam(required=false)String name,
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime update
    ){ //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)이거 찾아보기
        return scheduleService.checkService(); //왜 얘는 new 안 해요?
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> oneCheckSchedule (
            @PathVariable long id
    ){
        return new ResponseEntity<ScheduleResponseDto>(scheduleService.oneCheckService(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable long id

    ){
        scheduleService.deleteService(id);

        return ResponseEntity.ok("삭제 성공");

    }

}
