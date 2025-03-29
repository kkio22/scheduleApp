package com.example.scheduleapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

    @Getter
    @AllArgsConstructor

    public class Schedule {
        private long id;
        private String todo;
        private String name;
        private String password;
        private LocalDateTime create;
        private LocalDateTime update;

        public Schedule(String todo, String name) {
        this.todo = todo;
        this.name = name;

    }

    }


