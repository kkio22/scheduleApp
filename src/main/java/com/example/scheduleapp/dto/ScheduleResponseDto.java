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

//    public ScheduleResponseDto(long id, String todo, String name, String password, LocalDateTime create, LocalDateTime update){
//        this.id=id;
//        this.todo=todo;
//        this.name=name;
//        this.password=password;
//        this.create=create;
//        this.update=update;
//    }

}
