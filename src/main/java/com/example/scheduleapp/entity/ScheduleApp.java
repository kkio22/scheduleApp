package com.example.scheduleapp.entity;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleApp {
    private Long schedule_id;
    private String task;
    private String author_name;
    private String password; 
    private LocalDateTime created_date;  
    private LocalDateTime update_date;

   


    public ScheduleApp(String task, String author_name, String password, LocalDateTime created_date, LocalDateTime update_date) {
        this.task = task;
        this.author_name = author_name;
        this.password = password;
        this.created_date = created_date;
        this.update_date = update_date;
    }



   public void updateTask(ScheduleAppRequestDto requestDto) {
        this.task = requestDto.getTask();
        this.author_name=requestDto.getAuthor_name();

    }
}
