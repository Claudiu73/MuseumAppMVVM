package View;

import Presenter.IUsersFilterUI;
import Presenter.UsersFilterPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UsersFilterUI extends JFrame implements IUsersFilterUI {
    private JList<String> list1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private UsersFilterPresenter usersFilterPresenter;
    private DefaultListModel<String> listModel;

    public UsersFilterUI() {
        usersFilterPresenter = new UsersFilterPresenter(this);
        listModel = new DefaultListModel<>();
        initializeUI();
        usersFilterPresenter.fetchAndDisplayUsers();
        this.setVisible(true);
    }

    private void initializeUI() {
        this.setTitle("Filtrare Utilizatori");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        list1 = new JList<>(listModel);
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
        button1.addActionListener(onUsernameButtonClicked());
        panel.add(textField2);
        panel.add(button2);
        button2.addActionListener(onPasswordButtonClicked());
        panel.add(textField3);
        panel.add(button3);
        button3.addActionListener(onTypeUserButtonClicked());

        this.add(panel, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    public ActionListener onUsernameButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersFilterPresenter.filterUsers();
            }
        };
        return e;
    }

    public ActionListener onPasswordButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersFilterPresenter.filterUsers();
            }
        };
        return e;
    }

    public ActionListener onTypeUserButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersFilterPresenter.filterUsers();
            }
        };
        return e;
    }
    public JList getList1() {
        return list1;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public String getTextField2() {
        return textField2.getText();
    }

    public String getTextField3() {
        return textField3.getText();
    }

    public void setList1(DefaultListModel<String> model) {
        list1.setModel(model);
    }
    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public DefaultListModel<String> getUserListModel() {
        return listModel;
    }
    public void setUserListModel(DefaultListModel<String> userListModel) {
        this.listModel = listModel;
    }

}
