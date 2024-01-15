package com.example.tasktest.controller;

import com.example.tasktest.model.User;
import com.example.tasktest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get users", description = "Get a list of users with optional filters")
    @ApiResponse(responseCode = "200", description = "Successfully get users")
    @GetMapping
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(name = "name", required = false) String nameFilter,
            @RequestParam(name = "username", required = false) String usernameFilter,
            @RequestParam(name = "surname", required = false) String surnameFilter) {
        return ResponseEntity.ok(userService.getAllUsers(nameFilter, usernameFilter, surnameFilter));
    }
}
