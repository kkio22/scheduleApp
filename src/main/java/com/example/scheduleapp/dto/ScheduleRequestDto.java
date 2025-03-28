package com.example.scheduleapp.dto;


import lombok.Getter;


import java.time.LocalDateTime;

@Getter

public class ScheduleRequestDto {
    private long id;
    private String todo;
    private String name;
    private String password;
    private LocalDateTime create;
    private LocalDateTime update;
}
