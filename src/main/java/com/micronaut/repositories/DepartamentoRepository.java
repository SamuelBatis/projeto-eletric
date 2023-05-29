package com.micronaut.repositories;

import com.micronaut.etities.Departamento;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DepartamentoRepository {

  private final DataSource dataSource;

  public DepartamentoRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void save(Departamento departamento) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO Departamento (nome) VALUES (?);");
      stmt.setString(1, departamento.getNome());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

    public List<Departamento> findAll() {
      List<Departamento> departamentos = new ArrayList<>();
      try (Connection conn = dataSource.getConnection()) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Departamento");
        while (rs.next()) {
        	Departamento departamento = new Departamento(rs.getLong("idDepartamento"), rs.getString("nome"));
          departamentos.add(departamento);
        }
      } catch (SQLException e ) {
        e.printStackTrace();
      }
      return departamentos;
    }

  public Departamento findById(Long id) throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      String sql = "SELECT * FROM Departamento WHERE idDepartamento = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if(rs.next()) {
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(rs.getLong("idDepartamento"));
        departamento.setNome(rs.getString("nome"));
        return departamento;
      }
    }
    return null;
  }

  public void updateInfos(Departamento departamento) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("UPDATE Departamento SET nome = ? WHERE idDepartamento = ?");
      stmt.setString(1, departamento.getNome());
      stmt.setLong(2, departamento.getIdDepartamento());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(Long id) {
    try (Connection conn = dataSource.getConnection()) {
      // Excluir as máquinas associadas ao departamento
      PreparedStatement stmtMachines = conn.prepareStatement("DELETE FROM Maquinas WHERE Departamento_idDepartamento = ?;");
      stmtMachines.setLong(1, id);
      stmtMachines.executeUpdate();

      // Excluir o departamento
      PreparedStatement stmtDepartment = conn.prepareStatement("DELETE FROM Departamento WHERE idDepartamento = ?;");
      stmtDepartment.setLong(1, id);
      stmtDepartment.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}