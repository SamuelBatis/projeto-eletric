package com.micronaut.etities;

import jakarta.inject.Singleton;

import java.sql.Date;

@Singleton
public class Simulacao {

  private Long idSimulacao;
  private Float valor;
  private Date date;
  private Long idUsuarios;

  public Simulacao () {

  }
  public Simulacao(Long idSimulacao, Float valor, Long idUsuarios, Date date) {
    this.idSimulacao = idSimulacao;
    this.valor = valor;
    this.idUsuarios = idUsuarios;
    this.date = date;
  }
  
  public Simulacao(Float valor, Long idUsuarios, Date date) {
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
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