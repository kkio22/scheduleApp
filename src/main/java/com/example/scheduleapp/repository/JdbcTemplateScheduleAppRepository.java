package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleAppRepository implements ScheduleAppRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleAppRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleAppResponseDto saveSchedule(ScheduleAppRequestDto dto) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); // jdbcTemplate로 insert문을 만들어볼게
        jdbcInsert.withTableName("scheduleapp").usingGeneratedKeyColumns("schedule_id");
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("task", dto.getTask());
        parameters.put("author_name", dto.getAuthor_name());
        parameters.put("password", dto.getPassword());
        parameters.put("created_date", now);
        parameters.put("updated_date", now);

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleAppResponseDto(key.longValue(), dto.getTask(), dto.getAuthor_name(), dto.getPassword(), now, now);
    }


//    @Override
//    public List<ScheduleAppResponseDto> findAllSchedule() {
//        return jdbcTemplate.query("select * from memo", scheduleAppRowMapper());
//    }
//
//    private RowMapper<ScheduleAppResponseDto> scheduleAppRowMapper() {
//        return new RowMapper<ScheduleAppResponseDto>() {
//            @Override
//            public ScheduleAppResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new ScheduleAppResponseDto(
//                        rs.getLong("schedule_id"),
//                        rs.getString(""),
//                        rs.getString("")
//                );
//            }
//
//        };
//    }
//
//    @Override
//    public ScheduleApp findMemoById(Long id) {
//        return null;
//    }
//
//    @Override
//    public void deleteSchedule(Long id) {
//
//    }
}