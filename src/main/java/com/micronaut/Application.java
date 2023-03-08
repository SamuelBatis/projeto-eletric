package com.micronaut;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.runtime.Micronaut;
import jakarta.inject.Singleton;

import javax.sql.DataSource;

public class Application {

  public static void main(String[] args) {
    Micronaut.run(Application.class, args);
  }


  @Factory
  static class DatabaseConfig {
    @Bean
    @Singleton
    public HikariDataSource dataSource() {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false");
      config.setUsername("root");
      config.setPassword("docker");
      return new HikariDataSource(config);
    }
  }
}