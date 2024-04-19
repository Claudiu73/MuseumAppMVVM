package View;

import Command.CLogIn;
import Repo.UserRepository;
import ViewModel.LogInViewModel;
import ViewModel.MainScreenViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen{

    private MainScreenViewModel mainScreenViewModel;
    private JButton vizitatorButton;
    private JButton angajatButton;
    private JButton adminButton;
    private JFrame frame;
    private LogInViewModel viewModel;
    private UserRepository userRepository;

    public MainScreen() {
        viewModel = new LogInViewModel();
        mainScreenViewModel = new MainScreenViewModel(viewModel, userRepository);
        userRepository = new UserRepository();
        frame = new JFrame("Aplicatie Muzeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\sirbu\\Desktop\\Cauta\\Faculta 3.2\\PS\\MuseumApp\\FundalMainScreen.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        setupButtons(panel);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }


    private void setupButtons(JPanel panel) {
        JLabel titleLabel = new JLabel("Aplicatie Muzeu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(0, 40, 500, 30);
        panel.add(titleLabel);

        JLabel promptLabel = new JLabel("Sunt un:", SwingConstants.CENTER);
        promptLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setBounds(0, 140, 500, 20);
        panel.add(promptLabel);

        int centerX = 250; // Centru presupus al frame-ului
        JButton vizitatorButton = new JButton("Vizitator");
        vizitatorButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        vizitatorButton.setBounds(centerX - 50, 190, 100, 30);
        vizitatorButton.setBackground(new Color(238, 238, 238));
        vizitatorButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        vizitatorButton.addActionListener(e -> performOpenVisitor());
        panel.add(vizitatorButton);

        JButton angajatButton = new JButton("Angajat");
        angajatButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        angajatButton.setBounds(centerX - 50, 230, 100, 30);
        angajatButton.setBackground(new Color(238, 238, 238));
        angajatButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        angajatButton.addActionListener(e -> {
            viewModel.setUserType("angajat");
            new LogIn(viewModel, userRepository).showScreen();
        });
        panel.add(angajatButton);

        JButton adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        adminButton.setBounds(centerX - 50, 270, 100, 30);
        adminButton.setBackground(new Color(238, 238, 238));
        adminButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        adminButton.addActionListener(e -> {
            viewModel.setUserType("admin");
            new LogIn(viewModel, userRepository).showScreen();
        });
        panel.add(adminButton);
    }
    public void showScreen() {
        frame.setVisible(true);
    }


    private void performOpenVisitor()
    {
        System.out.println("aic");
        mainScreenViewModel.OpenVisitor();
    }
}
