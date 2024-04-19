package ViewModel;

import Command.CLogIn;
import Repo.UserRepository;

import javax.swing.*;


public class LogInViewModel {

    private UserRepository userRepository;
    private String username;
    private String password;
    private String userType;
    public CLogIn cLogIn;
    public LogInViewModel()
    {
        this.userRepository = new UserRepository();
        this.cLogIn = new CLogIn(this, userRepository);
    }
    public void logInButtonClicked()
    {
        cLogIn.execute();
    }

    public void initiateLogin() {
        if (!getUsername().isEmpty() && !getPassword().isEmpty()) {
            logInButtonClicked();
        } else {
            JOptionPane.showMessageDialog(null, "Vă rugăm să introduceți numele de utilizator și parola.", "Date lipsă", JOptionPane.WARNING_MESSAGE);
        }
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }
}