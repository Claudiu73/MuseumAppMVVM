package View;

import javax.swing.*;

public class EmployeeUI extends JFrame {
    private JList<String> list1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton adaugaButton;
    private JButton stergeButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton cautaButton;
    private JButton actualizeazaButton;

    public EmployeeUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Interfață Angajat");
        setSize(600, 400); // Setează dimensiunile ferestrei
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Închide fereastra când utilizatorul apasă pe X
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Setează layout manager

        list1 = new JList<>();
        JScrollPane scrollPane = new JScrollPane(list1); // Învelește JList în JScrollPane
        add(scrollPane);

        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();

        adaugaButton = new JButton("Adaugă");
        stergeButton = new JButton("Șterge");
        cautaButton = new JButton("Caută");
        actualizeazaButton = new JButton("Actualizează");

        JPanel panel = new JPanel(); // Folosește un panel pentru a grupa textfield-urile și butoanele
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(adaugaButton);
        panel.add(stergeButton);
        panel.add(cautaButton);
        panel.add(actualizeazaButton);

        add(panel); // Adaugă panel-ul la fereastra principală
    }
}
