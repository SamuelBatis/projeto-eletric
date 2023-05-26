package com.micronaut.controllers;

import com.micronaut.etities.Departamento;
import com.micronaut.repositories.DepartamentoRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

@Controller("/departamentos")
public class DepartamentoController {

  @Inject
  private final DepartamentoRepository departamentoRepository;

  public DepartamentoController(DepartamentoRepository departamentoRepository) {
    this.departamentoRepository = departamentoRepository;
  }

  @Post
  @Secured("isAuthenticated()")
  public HttpResponse<String> insert(@Body Departamento departamento) {
	  departamentoRepository.save(departamento);
    return HttpResponse.created("Created");
  }

  @Get
  @Secured("isAuthenticated()")
  public List<Departamento> findAll() {
    var departamento = departamentoRepository.findAll();
    return departamento;
  }

  @Put
  @Secured("isAuthenticated()")
  public HttpResponse<Departamento> update(@Body Departamento departamento) throws SQLException {
    var depart = departamentoRepository.findById(departamento.getIdDepartamento());
    depart.atualizarInfos(depart);
    departamentoRepository.updateInfos(depart);
    return HttpResponse.ok(depart);
  }

  @Delete("/{id}")
  @Secured("isAuthenticated()")
  public HttpResponse<String> delete(Long id) {
    departamentoRepository.delete(id);
    return HttpResponse.ok("Deleted");
  }

}