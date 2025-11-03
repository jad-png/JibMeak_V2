package com.taxist.JibMeakV2.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.taxist.JibMeak")
@EnableJpaRepositories(basePackages = "com.taxist.JibMeak.repository")
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public DataSource dataSource() {

        // set up connection details
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:jibMeak_db;DB_CLOSE_DELAY=1;DB_CLOSE_ON_EXIT=FALSE");
        config.setUsername("sa");
        config.setPassword("");

        // pool settings for Hikamaru
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(3000);

        return new HikariDataSource(config);

    }

    
}
