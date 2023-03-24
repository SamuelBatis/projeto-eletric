package com.micronaut.controllers;

import com.micronaut.etities.Maquina;
import com.micronaut.repositories.MaquinaRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;

import java.util.List;

@Controller("/machine")
public class MaquinasController {

  private final MaquinaRepository repository;

  public MaquinasController(MaquinaRepository repository) {
    this.repository = repository;
  }

  @Post
  @Secured("isAuthenticated()")
  public HttpResponse<Maquina> save(@Body Maquina maquina) {
    repository.save(maquina);
    return HttpResponse.ok(maquina);
  }

  @Get
  @Secured("isAuthenticated()")
  public HttpResponse<List<Maquina>> findAll() {
    var maquinas = repository.findAll();
    return HttpResponse.ok(maquinas);
  }

  @Get("/{id}")
  @Secured("isAuthenticated()")
  public HttpResponse<Maquina> findById(Long id) {
    var maquina = repository.findById(id);
    return HttpResponse.ok(maquina);
  }

  @Get("/user/{id}")
  @Secured("isAuthenticated()")
  public HttpResponse<List<Maquina>> findByUserId(Long id) {
    var maquinas = repository.findByUserId(id);
    return HttpResponse.ok(maquinas);
  }

//  @Put
//  @Secured("isAuthenticated()")
//  public HttpResponse update() {
//
//  }
}
