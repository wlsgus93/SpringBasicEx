package com.jkh.Example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration // Spring 설정 클래스임을 나타냅니다.
@EnableTransactionManagement // Spring의 선언적 트랜잭션 관리를 활성화합니다.
public class MemoConfig {

    @Primary // 여러 DataSource 빈 중 이 DataSource가 기본으로 사용되도록 지정합니다.
    @Bean(name = "memoDataSource") // DataSource 빈의 이름을 "memoDataSource"로 지정합니다.
    @ConfigurationProperties(prefix = "spring.datasource.memo-app") // application.yml의 'spring.datasource.memo-app' 속성과 매핑합니다.
    public DataSource memoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary // 여러 JdbcTemplate 빈 중 이 JdbcTemplate이 기본으로 사용되도록 지정합니다.
    @Bean(name = "memoJdbcTemplate") // JdbcTemplate 빈의 이름을 "memoJdbcTemplate"로 지정합니다.
    public JdbcTemplate memoJdbcTemplate(@Qualifier("memoDataSource") DataSource dataSource) {
        // "memoDataSource" 빈을 주입받아 JdbcTemplate을 생성합니다.
        return new JdbcTemplate(dataSource);
    }

    @Primary // 여러 PlatformTransactionManager 빈 중 이 매니저가 기본으로 사용되도록 지정합니다.
    @Bean(name = "memoTransactionManager") // 트랜잭션 매니저 빈의 이름을 "memoTransactionManager"로 지정합니다.
    public PlatformTransactionManager memoTransactionManager(@Qualifier("memoDataSource") DataSource dataSource) {
        // "memoDataSource" 빈을 주입받아 DataSourceTransactionManager를 생성합니다.
        return new DataSourceTransactionManager(dataSource);
    }
}