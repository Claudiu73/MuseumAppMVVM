package View;

import Presenter.AdminPresenter;
import Presenter.IAdminUI;
import Presenter.UsersFilterPresenter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI extends JFrame implements IAdminUI {
    private JList<String> list1;
    private JList<String> list2;
    private JTextField textField1, textField2, textField3;
    private JButton adaugaButton, stergeButton, cautaButton, actualizeazaButton, filtruOpereButton;
    private JButton filtrareUsersButton;
    private DefaultListModel<String> listModel, listModel1;
    private AdminPresenter adminPresenter;
    private JFrame frame;
    private JPanel panel;

    public AdminUI() {
        adminPresenter = new AdminPresenter(this);
        listModel = new DefaultListModel<>();
        listModel1 = new DefaultListModel<>();
        frame = new JFrame("Interfață Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        createAndShowUI();
        adminPresenter.fetchAndDisplayArtworks();
        adminPresenter.fetchAndDisplayUsers();
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
        adaugaButton.addActionListener(onAddUserClicked());
        stergeButton.addActionListener(addDeleteUserListener());
        cautaButton.addActionListener(addSearchUserListener());
        actualizeazaButton.addActionListener(onUpdateUserButtonClicked());
        filtruOpereButton.addActionListener(e -> openArtWorkListUI());
        filtrareUsersButton.addActionListener(e -> openUsersFilterUI());
    }
    public ActionListener onAddUserClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPresenter.onAddUserClicked();
            }
        };
        return e;
    }

    public ActionListener addDeleteUserListener()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPresenter.addDeleteUserListener();
            }
        };
        return e;
    }

    public ActionListener addSearchUserListener()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPresenter.addSearchUserListener();
            }
        };
        return e;
    }


    public ActionListener onUpdateUserButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPresenter.onUpdateUserButtonClicked();
            }
        };
        return e;
    }

    @Override
    public JList<String> getUserList() {
        return list1;
    }

    @Override
    public JList<String> getArtWorkList() {
        return list2;
    }

    @Override
    public void setArtWorkList(JList<String> artWorkList) {
        this.list2 = list2;
    }

    @Override
    public DefaultListModel<String> getArtWorksList() {
        return listModel1;
    }

    @Override
    public void setUserList(JList<String> userList) {
        this.list1 = list1;
    }

    @Override
    public void setArtWorksList(DefaultListModel<String> artWorksList) {
        this.list2.setModel(artWorksList);
    }

    @Override
    public DefaultListModel<String> getUserListModel() {
        return listModel;
    }

    @Override
    public void setUserListModel(DefaultListModel<String> userListModel) {
        this.listModel = listModel;
    }

    @Override
    public String getUsernameField() {
        return textField1.getText();
    }

    @Override
    public void setUsernameField(String username) {
        this.textField1.setText(username);
    }

    @Override
    public String getPasswordField() {
        return textField2.getText();
    }

    @Override
    public void setPasswordField(String password) {
        this.textField2.setText(password);
    }

    @Override
    public String getUserTypeField() {
        return textField3.getText();
    }

    @Override
    public void setUserTypeField(String userType) {
        this.textField3.setText(userType);
    }

    public void showScreen()
    {
        frame.setVisible(true);
    }
    private void openArtWorkListUI() {
        ArtWorkListView artWorkListView = new ArtWorkListView();
        artWorkListView.showScreen();
    }

    private void openUsersFilterUI() {
        UsersFilterUI usersFilterUI = new UsersFilterUI();
        usersFilterUI.setVisible(true);
    }
}