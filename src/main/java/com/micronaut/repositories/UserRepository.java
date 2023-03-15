package com.micronaut.repositories;

import com.micronaut.etities.User;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserRepository {

  private final DataSource dataSource;

  public UserRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  public void save(User user) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO Usuarios (Login, Senha, Admin) VALUES (?, ?, ?)");
      stmt.setString(1, user.getLogin());
      stmt.setString(2, user.getSenha());
      stmt.setInt(3, user.getAdmin());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public User findById(Long id)  throws SQLException{
    try (Connection conn = dataSource.getConnection()) {
      String sql = "SELECT * FROM Usuarios WHERE idUsuarios = ?";
      try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setLong(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
          if (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("idUsuarios"));
            user.setLogin(rs.getString("Login"));
            user.setSenha(rs.getString("Senha"));
            user.setAdmin(rs.getInt("Admin"));
            return user;
          }
        }
      }
    }

    return null;
  }

  public List<User> findALl() {
    List<User> users = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios");
      while (rs.next()) {
        User user = new User(rs.getLong("idUsuarios"), rs.getString("Login"), rs.getString("Senha"), rs.getInt("Admin"));
        users.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;

  }

  public void updateInfos(User user) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt = conn.prepareStatement("UPDATE Usuarios SET Login = ?, Senha = ?, Admin = ? WHERE idUsuarios = ?");
      stmt.setString(1, user.getLogin());
      stmt.setString(2, user.getSenha());
      stmt.setInt(3, user.getAdmin());
      stmt.setLong(4, user.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(Long id) {
    try (Connection conn = dataSource.getConnection()) {
      PreparedStatement stmt  = conn.prepareStatement("DELETE FROM Usuarios WHERE idUsuarios = ?");
      stmt.setLong(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public User findByLogin(String username) throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      String sql = "SELECT * FROM Usuarios WHERE Login = ?";
      try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        try (ResultSet rs = stmt.executeQuery()) {
          if (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("idUsuarios"));
            user.setLogin(rs.getString("Login"));
            user.setSenha(rs.getString("Senha"));
            user.setAdmin(rs.getInt("Admin"));
            return user;
          }
        }
      }
    }
    return null;
  }
}
