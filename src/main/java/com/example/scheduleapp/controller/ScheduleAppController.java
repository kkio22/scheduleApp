package com.example.scheduleapp.controller;


import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.service.ScheduleAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shcedules")//Prefix
public class ScheduleAppController {
    private final ScheduleAppService scheduleAppService;
    

    public ScheduleAppController(ScheduleAppService scheduleAppService) {
        this.scheduleAppService = scheduleAppService;
    }
   
    @PostMapping 
    
    public ResponseEntity<ScheduleAppResponseDto> createSchedule(@RequestBody ScheduleAppRequestDto dto) {
        return new ResponseEntity<>(scheduleAppService.scheduleSave(dto),HttpStatus.CREATED);
         }

   @GetMapping
   public List<ScheduleAppResponseDto> findAllSchedule() {
       return scheduleAppService.findAllSchedule() ;
   }// scheduleAppService.findAllSchedule() 

   @GetMapping("/{scheduleId}")
   public ResponseEntity<ScheduleAppResponseDto> findScheduleById(@PathVariable Long scheduleId) {
       return new ResponseEntity<>(scheduleAppService.findScheduleById(scheduleId),HttpStatus.OK);
   }

   @PutMapping("/{scheduleId}")
   public ResponseEntity<ScheduleAppResponseDto> updateSchedule(
           @PathVariable Long scheduleId,
           @RequestBody ScheduleAppRequestDto requestDto
   ) {
       return new ResponseEntity<>(scheduleAppService.updateSchedule(scheduleId, requestDto),HttpStatus.OK);
   }

   @PatchMapping("/{scheduleId}")
   public ResponseEntity<ScheduleAppResponseDto> updateTask(
           @PathVariable Long scheduleId,
           @RequestBody ScheduleAppRequestDto requestDto
   ) {
       return new ResponseEntity<>(scheduleAppService.updateTask(scheduleId, requestDto.getTask(), requestDto.getAuthor_name()), HttpStatus.OK);
   }

   @DeleteMapping("/{scheduleId}")
   public ResponseEntity<ScheduleAppResponseDto> deleteSchedule(
           @PathVariable Long scheduleId
   ) {
       scheduleAppService.deleteSchedule(scheduleId);
       return new ResponseEntity<>(HttpStatus.OK);

   }


}
