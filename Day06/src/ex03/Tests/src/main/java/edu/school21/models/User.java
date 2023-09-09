package edu.school21.models;

public class User {
    private final Integer identifier;
    private String login;
    private String password;
    private boolean authStatus;

    public User(String login, String password, boolean authStatus) {
        identifier = UserIdsGenerator.getInstance().generateId();
        this.login = login;
        this.password = password;
        this.authStatus = authStatus;
    }

    public Integer getIdentifier() { return identifier; }

    public boolean isAuthStatus() { return authStatus; }

    public void setAuthStatus(boolean authStatus) { this.authStatus = authStatus; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
