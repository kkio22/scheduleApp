package com.example.scheduleapp.controller;


import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.service.ScheduleAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shcedules")//Prefix
public class ScheduleAppController {
    private final ScheduleAppService scheduleAppService;
    //final은 ScheduleAppService 클래스에서 넘어온 데이터르 변경시키지 않기 위해서 사용

    public ScheduleAppController(ScheduleAppService scheduleAppService) {
        this.scheduleAppService = scheduleAppService;
    }
    // spring framwork에서는 의존성(di)를 통해 클래스를 관리합니다. 그래서 service와 controller는 서로 데이터를 주고 받아서 클라이언트에게 응답을 해야하기 때문에  생성자를 통해서
    // service 클래스가 controller 클래스로 들어와서 사용 가능하게 하는 코드가 무조건 있어야 한다.

    @PostMapping //목록 추가
    // http message에 post가 있으면 여기로 옴(가장 처음 들어오는 곳)
    public ResponseEntity<ScheduleAppResponseDto> createSchedule(@RequestBody ScheduleAppRequestDto dto) {
        // @RequestBody: JSON -> DTO 객체로 변환해주는 역할, DTO 클래스는 직접 사용하는게 아니라 JSON 데이터로 변환하기 위해 컨트롤러에서 매개변수 타입으로 사용하려고 만듬
        return new ResponseEntity<>(scheduleAppService.scheduleSave(dto),HttpStatus.CREATED);
        // 비즈니스 로직에서 처리한 응답 메세지에 들어갈 내용 -> 뒤에 이걸 어떤 형태로 반환할지는 우리가 생각 안해도 됨 프로그램이 해줌.
    }


//    @GetMapping//전체 목록 조회
//    public List<ScheduleAppResponseDto> findAllSchedule() {
//        return scheduleAppService.findAllSchedule() ;
//    }// scheduleAppService.findAllSchedule() 이 매서드를 통해 받아온 값이 List 형태인 것임
//
//    @GetMapping("/{scheduleId}")//단일 목록 조회
//    public ResponseEntity<ScheduleAppResponseDto> findScheduleById(@PathVariable Long scheduleId) {
//        return new ResponseEntity<>(scheduleAppService.findScheduleById(scheduleId),HttpStatus.OK);
//    }

//    @PutMapping("/{scheduleId}")//목록 수정
//    public ResponseEntity<ScheduleAppResponseDto> updateSchedule(
//            @PathVariable Long scheduleId,
//            @RequestBody ScheduleAppRequestDto requestDto
//    ) {
//        return new ResponseEntity<>(scheduleAppService.updateSchedule(scheduleId, requestDto),HttpStatus.OK);
//    }//내가 뭘 수정할 부분에 대한 식별자, 수정할 부분

//    @PatchMapping("/{scheduleId}")//목록 일부 수정
//    public ResponseEntity<ScheduleAppResponseDto> updateTask(
//            @PathVariable Long scheduleId,
//            @RequestBody ScheduleAppRequestDto requestDto
//    ) {
//        return new ResponseEntity<>(scheduleAppService.updateTask(scheduleId, requestDto.getTask(), requestDto.getAuthor_name()), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{scheduleId}")
//    public ResponseEntity<ScheduleAppResponseDto> deleteSchedule(
//            @PathVariable Long scheduleId
//    ) {
//        scheduleAppService.deleteSchedule(scheduleId);
//        return new ResponseEntity<>(HttpStatus.OK);
//        // 삭제일 때는 비지니스 로직에서 처리한 데이터를 보낼 필요가 없으니 따로 빼서 수행하고 상태코드만 생성자에 보낸다.
//    }


}
