package com.micronaut.controllers;

import com.micronaut.etities.User;
import com.micronaut.repositories.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;

import java.sql.SQLException;

@Controller("/logins")
public class LoginsController {

    private final UserRepository userRepository;

    public LoginsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get("/{login}")
    @Secured("isAuthenticated()")
    public HttpResponse<User> findByLogin(@PathVariable String login) throws SQLException {
        var user = userRepository.findByLogin(login);
        System.out.println("Dados" + login);
        return HttpResponse.ok(user);
    }
}
