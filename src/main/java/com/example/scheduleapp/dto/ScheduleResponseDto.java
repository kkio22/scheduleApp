package com.example.scheduleapp.dto;

import com.example.scheduleapp.entity.Schedule;
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



    public ScheduleResponseDto(Long id, String todo, String name, LocalDateTime create, LocalDateTime update){

        this.id=id;
        this.todo=todo;
        this.name=name;
        this.create=create;
        this.update=update;
    }

}
