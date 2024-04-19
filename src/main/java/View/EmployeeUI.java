package View;

import ViewModel.EmployeeViewModel;

import javax.swing.*;
import java.awt.*;

public class EmployeeUI extends JFrame {
    private EmployeeViewModel employeeViewModel;
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton adaugaButton, stergeButton, actualizeazaButton, cautaButton, generareCsvButton, generareJSONButton;
    private JButton generareXMLButton;
    private JButton generareDocButton;
    private JFrame frame;
    private JPanel panel;


    public EmployeeUI() {
        employeeViewModel = new EmployeeViewModel();
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
        performListArtWorks();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void initializeComponents(JPanel panel) {
        listModel = new DefaultListModel<>();
        list1 = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        scrollPane.setBounds(520, 160, 210, 220);
        panel.add(scrollPane);


        employeeViewModel.setArtworks(listModel);

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

        adaugaButton.addActionListener(e -> performAddArtWork());
        stergeButton.addActionListener(e -> {
            employeeViewModel.setTitle(textField1.getText());
            performDeleteArtWork();});
        actualizeazaButton.addActionListener(e -> {
            employeeViewModel.setTitle(textField1.getText());
            employeeViewModel.setAuthor(textField2.getText());
            employeeViewModel.setYear(Integer.parseInt(textField3.getText().toString()));
            employeeViewModel.setType(textField4.getText());
            performUpdateArtWork();
        });
        cautaButton.addActionListener(e -> {
            employeeViewModel.setTitle(textField1.getText());
            performSearchArtWork();});
        generareCsvButton.addActionListener(e -> performGenerateCSVFile());
        generareJSONButton.addActionListener(e -> performGenerateJSONFile());
        generareXMLButton.addActionListener(e -> performGenerateXMLFile());
        generareDocButton.addActionListener(e -> perfomGenerateDOCFile());
    }

    private void addLabeledField(JPanel panel, String label, JTextField textField, int x, int y) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(x, y, 50, 30);
        textField.setBounds(x + 60, y, 150, 30);
        panel.add(jLabel);
        panel.add(textField);
    }

    private void performAddArtWork()
    {
        employeeViewModel.setTitle(textField1.getText().trim());
        employeeViewModel.setAuthor(textField2.getText().trim());
        employeeViewModel.setYear(Integer.parseInt(textField3.getText().trim()));
        employeeViewModel.setType(textField4.getText().trim());
        employeeViewModel.AddArtWorkClicked();
    }

    private void performDeleteArtWork()
    {
        employeeViewModel.getTitle();
        employeeViewModel.DeleteArtWorkClicked();
    }

    private void performUpdateArtWork()
    {
        employeeViewModel.getTitle();
        employeeViewModel.UpdateArtWorkClicked();
    }

    private void performSearchArtWork()
    {
        employeeViewModel.getTitle();
        employeeViewModel.SearchArtWorkClicked();
    }

    private void performGenerateCSVFile()
    {
        employeeViewModel.GenerateCSVFile();
    }

    private void performGenerateJSONFile()
    {
        employeeViewModel.GenerateJSONFile();
    }

    private void performGenerateXMLFile()
    {
        employeeViewModel.GenerateXMLFile();
    }

    private void perfomGenerateDOCFile()
    {
        employeeViewModel.GenerateDOCFile();
    }

    private void performListArtWorks()
    {
        employeeViewModel.getArtworks();
        employeeViewModel.getAuthor();
        employeeViewModel.getTitle();
        employeeViewModel.getYear();
        employeeViewModel.getType();
        employeeViewModel.ListArtWorks();
    }

}