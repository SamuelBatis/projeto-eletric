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
}
