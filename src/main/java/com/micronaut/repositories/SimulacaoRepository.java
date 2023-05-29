package com.micronaut.repositories;

import com.micronaut.etities.Simulacao;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SimulacaoRepository {

  private final DataSource dataSource;

  public SimulacaoRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void save(Simulacao simulacao) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO Simulacao ( valor, Usuarios_idUsuarios, date, tempoDeUso, nome) VALUES (?, ?, ?, ?, ?);");
      System.out.println("here " + simulacao.getDate());
      stmt.setFloat(1, simulacao.getValor());
      stmt.setLong(2, simulacao.getIdUsuarios());
      stmt.setString(3, simulacao.getDate());
      stmt.setDouble(4, simulacao.getTempoDeUso());
      stmt.setString(5, simulacao.getNome());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

    public List<Simulacao> findAll() {
      List<Simulacao> Simulacoes = new ArrayList<>();
      try (Connection conn = dataSource.getConnection()) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Simulacao");
        while (rs.next()) {
        	Simulacao simulacao = new Simulacao(rs.getLong("idSimulacao"), rs.getFloat("valor"), rs.getLong("Usuarios_idUsuarios"), rs.getString("date"), rs.getDouble("tempoDeUso"), rs.getString(("nome")));
        	Simulacoes.add(simulacao);
        }
      } catch (SQLException e ) {
        e.printStackTrace();
      }
      return Simulacoes;
    }

  public List<Simulacao> findAllByUserId(Long idUsuarios) throws SQLException {
    List<Simulacao> simulacoes = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Simulacao WHERE Usuarios_idUsuarios = ?");
      stmt.setLong(1, idUsuarios);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Simulacao simulacao = new Simulacao(rs.getLong("idSimulacao"), rs.getFloat("valor"), rs.getLong("Usuarios_idUsuarios"), rs.getString("date"), rs.getDouble("tempoDeUso"), rs.getString(("nome")));
        simulacoes.add(simulacao);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return simulacoes;
  }

  public Simulacao findById(Long id) throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      String sql = "SELECT * FROM Simulacao WHERE idSimulacao = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if(rs.next()) {
        Simulacao simulacao = new Simulacao();
        simulacao.setIdSimulacao(rs.getLong("idSimulacao"));
        simulacao.setValor(rs.getFloat("valor"));
        simulacao.setIdUsuarios(rs.getLong("Usuarios_idUsuarios"));
        simulacao.setDate(rs.getString("date"));
        return simulacao;
      }
    }
    return null;
  }

  public void updateInfos(Simulacao simulacao) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("UPDATE Simulacao SET valor = ?, date = ? WHERE idSimulacao = ?");
      stmt.setFloat(1, simulacao.getValor());
      stmt.setString(2, simulacao.getDate());
      stmt.setLong(3, simulacao.getIdSimulacao());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(Long id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("DELETE FROM Simulacao WHERE idSimulacao = ?;");
      stmt.setLong(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}