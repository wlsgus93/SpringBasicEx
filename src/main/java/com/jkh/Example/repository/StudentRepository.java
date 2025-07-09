package com.jkh.Example.repository;

import com.jkh.Example.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(@Qualifier("eduSystemJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Student> mapper = (resultSet, rowNum) -> Student.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .score(resultSet.getInt("score"))
            .teacherId(resultSet.getInt("teacher_id"))
            .teacherName(resultSet.getString("teacher_name"))
            .build();

    public List<Student> findAll() {
        String sql = "SELECT s.id, s.name, s.score, s.teacher_id, t.name AS teacher_name " +
                "FROM student s LEFT JOIN teacher t ON s.teacher_id = t.id " +
                "ORDER BY s.id";

        return jdbcTemplate.query(sql, mapper);
    }

    public Student findById(int id) {
        String sql = "SELECT s.id, s.name, s.score, s.teacher_id, t.name AS teacher_name " +
                "FROM student s LEFT JOIN teacher t ON s.teacher_id = t.id " +
                "WHERE s.id = ?";

        return jdbcTemplate.queryForObject(sql, mapper, id);
    }

    public int save(Student student) {
        return jdbcTemplate.update(
                "INSERT INTO student (name, score, teacher_id) VALUES (?, ?, ?)",
                student.getName(), student.getScore(), student.getTeacherId()
        );
    }

    public int update(Student student) {
        return jdbcTemplate.update(
                "UPDATE student SET name = ?, score = ?, teacher_id = ? WHERE id = ?",
                student.getName(), student.getScore(), student.getTeacherId(), student.getId()
        );
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM student WHERE id = ?", id);
    }
}