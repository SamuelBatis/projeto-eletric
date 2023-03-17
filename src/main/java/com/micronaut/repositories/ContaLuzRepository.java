package com.micronaut.repositories;

import com.micronaut.etities.ContaLuz;
import com.micronaut.etities.User;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

//  public List<ContaLuz> findAllByUserId(Long idUsuarios) throws SQLException {
//    try (Connection conn = dataSource.getConnection()) {
//      String sql = "SELECT * FROM ContaLuz WHERE Usuarios_idUsuarios = ?";
//
//    }
}
