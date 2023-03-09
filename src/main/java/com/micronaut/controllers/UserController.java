package com.micronaut.controllers;

import com.micronaut.etities.User;
import com.micronaut.repositories.UserRepository;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller("/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Get("/{id}")
  public User findById(Long id) throws SQLException {
    System.out.println("Id " + id);
    var user = userRepository.findById(id);
    return user;
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

  @Put
  public HttpResponse<User> updateUser(@Body User dados) throws SQLException {
    var user = userRepository.findById(dados.getId());
    user.atualizarInfos(dados);
    userRepository.updateInfos(user);
    return HttpResponse.ok(user);
  }

  @Delete("/{id}")
  public HttpResponse<String> deleteUser(Long id) {
    userRepository.delete(id);
    return HttpResponse.ok("Deleted");
  }

}
