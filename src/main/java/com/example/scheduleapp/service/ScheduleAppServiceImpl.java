package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import com.example.scheduleapp.repository.ScheduleAppRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ScheduleAppServiceImpl implements ScheduleAppService {

    private final ScheduleAppRepository scheduleAppRepository;

    public ScheduleAppServiceImpl(ScheduleAppRepository scheduleAppRepository) {
        this.scheduleAppRepository = scheduleAppRepository;
    }
    //Repository랑 연결하기 위해 작성 (di)


    @Override
    public ScheduleAppResponseDto scheduleSave(ScheduleAppRequestDto dto) {
//        LocalDateTime now = LocalDateTime.now(); //현재 날짜&시간 받아오는 객체 생성
//        ScheduleApp scheduleApp = new ScheduleApp(
//                dto.getTask(),
//                dto.getAuthor_name(),
//                dto.getPassword(),
//                now, //created_date로 감
//                now  //updated_date로 감
//        );
        //dto에 있는 데이터 보내서 entity로 변환 -> get 사용
        ScheduleAppResponseDto savedSchedule = scheduleAppRepository.saveSchedule(dto);
        //entity로 변경한 데이터를 레파지토리로 보내서 거기서 처리해서 다시 가져오기
        return savedSchedule;
        //이 반환값이 scheduleAppService.scheduleSave(dto)값이다. 근데 이게 클래스로 반환되니 객체를 생성해서
    }
    // 각 일정을 post하면 자동으로 식별자의 수가 증가하는 코드 작성

//    @Override
//    public List<ScheduleAppResponseDto> findAllSchedule() {
//        List<ScheduleAppResponseDto> allSchedule = scheduleAppRepository.findAllSchedules();
//
//        return allSchedule;
//    }
//    //전체 목록 리스트로 담아서 전송
//
//    @Override
//    public ScheduleAppResponseDto findScheduleById(Long scheduleId) {
//        ScheduleApp scheduleApp = scheduleAppRepository.findScheduleById(scheduleId);
//        if (scheduleApp == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + scheduleId);
//        }
//        return new ScheduleAppResponseDto(scheduleApp);
//    } // 단일 조회 목록
//
//    @Override
//    public ScheduleAppResponseDto updateTask(Long scheduleId, String task, String author_name) {
//        ScheduleApp scheduleApp = scheduleAppRepository.findScheduleById(scheduleId);
//
//        LocalDateTime now = LocalDateTime.now();
//
//    }

//    @Override
//    public void deleteSchedule(Long scheduleId) {
//
//    }

}



