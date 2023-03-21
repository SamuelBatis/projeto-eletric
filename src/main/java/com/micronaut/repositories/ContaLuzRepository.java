package com.micronaut.repositories;

import com.micronaut.etities.ContaLuz;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ContaLuzRepository {

  private final DataSource dataSource;

  public ContaLuzRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void save(ContaLuz contaDeLuz) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO ContaLuz ( valor, Usuarios_idUsuarios, data) VALUES (?, ?, ?);");
      System.out.println("here " + contaDeLuz.getDate());
      stmt.setFloat(1, contaDeLuz.getValor());
      stmt.setLong(2, contaDeLuz.getIdUsuarios());
      stmt.setDate(3, contaDeLuz.getDate());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

    public List<ContaLuz> findAll() {
      List<ContaLuz> contas = new ArrayList<>();
      try (Connection conn = dataSource.getConnection()) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ContaLuz");
        while (rs.next()) {
          ContaLuz conta = new ContaLuz(rs.getLong("idContaLuz"), rs.getFloat("valor"), rs.getLong("Usuarios_idUsuarios"), rs.getDate("data"));
          contas.add(conta);
        }
      } catch (SQLException e ) {
        e.printStackTrace();
      }
      return contas;
    }

  public List<ContaLuz> findAllByUserId(Long idUsuarios) throws SQLException {
    List<ContaLuz> contas = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ContaLuz WHERE Usuarios_idUsuarios = ?");
      stmt.setLong(1, idUsuarios);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        ContaLuz conta = new ContaLuz(rs.getLong("idContaLuz"), rs.getFloat("valor"), rs.getLong("Usuarios_idUsuarios"), rs.getDate("data"));
        contas.add(conta);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return contas;
  }

  public ContaLuz findById(Long id) throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      String sql = "SELECT * FROM ContaLuz WHERE idContaLuz = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if(rs.next()) {
        ContaLuz contaLuz = new ContaLuz();
        contaLuz.setValor(rs.getFloat("valor"));
        //contaLuz.setIdUsuarios(rs.getLong());
        return contaLuz;
      }
    }
    return null;
  }

}