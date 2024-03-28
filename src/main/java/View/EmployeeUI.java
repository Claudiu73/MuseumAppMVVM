package View;

import Presenter.EmployeePresenter;
import Presenter.IEmployeeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeUI extends JFrame implements IEmployeeUI {
    private EmployeePresenter employeePresenter;
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton adaugaButton, stergeButton, actualizeazaButton, cautaButton;


    public EmployeeUI() {
        employeePresenter = new EmployeePresenter(this);
        initializeUI();
        employeePresenter.fetchAndDisplayArtworks();
    }

    private void initializeUI() {
        setTitle("Servicii Angajat");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        listModel = new DefaultListModel<>();
        list1 = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        JLabel lblTitlu = new JLabel("Titlu");
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.insets = new Insets(10,10,10,10);
        add(lblTitlu, gbc);

        textField1 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField1, gbc);

        JLabel lblArtist = new JLabel("Artist");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(lblArtist, gbc);

        textField2 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField2, gbc);

        JLabel lblAn = new JLabel("An");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(lblAn, gbc);

        textField3 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField3, gbc);

        JLabel lblTip = new JLabel("Tipul");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(lblTip, gbc);

        textField4 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField4, gbc);

        adaugaButton = new JButton("Adauga");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(adaugaButton, gbc);
        adaugaButton.addActionListener(onAddButtonClicked());

        stergeButton = new JButton("Sterge");
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(stergeButton, gbc);
        stergeButton.addActionListener(onDeleteButtonClicked());

        actualizeazaButton = new JButton("Actualizeaza");
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(actualizeazaButton, gbc);
        actualizeazaButton.addActionListener(fetchAndDisplayArtworks());

        cautaButton = new JButton("Cauta");
        gbc.gridx = 4;
        gbc.gridy = 4;
        add(cautaButton, gbc);
        cautaButton.addActionListener(onSearchButtonClicked());

        pack();
        setLocationRelativeTo(null);
    }


    public ActionListener onAddButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.onAddButtonClicked();
            }
        };
        return e;
    }

    public ActionListener onDeleteButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.onDeleteButtonClicked();
            }
        };
        return e;
    }

    public ActionListener fetchAndDisplayArtworks()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.fetchAndDisplayArtworks();
            }
        };
        return e;
    }


    public ActionListener onSearchButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.onSearchButtonClicked();
            }
        };
        return e;
    }


    public JList<String> getList1() {
        return list1;
    }

    public void setList1(JList<String> list1) {
        this.list1 = list1;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public void setTextField1(String textField1) {
        this.textField1.setText( textField1);
    }

    public String getTextField2() {
        return textField2.getText();
    }

    public void setTextField2(String textField2) {
        this.textField2.setText(textField2);
    }

    public String getTextField3() {
        return textField3.getText();
    }

    public void setTextField3(String textField3) {
        this.textField3.setText(textField3);
    }

    public String getTextField4() {
        return textField4.getText();
    }

    public void setTextField4(String textField4) {
        this.textField4.setText(textField4);
    }

}