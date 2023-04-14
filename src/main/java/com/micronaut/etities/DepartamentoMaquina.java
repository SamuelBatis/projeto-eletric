package com.micronaut.etities;

import jakarta.inject.Singleton;

@Singleton
public class DepartamentoMaquina {

	private Long idMaquina;
	private Long idDepartamento;
	private Long qtd;
	
	DepartamentoMaquina(){
		
	}
	
	public DepartamentoMaquina(Long idMaquina, Long idDepartamento, Long qtd){
		this.idMaquina = idMaquina;
		this.idDepartamento = idDepartamento;
		this.qtd = qtd;
	}
	
	DepartamentoMaquina(Long idDepartamento, Long qtd){
		this.idDepartamento = idDepartamento;
		this.qtd = qtd;
	}
	
	public Long getIdMaquina() {
		return idMaquina;
	}
	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}
	public Long getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public Long getQtd() {
		return qtd;
	}
	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}
	public void atualizarInfos(DepartamentoMaquina dptMaquina) {
	    if (dptMaquina.getQtd() != this.qtd) {
	      this.qtd = dptMaquina.getQtd();
	    }
	  }
	
}
