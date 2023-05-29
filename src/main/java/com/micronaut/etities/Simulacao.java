package com.micronaut.etities;

import jakarta.inject.Singleton;

import java.sql.Date;

@Singleton
public class Simulacao {

  private Long idSimulacao;

  private String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  private Float valor;
  private String date;
  private Long idUsuarios;

  private Double tempoDeUso;

  public Double getTempoDeUso() {
    return tempoDeUso;
  }

  public void setTempoDeUso(Double tempoDeUso) {
    this.tempoDeUso = tempoDeUso;
  }

  public Simulacao () {

  }
  public Simulacao(Long idSimulacao, Float valor, Long idUsuarios, String date, Double tempoDeUso, String nome ) {
    this.idSimulacao = idSimulacao;
    this.valor = valor;
    this.idUsuarios = idUsuarios;
    this.date = date;
    this.tempoDeUso = tempoDeUso;
    this.nome = nome;
  }
  
  public Simulacao(Float valor, Long idUsuarios, String date) {
	    this.valor = valor;
	    this.idUsuarios = idUsuarios;
	    this.date = date;
	  }

  public Long getIdSimulacao() {
	return idSimulacao;
  }
  public void setIdSimulacao(Long idSimulacao) {
	this.idSimulacao = idSimulacao;
  }

  public Float getValor() {
    return valor;
  }

  public void setValor(Float valor) {
    this.valor = valor;
  }

  public Long getIdUsuarios() {
    return idUsuarios;
  }

  public void setIdUsuarios(Long idUsuarios) {
    this.idUsuarios = idUsuarios;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void atualizarInfos(Simulacao simulacao) {
    if (simulacao.getValor() != this.valor) {
      this.valor = simulacao.getValor();
    }

    if (simulacao.getDate() != this.getDate()) {
      this.date = simulacao.getDate();
    }
  }
}