package edu.school21.models;

public class User {
    private Integer id;
    private String login;
    private String password;
    private boolean authenticate;

    public User(Integer id, String login, String password, boolean authenticate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authenticate = authenticate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }
}
