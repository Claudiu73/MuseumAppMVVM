package View;

import Presenter.ILogInUI;
import Presenter.LogInPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame implements ILogInUI {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private String userType;
    private LogInPresenter logInPresenter;

    public LogIn(String userType) {
        logInPresenter = new LogInPresenter(this);
        this.userType = userType;
        setTitle("Autentificare");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        textField1 = new JTextField(20);
        add(textField1);

        passwordField1 = new JPasswordField(20);
        add(passwordField1);

        logInButton = new JButton("Log In");
        add(logInButton);

        logInButton.addActionListener(attemptLogin());
    }

    public ActionListener attemptLogin()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInPresenter.attemptLogin();
            }
        };
        return e;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public String getPasswordField1() {
        return passwordField1.getText();
    }

    public String getUserType() {
        return userType;
    }

    public void setTextField1(String textField1) {
        this.textField1.setText(textField1);
    }

    public void setPasswordField1(String passwordField1) {
        this.passwordField1.setText(passwordField1);
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}