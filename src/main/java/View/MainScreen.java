package View;

import Presenter.IMainScreenUI;
import Presenter.MainScreenPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen implements IMainScreenUI {

    private MainScreenPresenter mainScreenPresenter;
    private JButton vizitatorButton;
    private JButton angajatButton;
    private JButton adminButton;
    private JFrame frame;

    public MainScreen() {

        mainScreenPresenter = new MainScreenPresenter(this);
        frame = new JFrame("Aplicatie Muzeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

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

        JLabel titleLabel = new JLabel("Aplicatie Muzeu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setSize(500, 30);
        titleLabel.setLocation(0, 40);

        JLabel promptLabel = new JLabel("Sunt un:", SwingConstants.CENTER);
        promptLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setSize(500, 20);
        promptLabel.setLocation(0, 140);

        vizitatorButton = new JButton("Vizitator");
        angajatButton = new JButton("Angajat");
        adminButton = new JButton("Admin");


        int centerX = frame.getWidth() / 2;
        vizitatorButton = new JButton("Vizitator");
        vizitatorButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        vizitatorButton.setBounds(centerX - 50, 190, 100, 30);
        vizitatorButton.setBackground(new Color(238, 238, 238));
        vizitatorButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        angajatButton = new JButton("Angajat");
        angajatButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        angajatButton.setBounds(centerX - 50, 230, 100, 30);
        angajatButton.setBackground(new Color(238, 238, 238));
        angajatButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        adminButton.setBounds(centerX - 50, 270, 100, 30);
        adminButton.setBackground(new Color(238, 238, 238));
        adminButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(titleLabel);
        panel.add(promptLabel);
        panel.add(vizitatorButton);
        panel.add(angajatButton);
        panel.add(adminButton);

        vizitatorButton.addActionListener(openArtWorkListView());
        angajatButton.addActionListener(openLogInView("angajat"));
        adminButton.addActionListener(openLogInView("admin"));

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public ActionListener openArtWorkListView()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreenPresenter.openArtWorkListView();
                frame.dispose();
            }
        };
        return e;
    }

    public ActionListener openLogInView(String type) {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreenPresenter.openLogInView(type);
                frame.dispose();
            }
        };
        return e;
    }

    @Override
    public void showScreen() {
        this.frame.setVisible(true);
    }
}
