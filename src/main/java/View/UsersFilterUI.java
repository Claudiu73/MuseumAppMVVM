package View;

import ViewModel.UsersFilterViewModel;

import javax.swing.*;
import java.awt.*;


public class UsersFilterUI extends JFrame {
    private JList<String> list1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private UsersFilterViewModel usersFilterViewModel;
    private DefaultListModel<String> listModel;

    public UsersFilterUI() {
        usersFilterViewModel = new UsersFilterViewModel();
        listModel = new DefaultListModel<>();
        initializeUI();
        performLisToBeFiltered();
        this.setVisible(true);

    }

    private void initializeUI() {
        this.setTitle("Filtrare Utilizatori");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        list1 = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        usersFilterViewModel.setListModelUser(listModel);
        this.add(scrollPane, BorderLayout.CENTER);
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        button1 = new JButton("Username");
        button2 = new JButton("Password");
        button3 = new JButton("Type");


        this.add(new JScrollPane(list1), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(textField1);
        panel.add(button1);
        button1.addActionListener(e -> {
            usersFilterViewModel.setUsername(textField1.getText());
            performToFilterUsernameForAdmin();});
        panel.add(textField2);
        panel.add(button2);
        button2.addActionListener(e -> {
                    usersFilterViewModel.setPassword(textField2.getText());
            performToFilterPasswordForAdmin();});
        panel.add(textField3);
        panel.add(button3);
        button3.addActionListener(e -> {
            usersFilterViewModel.setUserType(textField3.getText());
            performToFilterUserTypeForAdmin();});

        this.add(panel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void performLisToBeFiltered()
    {
        usersFilterViewModel.ListUsersToBeFiltered();
    }

    private void performToFilterUsernameForAdmin()
    {
        usersFilterViewModel.setUsername(textField1.getText());
        usersFilterViewModel.ToFilterUsernameForAdmin();
        list1.setModel(usersFilterViewModel.getListModelUser());
    }

    private void performToFilterPasswordForAdmin()
    {
        usersFilterViewModel.setPassword(textField2.getText());
        usersFilterViewModel.ToFilterPasswordForAdmin();
        list1.setModel(usersFilterViewModel.getListModelUser());
    }

    private void performToFilterUserTypeForAdmin()
    {
        usersFilterViewModel.setUserType(textField3.getText());
        usersFilterViewModel.ToFilterUserTypeForAdmin();
        list1.setModel(usersFilterViewModel.getListModelUser());

    }


}
