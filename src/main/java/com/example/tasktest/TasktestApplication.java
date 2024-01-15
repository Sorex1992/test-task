package com.example.tasktest;

import com.example.tasktest.config.ConnectionApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(
        ConnectionApplicationProperties.class
)
public class TasktestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasktestApplication.class, args);
    }
}
