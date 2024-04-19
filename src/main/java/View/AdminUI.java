package View;

import ViewModel.AdminViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI extends JFrame{
    private JList<String> list1;
    private JList<String> list2;
    private JTextField textField1, textField2, textField3;
    private JButton adaugaButton, stergeButton, cautaButton, actualizeazaButton, filtruOpereButton;
    private JButton filtrareUsersButton;
    private DefaultListModel<String> listModel, listModel1;
    private AdminViewModel adminViewModel;
    private JFrame frame;
    private JPanel panel;

    public AdminUI() {
        adminViewModel = new AdminViewModel();
        listModel = new DefaultListModel<>();
        listModel1 = new DefaultListModel<>();
        frame = new JFrame("Interfață Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        createAndShowUI();
        adminViewModel.setListModelArt(listModel);
        adminViewModel.setListModelUser(listModel1);
        performListArtWorks();
        performListUsers();
    }

    private void createAndShowUI() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\sirbu\\Desktop\\Cauta\\Faculta 3.2\\PS\\MuseumApp\\FundalAdmin.png");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Servicii Admin", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(150, 10, 500, 30);
        panel.add(titleLabel);

        JLabel UNLabel = new JLabel("UN: ", SwingConstants.HORIZONTAL);
        UNLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        UNLabel.setForeground(Color.BLACK);
        UNLabel.setBounds(65,135, 500, 30);
        panel.add(UNLabel);

        JLabel PWLabel = new JLabel("PW: ", SwingConstants.HORIZONTAL);
        PWLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        PWLabel.setForeground(Color.BLACK);
        PWLabel.setBounds(65,170, 500, 30);
        panel.add(PWLabel);

        JLabel UTLabel = new JLabel("UT: ", SwingConstants.HORIZONTAL);
        UTLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        UTLabel.setForeground(Color.BLACK);
        UTLabel.setBounds(65,205, 500, 30);
        panel.add(UTLabel);

        initializeUI();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void initializeUI() {
        list1 = new JList<>(listModel);
        JScrollPane scrollPaneArtworks = new JScrollPane(list1);
        scrollPaneArtworks.setBounds(20, 165, 250, 200);

        list2 = new JList<>(listModel1);
        JScrollPane scrollPaneUsers = new JScrollPane(list2);
        scrollPaneUsers.setBounds(520, 165, 250, 200);

        textField1 = new JTextField();
        textField1.setBounds(330, 135, 150, 25);
        textField2 = new JTextField();
        textField2.setBounds(330, 170, 150, 25);
        textField3 = new JTextField();
        textField3.setBounds(330, 205, 150, 25);

        Color orangeBrown = new Color(238, 155, 69);

        adaugaButton = new JButton("Adaugă");
        adaugaButton.setBounds(300, 275, 90, 25);
        adaugaButton.setBackground(orangeBrown);

        stergeButton = new JButton("Șterge");
        stergeButton.setBounds(400, 275, 90, 25);
        stergeButton.setBackground(orangeBrown);

        cautaButton = new JButton("Caută User");
        cautaButton.setBounds(300, 310, 90, 25);
        cautaButton.setBackground(orangeBrown);

        actualizeazaButton = new JButton("Actualizează");
        actualizeazaButton.setBounds(400, 310, 90, 25);
        actualizeazaButton.setBackground(orangeBrown);

        filtruOpereButton = new JButton("Filtrare Lista Opere");
        filtruOpereButton.setBounds(300, 345, 190, 25);
        filtruOpereButton.setBackground(orangeBrown);

        filtrareUsersButton = new JButton("Filtrare Lista Users");
        filtrareUsersButton.setBounds(300, 380, 190, 25);
        filtrareUsersButton.setBackground(orangeBrown);

        panel.add(scrollPaneArtworks);
        panel.add(scrollPaneUsers);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(adaugaButton);
        panel.add(stergeButton);
        panel.add(cautaButton);
        panel.add(actualizeazaButton);
        panel.add(filtruOpereButton);
        panel.add(filtrareUsersButton);

        addEventHandlers();
    }


    private void addEventHandlers() {
        adaugaButton.addActionListener(e -> {
            performAddUser();
        });
        stergeButton.addActionListener(e -> {
            adminViewModel.setUsername(textField1.getText());
            performDeleteUser();
        });
        cautaButton.addActionListener(e -> {
            adminViewModel.setUsername(textField1.getText());
            performSearchUser();});
        actualizeazaButton.addActionListener(e -> {
            adminViewModel.setUsername(textField1.getText());
            adminViewModel.setPassword(textField2.getText());
            adminViewModel.setUserType(textField3.getText());
            performUpdateUser();});
        filtruOpereButton.addActionListener(e -> performOpenToFilterListOfArtWorks());
        filtrareUsersButton.addActionListener(e -> performOpenToFilterUsers());
    }


    private void performAddUser()
    {
        adminViewModel.setUsername(textField1.getText().trim());
        adminViewModel.setPassword(textField2.getText().trim());
        adminViewModel.setUserType(textField3.getText().trim());
        adminViewModel.AddUser();
    }

    private void performDeleteUser()
    {
        adminViewModel.getUsername();
        adminViewModel.DeleteUser();
    }

    private void performSearchUser()
    {
        adminViewModel.SearchUser();
    }

    private void performUpdateUser()
    {
        adminViewModel.UpdateUser();
    }

    private void performListArtWorks()
    {
        adminViewModel.ListArtWorks();
    }

    private void performListUsers()
    {
        adminViewModel.ListUsers();
    }

    private void performOpenToFilterUsers()
    {
        adminViewModel.OpenToFilterUsers();
    }
    private void performOpenToFilterListOfArtWorks()
    {
        adminViewModel.OpenToFilterListOfArtWorks();
    }

}