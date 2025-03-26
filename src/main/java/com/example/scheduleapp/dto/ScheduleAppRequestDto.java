package com.example.scheduleapp.dto;

import com.example.scheduleapp.entity.ScheduleApp;
import lombok.Getter;


import java.time.LocalDateTime;


@Getter

public class ScheduleAppRequestDto {
    private String task;
    private String author_name;
    private String password; //비밀번호는 String으로 처리함(보안상의 문제 때문임)
    private LocalDateTime created_date;  //날짜나 시간은 LocalDateTime으로 자료형을 적어야 함
    private LocalDateTime update_date;



}


