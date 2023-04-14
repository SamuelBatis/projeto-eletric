package com.micronaut.etities;

import jakarta.inject.Singleton;

import java.sql.Date;

@Singleton
public class Departamento {

	private Long idDepartamento;
	private String nome;

  public Departamento () {

  }
  
  public Departamento(Long idDepartamento, String nome) {
    this.idDepartamento = idDepartamento;
    this.nome = nome;
  }
  
  public Departamento(String nome) {
	    this.nome = nome;
	  }

	  public Long getIdDepartamento() {
		return idDepartamento;
	}
	
	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void atualizarInfos(Departamento departamento) {
    if (departamento.getNome() != this.nome) {
      this.nome = departamento.getNome();
    }
  }
}