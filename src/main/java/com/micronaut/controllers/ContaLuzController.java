package com.micronaut.controllers;

import com.micronaut.etities.ContaLuz;
import com.micronaut.repositories.ContaLuzRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;

import javax.annotation.security.PermitAll;

@Controller("/conta-luz")
public class ContaLuzController {

  @Inject
  private final ContaLuzRepository contaLuzRepository;

  public ContaLuzController(ContaLuzRepository contaLuzRepository) {
    this.contaLuzRepository = contaLuzRepository;
  }

  @Post
  @Secured("isAuthenticated()")
  public HttpResponse<String> insert(@Body ContaLuz contaLuz) {
    contaLuzRepository.save(contaLuz);
    return HttpResponse.created("Created");
  }
}
