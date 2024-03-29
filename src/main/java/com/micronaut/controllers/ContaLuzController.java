package com.micronaut.controllers;

import com.micronaut.etities.ContaLuz;
import com.micronaut.repositories.ContaLuzRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

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

  @Get
  @Secured("isAuthenticated()")
  public List<ContaLuz> findAll() {
    var conta = contaLuzRepository.findAll();
    return conta;
  }

  @Get("/user/{id}")
  @Secured("isAuthenticated()")
  public List<ContaLuz> findByUserId(Long id) throws SQLException {
    var conta = contaLuzRepository.findAllByUserId(id);
    return conta;
  }

  @Put
  @Secured("isAuthenticated()")
  public HttpResponse<ContaLuz> update(@Body ContaLuz contaLuz) throws SQLException {
    var conta = contaLuzRepository.findById(contaLuz.getIdContaLuz());
    conta.atualizarInfos(contaLuz);
    contaLuzRepository.updateInfos(conta);
    return HttpResponse.ok(conta);
  }

  @Delete("/{id}")
  @Secured("isAuthenticated()")
  public HttpResponse<String> delete(Long id) {
    contaLuzRepository.delete(id);
    return HttpResponse.ok("Deleted");
  }

}
