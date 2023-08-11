package tobyspring.helloboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    void insertAndQuery() {
        jdbcTemplate.update("insert into hello values(?, ?)", "Aaron", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "Spring", 3);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        Assertions.assertEquals(2, count);
    }

    @Test
    void insertAndQuery_check_transaction() {
        jdbcTemplate.update("insert into hello values(?, ?)", "Aaron", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "Spring", 3);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        Assertions.assertEquals(2, count);
    }
}
