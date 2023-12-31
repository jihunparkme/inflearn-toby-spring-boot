package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * application.properties 파일 등록은 스프링 프레임워크 기본 동작 방식은 아니고 스프링 부트 초기화 과정에서 추가해주는 것.
 * 자동으로 포함이 안 되므로
 * @TestPropertySource("classpath:/application.properties")
 * 로 properties 정보를 읽어오도록 설정
 */
@HellobootTest
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
