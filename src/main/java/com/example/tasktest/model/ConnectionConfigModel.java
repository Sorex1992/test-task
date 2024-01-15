package com.example.tasktest.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Data
@Component
public class ConnectionConfigModel {
    private UUID id;
    private String name;
    private String url;
    private String username;
    private String password;
    private String table;
    private Map<String, String> mapping;
}
