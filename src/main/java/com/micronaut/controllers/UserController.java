package com.micronaut.controllers;

import com.micronaut.etities.User;
import com.micronaut.repositories.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;

import javax.annotation.security.PermitAll;
import java.sql.SQLException;
import java.util.List;

@Controller("/users")
public class UserController {

    private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Get("/{id}")
  @Secured("isAuthenticated()")
  public User findById(Long id) throws SQLException {
    System.out.println("Id " + id);
    var user = userRepository.findById(id);
    return user;
  }

  @Get("/{login}")
  @Secured("isAuthenticated()")
  public User findByLogin(@PathVariable String login) throws SQLException {
    var user = userRepository.findByLogin(login);

    return user;
  }

  @Get
  @Secured("isAuthenticated()")
  public List<User> findAll() {
    return userRepository.findALl();
  }

  @Post
  @Secured("isAuthenticated()")
  public HttpResponse<String> registerUser(@Body User user) {
    userRepository.save(user);
    return HttpResponse.created("Created");
  }

  @Put
  @Secured("isAuthenticated()")
  public HttpResponse<User> updateUser(@Body User dados) throws SQLException {
    var user = userRepository.findById(dados.getId());
    user.atualizarInfos(dados);
    userRepository.updateInfos(user);
    return HttpResponse.ok(user);
  }

  @Delete("/{id}")
  @Secured("isAuthenticated()")
  public HttpResponse<String> deleteUser(Long id) {
    userRepository.delete(id);
    return HttpResponse.ok("Deleted");
  }

}
