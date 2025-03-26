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
    private String password; //비밀번호는 String으로 처리함(보안상의 문제 때문임)
    private LocalDateTime created_date;  //날짜나 시간은 LocalDateTime으로 자료형을 적어야 함
    private LocalDateTime update_date;

    //DB랑 연결되서 Entity로 DB의 데이터를 전제 다 받는 클래스임


    public ScheduleApp(String task, String author_name, String password, LocalDateTime created_date, LocalDateTime update_date) {
        this.task = task;
        this.author_name = author_name;
        this.password = password;
        this.created_date = created_date;
        this.update_date = update_date;
    }
    //service에서 비지니스 로직에서 dto 데이터를 entity로 변경하기 위해 생성자 작성


   public void updateTask(ScheduleAppRequestDto requestDto) {
        this.task = requestDto.getTask();
        this.author_name=requestDto.getAuthor_name();

    }
}
