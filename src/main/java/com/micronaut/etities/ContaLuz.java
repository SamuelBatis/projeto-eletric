package com.micronaut.etities;

import jakarta.inject.Singleton;

import java.sql.Date;

@Singleton
public class ContaLuz {

  private Long idContaLuz;
  private Float valor;
  private Long idUsuarios;
  private Date date;

  public ContaLuz () {

  }
  public ContaLuz(Long idContaLuz, Float valor, Long idUsuarios, Date date) {
    this.idContaLuz = idContaLuz;
    this.valor = valor;
    this.idUsuarios = idUsuarios;
    this.date = date;
  }

  public Long getIdContaLuz() {
    return idContaLuz;
  }

  public void setIdContaLuz(Long idContaLuz) {
    this.idContaLuz = idContaLuz;
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
}
