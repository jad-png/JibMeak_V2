package com.taxist.JibMeakV2.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.taxist.JibMeak")
@EnableJpaRepositories(basePackages = "com.taxist.JibMeak.repository")
@EnableTransactionManagement
public class AppConfig {
}
