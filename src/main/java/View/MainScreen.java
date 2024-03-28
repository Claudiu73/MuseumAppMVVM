package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Presenter.IMainScreenUI;
import Presenter.MainScreenPresenter;

public class MainScreen extends JFrame implements IMainScreenUI {

    private JPanel panel1;
    private JButton vizitatorButton;
    private JButton angajatButton;
    private JButton adminButton;
    private MainScreenPresenter mainScreenPresenter;

    public MainScreen() {
        mainScreenPresenter = new MainScreenPresenter(this);

        setTitle("Ecran Principal");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        vizitatorButton = new JButton("Vizitator");
        angajatButton = new JButton("Angajat");
        adminButton = new JButton("Admin");

        add(vizitatorButton);
        add(angajatButton);
        add(adminButton);

        vizitatorButton.addActionListener(openArtWorkListView());
        angajatButton.addActionListener(openLogInView("angajat"));
        adminButton.addActionListener(openLogInView("admin"));
    }

    public ActionListener openArtWorkListView()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreenPresenter.openArtWorkListView();
            }
        };
        return e;
    }

    public ActionListener openLogInView(String type)
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreenPresenter.openLogInView(type);
            }
        };

        return e;
    }

    @Override
    public void showScreen() {
        this.setVisible(true);
    }
}
