package View;

import Presenter.ILogInUI;
import Presenter.LogInPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LogIn implements ILogInUI {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private String userType;
    private JFrame frame;
    private LogInPresenter logInPresenter;

    public LogIn(String userType) {
        logInPresenter = new LogInPresenter(this);
        this.userType = userType;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Autentificare");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\sirbu\\Desktop\\Cauta\\Faculta 3.2\\PS\\MuseumApp\\LogIn fundal.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 10, 200, 20);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(usernameLabel);

        textField1 = new JTextField(20);
        textField1.setBounds(50, 30, 200, 30);
        panel.add(textField1);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 60, 200, 20);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(passwordLabel);

        passwordField1 = new JPasswordField(20);
        passwordField1.setBounds(50, 80, 200, 30);
        panel.add(passwordField1);

        logInButton = new JButton("Log In");
        logInButton.setBounds(100, 120, 100, 30);
        logInButton.setBackground(new Color(232, 181, 17, 150));
        logInButton.setOpaque(true);
        logInButton.setBorderPainted(false);
        logInButton.addActionListener(this::attemptLogin);
        panel.add(logInButton);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void attemptLogin(ActionEvent e) {
        logInPresenter.attemptLogin();
        frame.dispose();
    }

    @Override
    public String getTextField1() {
        return textField1.getText();
    }

    @Override
    public String getPasswordField1() {
        return new String(passwordField1.getPassword());
    }

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public void setTextField1(String textField1) {
        this.textField1.setText(textField1);
    }

    @Override
    public void setPasswordField1(String passwordField1) {
        this.passwordField1.setText(passwordField1);
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void showScreen()
    {
        frame.setVisible(true);
    }
}
