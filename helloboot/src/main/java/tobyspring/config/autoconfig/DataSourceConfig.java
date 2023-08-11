package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * DataSourceConfig 는 JdbcOperations 클래스 존재를 확인해서 등록
 */
@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@EnableTransactionManagement // 트랜잭션 기능(@Transactional)을 가능하게 해주는 구성용 애노테이션
public class DataSourceConfig {
    /**
     * HikariDataSource 클래스가 존재할 경우 HikariDataSource Bean 등록
     */
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        // 프로퍼티 정의
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    /**
     * 앞에서 HikariDataSource 가 등록되면 빈을 만들지 않도록 ConditionalOnMissingBean 선언
     * HikariDataSource Bean 이 등록되지 않았을 경우 SimpleDriverDataSource 등록
     */
    @Bean
    @ConditionalOnMissingBean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        /**
         * SimpleDriverDataSource
         * - 클래스명 그대로 단순하게 만들어진 클래스로
         * - connection pool 이 없고 매번 새로운 connection 을 생성하므로 실무에서는 사용 X
         */
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        // 프로퍼티 정의
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    /**
     * @ConditionalOnSingleCandidate(DataSource.class)
     * - DataSource Bean 이 한 개만 등록이 되어 있다면 해당 DataSource 를 사용
     */
    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }
}
