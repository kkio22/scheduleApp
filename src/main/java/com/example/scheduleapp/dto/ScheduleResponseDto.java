package com.example.scheduleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor

public class ScheduleResponseDto {
    private long id;
    private String todo;
    private String name;
    private String password;
    private LocalDateTime create;
    private LocalDateTime update;
}
