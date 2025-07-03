package com.jkh.Example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DashboardConfig {

    // @Primary 어노테이션은 MemoConfig에 있으므로 여기서는 사용하지 않습니다.
    // 명시적인 빈 이름을 사용하여 MemoConfig의 빈들과 충돌을 피합니다.
    @Bean(name = "dashboardDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dashboard-db") // application.yml의 dashboard-db 설정과 매핑
    public DataSource dashboardDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dashboardJdbcTemplate")
    public JdbcTemplate dashboardJdbcTemplate(@Qualifier("dashboardDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "dashboardTransactionManager")
    public PlatformTransactionManager dashboardTransactionManager(@Qualifier("dashboardDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}