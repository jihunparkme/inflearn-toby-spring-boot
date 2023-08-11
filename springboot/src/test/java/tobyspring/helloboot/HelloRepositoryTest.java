package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
 * - 웹 환경 세팅은 제외
 */
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class HelloRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("Aaron")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("Aaron")).isEqualTo(0);

        helloRepository.increaseCount("Aaron");
        assertThat(helloRepository.countOf("Aaron")).isEqualTo(1);

        helloRepository.increaseCount("Aaron");
        assertThat(helloRepository.countOf("Aaron")).isEqualTo(2);
    }
}