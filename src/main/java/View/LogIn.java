package View;

import Repo.UserRepository;
import ViewModel.LogInViewModel;

import javax.swing.*;
import java.awt.*;

public class LogIn {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JFrame frame;
    private LogInViewModel viewModel;

    public LogIn(LogInViewModel viewModel, UserRepository userRepository) {
        this.viewModel = viewModel;  // Folosește instanța pasată, nu crea una nouă
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

        setupLabelsAndFields(panel);

        logInButton = new JButton("Log In");
        logInButton.setBounds(100, 120, 100, 30);
        logInButton.setBackground(new Color(232, 181, 17, 150));
        logInButton.setOpaque(true);
        logInButton.setBorderPainted(false);
        logInButton.addActionListener(e -> performLogin());
        panel.add(logInButton);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void setupLabelsAndFields(JPanel panel) {
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
    }

    private void performLogin() {
        viewModel.setUsername(textField1.getText().trim());
        viewModel.setPassword(new String(passwordField1.getPassword()).trim());
        viewModel.initiateLogin();  // Apelarea noii metode pentru a iniția logarea
    }


    public void showScreen() {
        frame.setVisible(true);
    }
}
