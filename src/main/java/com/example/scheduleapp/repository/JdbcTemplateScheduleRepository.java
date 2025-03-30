package com.example.scheduleapp.repository;


import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository

public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;


    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ScheduleResponseDto saveRepository(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("scheduleapp").usingGeneratedKeyColumns("id");
        LocalDateTime now = LocalDateTime.now();//static
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", schedule.getTodo());
        parameters.put("name", schedule.getName());
        parameters.put("password", schedule.getPassword());
        parameters.put("created", now);
        parameters.put("updated", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getTodo(), schedule.getName(), schedule.getPassword(), now, now);


    }

    @Override
    public List<ScheduleResponseDto> checkRepository() {
        return jdbcTemplate.query("select * from scheduleapp order by updated desc ", scheduleRowMapper()); //jdbcTemplate.query jdbcTemplate 매서드/sql문 넣고,
    }




    private RowMapper<ScheduleResponseDto> scheduleRowMapper() { //ScheduleResponse 객체에 데이터베이스 객체를 매핑해서 RowMapper를 이용해 반환하겠다. //물어봐야겠다.
        return new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("updated").toLocalDateTime()
                );

            }
        };

    }
    @Override
    public List<ScheduleResponseDto> checkReRepository(String name, LocalDateTime modifiedDateTime) {
        return jdbcTemplate.query("select * from scheduleapp where name =? AND DATE(updated)=?", new Object[]{name, java.sql.Date.valueOf(modifiedDateTime.toLocalDate())}, scheduleRowMapperV3());
    }


    private RowMapper<ScheduleResponseDto> scheduleRowMapperV3(){
        return new RowMapper<ScheduleResponseDto>(){

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("updated").toLocalDateTime()
                );
            }
        };
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByDate(LocalDateTime modifiedDateTime) {
       return jdbcTemplate.query("select * from scheduleapp where DATE(updated)=?",  new Object[]{java.sql.Date.valueOf(modifiedDateTime.toLocalDate())}, scheduleRowMapperV4());
    }



    private RowMapper<ScheduleResponseDto> scheduleRowMapperV4(){
        return new RowMapper<ScheduleResponseDto>(){

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("updated").toLocalDateTime()
                );
            }
        };
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByName(String name) {
        return jdbcTemplate.query("select * from scheduleapp where name=?", new Object[]{name}, scheduleRowMapperV5());
    }



    private RowMapper<ScheduleResponseDto> scheduleRowMapperV5(){
        return new RowMapper<ScheduleResponseDto>(){

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("updated").toLocalDateTime()
                );
            }
        };
    }


    @Override
    public Schedule oneCheckRepository(long id) {
        List<Schedule> result = jdbcTemplate.query("select * from scheduleapp where id= ?", ScheduleRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }



    private RowMapper<Schedule> ScheduleRowMapperV2() {
        return new RowMapper<Schedule>() {

            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("updated").toLocalDateTime()
                );
            }
        };
    }


    @Override
    public int modifyRepository(long id, Schedule schedule) {
        return jdbcTemplate.update("update  scheduleapp set todo=? , name =? where id= ?" ,schedule.getTodo(), schedule.getName(), id);

    }

    @Override
    public int deleteRepository(long id) {
        return jdbcTemplate.update("delete from scheduleapp where id=?", id);
    }

    @Override
    public String matchPassword(long id) {
        return jdbcTemplate.queryForObject("select password from scheduleapp where id= ?", String.class, id);
    }

}

