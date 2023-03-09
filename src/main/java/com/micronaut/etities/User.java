package com.micronaut.etities;

public class User {

  private Long id;
  private String login;
  private String senha;
  private int admin;

  public User() {}

  public User(Long id, String login, String senha, int admin) {
    this.id = id;
    this.login = login;
    this.senha = senha;
    this.admin = admin;
  }
  public User(String login, String senha, int admin) {
    this.login = login;
    this.senha = senha;
    this.admin = admin;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public int getAdmin() {
    return admin;
  }

  public void setAdmin(int admin) {
    this.admin = admin;
  }
}
