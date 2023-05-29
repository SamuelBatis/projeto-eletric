package com.micronaut.etities;

public class Maquina {
  private Long idMaquinas;
  private String nome;
  private Double power;
  private Double tempoDeUso;
  private Double custoTotal;
  private Long idUsuarios;
  private Long idDepartamento;

  public Long getIdDepartamento() {
    return idDepartamento;
  }

  public void setIdDepartamento(Long idDepartamento) {
    this.idDepartamento = idDepartamento;
  }

  public Maquina(Long idMaquinas, String nome, Double power, Double tempoDeUso, Double custoTotal, Long idUsuarios, Long idDepartamento) {
    this.idMaquinas = idMaquinas;
    this.nome = nome;
    this.power = power;
    this.tempoDeUso = tempoDeUso;
    this.custoTotal = custoTotal;
    this.idUsuarios = idUsuarios;
    this.idDepartamento = idDepartamento;
  }




  public Maquina(){}
  public Long getIdMaquinas() {
    return idMaquinas;
  }

  public void setIdMaquinas(Long idMaquinas) {
    this.idMaquinas = idMaquinas;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Double getPower() {
    return power;
  }

  public void setPower(Double power) {
    this.power = power;
  }

  public Double getTempoDeUso() {
    return tempoDeUso;
  }

  public void setTempoDeUso(Double tempoDeUso) {
    this.tempoDeUso = tempoDeUso;
  }

  public Double getCustoTotal() {
    return custoTotal;
  }

  public void setCustoTotal(Double custoTotal) {
    this.custoTotal = custoTotal;
  }

  public Long getIdUsuarios() {
    return idUsuarios;
  }

  public void setIdUsuarios(Long idUsuarios) {
    this.idUsuarios = idUsuarios;
  }
}
