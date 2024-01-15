package com.example.tasktest.config;

import com.example.tasktest.model.ConnectionConfigModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JdbcTemplateConfiguration {

    @Bean
    public List<JdbcTemplate> jdbcTemplates(ConnectionApplicationProperties connectionApplicationProperties) {
        List<JdbcTemplate> jdbcTemplates = new ArrayList<>();

        for (ConnectionConfigModel config : connectionApplicationProperties.getConnectionConfigModels()) {
            JdbcTemplate jdbcTemplate = createJdbcTemplate(config);
            jdbcTemplates.add(jdbcTemplate);
        }
        return jdbcTemplates;
    }

    private JdbcTemplate createJdbcTemplate(ConnectionConfigModel connectionConfigModel) {
        return new JdbcTemplate(new DriverManagerDataSource(
                connectionConfigModel.getUrl(),
                connectionConfigModel.getUsername(),
                connectionConfigModel.getPassword())
        );
    }
}
