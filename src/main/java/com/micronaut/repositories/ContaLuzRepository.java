package com.micronaut.repositories;

import com.micronaut.etities.ContaLuz;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO ContaLuz (valor, Usuarios_idUsuarios, data) VALUES (?, ?, ?);");
      stmt.setFloat(1, contaDeLuz.getValor());
      stmt.setLong(2, contaDeLuz.getIdUsuarios());

      // Convertendo a data de String para java.sql.Date
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      java.util.Date date = dateFormat.parse(contaDeLuz.getData().toString());
      java.sql.Date sqlDate = new java.sql.Date(date.getTime());

      stmt.setDate(3, sqlDate);
      stmt.executeUpdate();
    } catch (SQLException | ParseException e) {
      e.printStackTrace();
    }
  }


  public List<ContaLuz> findAll() {
      List<ContaLuz> contas = new ArrayList<>();
      try (Connection conn = dataSource.getConnection()) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ContaLuz");
        while (rs.next()) {
          ContaLuz conta = new ContaLuz(rs.getLong("idContaLuz"), rs.getFloat("valor"), rs.getLong("Usuarios_idUsuarios"), rs.getString("data"));
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
        ContaLuz conta = new ContaLuz(rs.getLong("idContaLuz"), rs.getFloat("valor"), rs.getLong("Usuarios_idUsuarios"), rs.getString("data"));
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
        contaLuz.setIdContaLuz( rs.getLong("idContaLuz"));
        contaLuz.setValor(rs.getFloat("valor"));
        contaLuz.setIdUsuarios(rs.getLong("Usuarios_idUsuarios"));
        contaLuz.setData(rs.getString("data"));
        return contaLuz;
      }
    }
    return null;
  }

  public void updateInfos(ContaLuz conta) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("UPDATE ContaLuz SET valor = ?, data = ? WHERE idContaLuz = ?");
      stmt.setFloat(1, conta.getValor());
      stmt.setString(2, conta.getData());
      stmt.setLong(3, conta.getIdContaLuz());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(Long id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("DELETE FROM ContaLuz WHERE idContaLuz = ?;");
      stmt.setLong(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}