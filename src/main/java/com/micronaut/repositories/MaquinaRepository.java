package com.micronaut.repositories;

import com.micronaut.etities.Maquina;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MaquinaRepository {

  private final DataSource dataSource;

  public MaquinaRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  public void save(Maquina maquina) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO Maquinas (nome, power, tempoDeUso, custoTotal, Usuarios_idUsuarios, Departamento_idDepartamento) VALUES (?, ?, ?, ?, ?, ?); ");
      stmt.setString(1, maquina.getNome());
      stmt.setDouble(2, maquina.getPower());
      stmt.setDouble(3, maquina.getTempoDeUso());
      stmt.setDouble(4, maquina.getCustoTotal());
      stmt.setLong(5, maquina.getIdUsuarios());
      stmt.setLong(6, maquina.getIdDepartamento());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Maquina> findAll() {
    List<Maquina> maquinas = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Maquinas;");
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Maquina maquina = new Maquina(rs.getLong("idMaquinas"), rs.getString("nome"), rs.getDouble("power"), rs.getDouble("tempoDeUso"), rs.getDouble("custoTotal"), rs.getLong("Usuarios_idUsuarios"), rs.getLong("Departamento_idDepartamento"));
        maquinas.add(maquina);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return maquinas;
  }

  public Maquina findById(Long id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Maquinas where idMaquinas=?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        Maquina maquina = new Maquina(rs.getLong("idMaquinas"), rs.getString("nome"), rs.getDouble("power"), rs.getDouble("tempoDeUso"), rs.getDouble("custoTotal"), rs.getLong("Usuarios_idUsuarios"), rs.getLong("Departamento_idDepartamento"));
        return maquina;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Maquina> findByUserId(Long id) {
    List<Maquina> maquinas = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Maquinas where Usuarios_idUsuarios=?");
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()) {
        Maquina maquina = new Maquina(rs.getLong("idMaquinas"), rs.getString("nome"), rs.getDouble("power"), rs.getDouble("tempoDeUso"), rs.getDouble("custoTotal"), rs.getLong("Usuarios_idUsuarios"), rs.getLong("Departamento_idDepartamento"));
        maquinas.add(maquina);
        return maquinas;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void delete(Long id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("DELETE FROM Maquinas WHERE idMaquinas = ?");
      stmt.setLong(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
