package View;

import Presenter.AdminPresenter;
import Presenter.IAdminUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI extends JFrame implements IAdminUI {
    private JList<String> list1;
    private JList<String> list2;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton adaugaButton, stergeButton, cautaButton, actualizeazaButton, cautaOperaButton;
    private DefaultListModel<String> listModel, listModel1;
    private AdminPresenter adminPresenter;

    public AdminUI() {
        adminPresenter = new AdminPresenter(this);
        listModel = new DefaultListModel<>();
        listModel1 = new DefaultListModel<>();
        initializeUI();
        adminPresenter.fetchAndDisplayArtworks();
        adminPresenter.fetchAndDisplayUsers();
    }

    private void initializeUI() {
        setTitle("Interfață Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4, 4);

        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        listModel1 = new DefaultListModel<>();
        list2.setModel(listModel1);

        initializeLists(gbc);
        initializeTextFieldsAndLabels(gbc);
        initializeButtonsCentered(gbc);


        pack();
        setLocationRelativeTo(null);
    }

    private void initializeLists(GridBagConstraints gbc) {
        list1 = new JList<>(listModel);
        JScrollPane scrollPaneArtworks = new JScrollPane(list1);

        list2 = new JList<>();
        JScrollPane scrollPaneUsers = new JScrollPane(list2);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 5;
        gbc.weightx = 0.25; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        add(scrollPaneArtworks, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0.25;
        add(scrollPaneUsers, gbc);

        gbc.gridheight = 1; gbc.weightx = 0;
    }

    private void initializeTextFieldsAndLabels(GridBagConstraints gbc) {
        String[] labels = {"Username:", "Password:", "Type:", "Lista Opere:"};
        JTextField[] textFields = {textField1 = new JTextField(20), textField2 = new JTextField(20),
                textField3 = new JTextField(20), textField4 = new JTextField(20)};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 1;
            gbc.gridy = i;
            add(new JLabel(labels[i]), gbc);

            gbc.gridx = 2;
            gbc.gridwidth = 2;
            add(textFields[i], gbc);
        }
    }

    private void initializeButtonsCentered(GridBagConstraints gbc) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        adaugaButton = new JButton("Adaugă");
        stergeButton = new JButton("Șterge");
        cautaButton = new JButton("Caută User");
        actualizeazaButton = new JButton("Actualizează");
        cautaOperaButton = new JButton("Caută Opera");


        buttonPanel.add(adaugaButton);
        adaugaButton.addActionListener(onAddUserClicked());
        buttonPanel.add(stergeButton);
        stergeButton.addActionListener(addDeleteUserListener());
        buttonPanel.add(cautaButton);
        cautaButton.addActionListener(addSearchUserListener());
        buttonPanel.add(actualizeazaButton);
        actualizeazaButton.addActionListener(fetchAndDisplayUsers());
        buttonPanel.add(cautaOperaButton);
        cautaOperaButton.addActionListener(filterArtworks());

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);
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

    public ActionListener filterArtworks()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPresenter.filterArtworks();
            }
        };
        return e;
    }

    public ActionListener fetchAndDisplayUsers()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPresenter.fetchAndDisplayUsers();
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

    @Override
    public String getArtWorkFilter() {
        return textField4.getText();
    }

    @Override
    public void setArtWorkFilter(String filter) {
        this.textField4.setText(filter);
    }

}