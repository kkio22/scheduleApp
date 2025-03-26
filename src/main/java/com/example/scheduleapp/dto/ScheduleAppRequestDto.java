package com.example.scheduleapp.dto;

import com.example.scheduleapp.entity.ScheduleApp;
import lombok.Getter;


import java.time.LocalDateTime;


@Getter

public class ScheduleAppRequestDto {
    private String task;
    private String author_name;
    private String password; 
    private LocalDateTime created_date;  
    private LocalDateTime update_date;



}


