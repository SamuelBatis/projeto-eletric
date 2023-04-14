package com.micronaut.controllers;

import com.micronaut.etities.DepartamentoMaquina;
import com.micronaut.repositories.DepartamentoMaquinaRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;

import javax.annotation.security.PermitAll;
import java.sql.SQLException;
import java.util.List;

@Controller("/departamento")
public class DepartamentoMaquinaController {

  private final DepartamentoMaquinaRepository DepartamentoMaquinaRepository;

  public DepartamentoMaquinaController(DepartamentoMaquinaRepository DepartamentoMaquinaRepository) {
    this.DepartamentoMaquinaRepository = DepartamentoMaquinaRepository;
  }

  @Get("/{id}")
  @Secured("isAuthenticated()")
  public DepartamentoMaquina findById(Long id) throws SQLException {
    System.out.println("Id " + id);
    var DepartamentoMaquina = DepartamentoMaquinaRepository.findById(id);
    return DepartamentoMaquina;
  }

  @Get
  @Secured("isAuthenticated()")
  public List<DepartamentoMaquina> findAll() {
    return DepartamentoMaquinaRepository.findAll();
  }

  @Post
  @Secured("isAuthenticated()")
  public HttpResponse<String> registerDepartamento(@Body DepartamentoMaquina DepartamentoMaquina) {
    DepartamentoMaquinaRepository.save(DepartamentoMaquina);
    return HttpResponse.created("Created");
  }

  @Put
  @Secured("isAuthenticated()")
  public HttpResponse<DepartamentoMaquina> updateDepartamento(@Body DepartamentoMaquina dados) throws SQLException {
    var DepartamentoMaquina = DepartamentoMaquinaRepository.findById(dados.getIdDepartamento());
    DepartamentoMaquina.atualizarInfos(dados);
    DepartamentoMaquinaRepository.updateInfos(DepartamentoMaquina);
    return HttpResponse.ok(DepartamentoMaquina);
  }

  @Delete("/{id}")
  @Secured("isAuthenticated()")
  public HttpResponse<String> deleteDepartamento(Long id) {
    DepartamentoMaquinaRepository.delete(id);
    return HttpResponse.ok("Deleted");
  }

}
