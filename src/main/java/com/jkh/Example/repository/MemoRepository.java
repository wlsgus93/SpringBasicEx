package com.jkh.Example.repository;

import com.jkh.Example.model.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Autowired
    private Environment env;

    @Component
    public class EnvCheck implements CommandLineRunner {

        @Override
        public void run(String... args) {
            System.out.println("🌎 System.getenv(DB_HOST): " + System.getenv("DB_HOST"));
            System.out.println("🌎 System.getenv(DB_PORT): " + System.getenv("DB_PORT"));
        }
    }


    private final RowMapper<Memo> memoRowMapper = (resultSet, rowNum) ->
            new Memo(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content")
            );

    public List<Memo> findAll() {
        System.out.println("APP_NAME: " + env.getProperty("APP_NAME"));
        return jdbcTemplate.query(
                "SELECT * FROM memo ORDER BY id DESC",
                memoRowMapper
        );
    }
    public Memo findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM memo WHERE id = ?",
                memoRowMapper,
                id
        );
    }

    public void save(String title, String content) {
        jdbcTemplate.update("INSERT INTO memo (title, content) VALUES (?, ?)", title, content);
    }
    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM memo WHERE id = ?",
                id
        );
    }
    public void update(int id, String title, String content) {
        jdbcTemplate.update(
                "UPDATE memo SET title = ?, content = ? WHERE id = ?",
                title, content, id
        );
    }
}
