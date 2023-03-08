package com.micronaut.controllers;

import com.micronaut.etities.User;
import com.micronaut.repositories.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.util.List;

@Controller("/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  @Get
  public List<User> findAll() {
    return userRepository.findALl();
  }

  @Post
  public HttpResponse<String> registerUser(@Body User user) {
    userRepository.save(user);
    return HttpResponse.created("Created");
  }
}
