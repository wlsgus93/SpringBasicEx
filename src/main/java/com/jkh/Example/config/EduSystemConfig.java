package com.jkh.Example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class EduSystemConfig {

    @Bean(name = "eduSystemDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.edusystem-db")
    public DataSource eduSystemDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "eduSystemJdbcTemplate")
    public JdbcTemplate eduSystemJdbcTemplate(@Qualifier("eduSystemDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "eduSystemTransactionManager")
    public DataSourceTransactionManager eduSyst1emTransactionManager(@Qualifier("eduSystemDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}