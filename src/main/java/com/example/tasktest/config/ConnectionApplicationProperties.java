package com.example.tasktest.config;

import com.example.tasktest.model.ConnectionConfigModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class ConnectionApplicationProperties {
    private List<ConnectionConfigModel> connectionConfigModels;
}
