package com.example.scheduleapp.dto;

import com.example.scheduleapp.entity.ScheduleApp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleAppResponseDto {
    private Long schedule_id;
    private String task;
    private String author_name;
    private String password;
    private LocalDateTime created_date;
    private LocalDateTime update_date;

    public ScheduleAppResponseDto(ScheduleApp scheduleApp) {
        this.schedule_id = scheduleApp.getSchedule_id();
        this.task =scheduleApp.getTask();
        this.author_name =scheduleApp.getAuthor_name();
        this.password =scheduleApp.getPassword();
        this.created_date =scheduleApp.getCreated_date();
        this.update_date =scheduleApp.getUpdate_date();
    }

}
//클라이언트에게 응답하는 클래스로 클라이언트가 어떤 데이터를 원할지 모른다. entity를 원할 수도 있기에 전체 다 적어야 함.
