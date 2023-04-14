package com.micronaut.repositories;

import com.micronaut.etities.DepartamentoMaquina;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DepartamentoMaquinaRepository {

  private final DataSource dataSource;

  public DepartamentoMaquinaRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  public void save(DepartamentoMaquina departamentoMaquina) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO Departamento_Maquinas (qtd) VALUES (?); ");
      stmt.setLong(1, departamentoMaquina.getIdMaquina());
      stmt.setLong(2, departamentoMaquina.getIdDepartamento());
      stmt.setLong(2, departamentoMaquina.getQtd());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void updateInfos(DepartamentoMaquina departamentoMaquina) {
	    try (Connection conn = dataSource.getConnection()) {
	      PreparedStatement stmt = conn.prepareStatement("UPDATE Departamento_Maquinas SET nome = ? WHERE idDepartamento = ? AND idMaquina = ?");
	      stmt.setLong(1, departamentoMaquina.getQtd());
	      stmt.setLong(2, departamentoMaquina.getIdDepartamento());
	      stmt.setLong(3, departamentoMaquina.getIdMaquina());
	      stmt.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }

  public List<DepartamentoMaquina> findAll() {
    List<DepartamentoMaquina> departamentoMaquinas = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Departamento_Maquinas;");
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        DepartamentoMaquina departamentoMaquina = new DepartamentoMaquina(rs.getLong("Maquinas_idMaquinas"), rs.getLong("Departamento_idDepartamento"), rs.getLong("qtd"));
        departamentoMaquinas.add(departamentoMaquina);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return departamentoMaquinas;
  }

  public DepartamentoMaquina findById(Long id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Departamento_Maquinas where Departamento_idDepartamento=?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        DepartamentoMaquina departamentoMaquina = new DepartamentoMaquina(rs.getLong("Maquinas_idMaquinas"), rs.getLong("Departamento_idDepartamento"), rs.getLong("qtd"));
        return departamentoMaquina;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<DepartamentoMaquina> findByUserId(Long id) {
    List<DepartamentoMaquina> DepartamentoMaquina = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Departamento_Maquinas where Departamento_idDepartamento=?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()) {
        DepartamentoMaquina departamentoMaquina = new DepartamentoMaquina(rs.getLong("Maquinas_idMaquinas"), rs.getLong("Departamento_idDepartamento"), rs.getLong("qtd"));
        DepartamentoMaquina.add(departamentoMaquina);
        return DepartamentoMaquina;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  public void delete(Long id) {
	    try (Connection conn = dataSource.getConnection()) {
	      PreparedStatement stmt = conn.prepareStatement("DELETE FROM Departamento_Maquinas WHERE idDepartamento = ?;");
	      stmt.setLong(1, id);
	      stmt.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
}
