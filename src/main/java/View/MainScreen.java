package View;

import Model.ArtWork;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainScreen extends JFrame{
    private JPanel panel1;
    private JButton vizitatorButton;
    private JButton angajatButton;
    private JButton adminButton;

    public MainScreen() {

        setTitle("Ecran Principal");
        setSize(300, 300);
        this.setContentPane(panel1);

        // Adaugă ascultători
        vizitatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Deschide fereastra ArtWorkListView
                ArtWorkListView artWorkListView = new ArtWorkListView();
                artWorkListView.setVisible(true);
                // O opțiune este să închizi fereastra principală, sau să o ascunzi folosind MainScreen.this.setVisible(false);
            }

        });

        angajatButton.addActionListener(e -> {
            LogIn logIn = new LogIn("angajat");
            logIn.setVisible(true);
        });

        adminButton.addActionListener(e -> {
            LogIn logIn = new LogIn("admin");
            logIn.setVisible(true);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
