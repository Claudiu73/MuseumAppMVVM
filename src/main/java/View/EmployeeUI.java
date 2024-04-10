package View;

import Presenter.EmployeePresenter;
import Presenter.IEmployeeUI;

import javax.swing.*;
import java.awt.*;

public class EmployeeUI extends JFrame implements IEmployeeUI {
    private EmployeePresenter employeePresenter;
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton adaugaButton, stergeButton, actualizeazaButton, cautaButton, generareCsvButton, generareJSONButton;
    private JButton generareXMLButton;
    private JButton generareDocButton;
    private JFrame frame;
    private JPanel panel;


    public EmployeeUI() {
        employeePresenter = new EmployeePresenter(this);
        frame = new JFrame("Servicii Angajat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\sirbu\\Desktop\\Cauta\\Faculta 3.2\\PS\\MuseumApp\\FundalAngajat.png");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Servicii Angajat", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(150, 10, 500, 30);
        panel.add(titleLabel);

        initializeComponents(panel);
        employeePresenter.fetchAndDisplayArtworks();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void initializeComponents(JPanel panel) {
        listModel = new DefaultListModel<>();
        list1 = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        scrollPane.setBounds(520, 160, 210, 220);
        panel.add(scrollPane);

        int yPosition = 245;
        Color color = new Color(206, 193, 193);

        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        adaugaButton = new JButton("Adaugă");
        stergeButton = new JButton("Șterge");
        actualizeazaButton = new JButton("Actualizează");
        cautaButton = new JButton("Caută");
        generareCsvButton = new JButton("Generare CSV");
        generareJSONButton = new JButton("Generare JSON");
        generareXMLButton = new JButton("Generare XML");
        generareDocButton = new JButton("Generare DOC");


        adaugaButton.setBackground(color);
        stergeButton.setBackground(color);
        actualizeazaButton.setBackground(color);
        cautaButton.setBackground(color);
        generareCsvButton.setBackground(color);
        generareJSONButton.setBackground(color);
        generareXMLButton.setBackground(color);
        generareDocButton.setBackground(color);

        addLabeledField(panel, "Titlu:", textField1, 233, yPosition);
        addLabeledField(panel, "Artist:", textField2, 233, yPosition + 40);
        addLabeledField(panel, "An:", textField3, 233, yPosition + 80);
        addLabeledField(panel, "Tipul:", textField4, 233, yPosition + 120);

        adaugaButton.setBounds(220, yPosition + 200, 122, 30);
        stergeButton.setBounds(350, yPosition + 200, 122, 30);
        actualizeazaButton.setBounds(480, yPosition + 200, 122, 30);
        cautaButton.setBounds(610, yPosition + 200, 122, 30);
        generareCsvButton.setBounds(220, yPosition + 250, 122, 30);
        generareJSONButton.setBounds(350,yPosition+250, 122, 30);
        generareXMLButton.setBounds(480, yPosition+250, 122, 30);
        generareDocButton.setBounds(610, yPosition+250, 122, 30);

        panel.add(adaugaButton);
        panel.add(stergeButton);
        panel.add(actualizeazaButton);
        panel.add(cautaButton);
        panel.add(generareCsvButton);
        panel.add(generareJSONButton);
        panel.add(generareXMLButton);
        panel.add(generareDocButton);

        adaugaButton.addActionListener(e -> employeePresenter.onAddButtonClicked());
        stergeButton.addActionListener(e -> employeePresenter.onDeleteButtonClicked());
        actualizeazaButton.addActionListener(e -> employeePresenter.onUpdateButtonClicked());
        cautaButton.addActionListener(e -> employeePresenter.onSearchButtonClicked());
        generareCsvButton.addActionListener(e -> employeePresenter.onGenerateCsvButtonClicked());
        generareJSONButton.addActionListener(e -> employeePresenter.onGenerateJSONButtonClicked());
        generareXMLButton.addActionListener(e -> employeePresenter.onGenerateXMLButtonClicked());
        generareDocButton.addActionListener(e -> employeePresenter.onGenerateSimpleDocButtonClicked());
    }

    private void addLabeledField(JPanel panel, String label, JTextField textField, int x, int y) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(x, y, 50, 30);
        textField.setBounds(x + 60, y, 150, 30);
        panel.add(jLabel);
        panel.add(textField);
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