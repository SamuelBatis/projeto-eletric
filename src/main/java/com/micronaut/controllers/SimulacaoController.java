package com.micronaut.controllers;

import com.micronaut.etities.Simulacao;
import com.micronaut.repositories.SimulacaoRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

@Controller("/conta-luz")
public class SimulacaoController {

  @Inject
  private final SimulacaoRepository simulacaoRepository;

  public SimulacaoController(SimulacaoRepository simulacaoRepository) {
    this.simulacaoRepository = simulacaoRepository;
  }

  @Post
  @Secured("isAuthenticated()")
  public HttpResponse<String> insert(@Body Simulacao simulacao) {
    simulacaoRepository.save(simulacao);
    return HttpResponse.created("Created");
  }

  @Get
  @Secured("isAuthenticated()")
  public List<Simulacao> findAll() {
    var simulacao = simulacaoRepository.findAll();
    return simulacao;
  }

  @Get("/user/{id}")
  @Secured("isAuthenticated()")
  public List<Simulacao> findByUserId(Long id) throws SQLException {
    var simulacao = simulacaoRepository.findAllByUserId(id);
    return simulacao;
  }

  @Put
  @Secured("isAuthenticated()")
  public HttpResponse<Simulacao> updateInfos(@Body Simulacao simulacao) throws SQLException {
    var simu = simulacaoRepository.findById(simulacao.getIdSimulacao());
    simulacao.atualizarInfos(simulacao);
    simulacaoRepository.updateInfos(simulacao);
    return HttpResponse.ok(simulacao);
  }

  @Delete("/{id}")
  @Secured("isAuthenticated()")
  public HttpResponse<String> delete(Long id) {
	  simulacaoRepository.delete(id);
    return HttpResponse.ok("Deleted");
  }

}
