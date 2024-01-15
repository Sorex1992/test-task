package com.example.tasktest.service;

import com.example.tasktest.config.ConnectionApplicationProperties;
import com.example.tasktest.model.ConnectionConfigModel;
import com.example.tasktest.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final List<JdbcTemplate> jdbcTemplates;
    private final ConnectionApplicationProperties connectionApplicationProperties;

    public List<User> getAllUsers(String nameFilter, String usernameFilter, String surnameFilter) {
        List<User> allUsers = new ArrayList<>();

        for (int i = 0; i < jdbcTemplates.size(); i++) {
            JdbcTemplate jdbcTemplate = jdbcTemplates.get(i);
            List<User> usersFromDatabase = getUsersFromDatabase(jdbcTemplate,
                    connectionApplicationProperties.getConnectionConfigModels().get(i),
                    nameFilter, usernameFilter, surnameFilter);
            allUsers.addAll(usersFromDatabase);
        }
        return allUsers;
    }

    private List<User> getUsersFromDatabase(
            JdbcTemplate jdbcTemplate, ConnectionConfigModel configModel,
            String nameFilter, String usernameFilter, String surnameFilter) {

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ").append(configModel.getTable());
        List<Object> parameters = new ArrayList<>();

        filterCondition(queryBuilder, parameters, configModel, "name", nameFilter);
        filterCondition(queryBuilder, parameters, configModel, "username", usernameFilter);
        filterCondition(queryBuilder, parameters, configModel, "surname", surnameFilter);

        return jdbcTemplate.query(queryBuilder.toString(), parameters.toArray(), mapToUser(configModel));
    }

    private void filterCondition(StringBuilder queryBuilder, List<Object> parameters,
                                       ConnectionConfigModel configModel, String column, String filter) {
        if (StringUtils.hasText(filter)) {
            if (parameters.isEmpty()) {
                queryBuilder.append(" WHERE ");
            } else {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(configModel.getMapping().get(column)).append(" = ?");
            parameters.add(filter);
        }
    }

    private RowMapper<User> mapToUser(ConnectionConfigModel configModel) {
        return (rs, rowNum) -> User.builder()
                .id(UUID.fromString(rs.getString(configModel.getMapping().get("id"))))
                .username(rs.getString(configModel.getMapping().get("username")))
                .name(rs.getString(configModel.getMapping().get("name")))
                .surname(rs.getString(configModel.getMapping().get("surname")))
                .build();
    }
}
