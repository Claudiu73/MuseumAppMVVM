package View;

import Auth.UserAuth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {
    private JTextField textField1;
    private JButton logInButton;
    private JPasswordField passwordField1;
    private String userType; // "angajat" sau "admin"

    public LogIn(String userType) {
        this.userType = userType; // Adaugă acest rând
        setTitle("Autentificare");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        textField1 = new JTextField(20);
        add(textField1);

        passwordField1 = new JPasswordField(20);
        add(passwordField1);

        logInButton = new JButton("Log In");
        add(logInButton);

        // Aici poți adăuga logica pentru butonul de Log In
        logInButton.addActionListener(e -> {
            String username = textField1.getText();
            String password = new String(passwordField1.getPassword());

            // Aici poți adăuga verificarea autentificării
            System.out.println("Încercare de logare...");

            // Presupunem că există o clasă UserAuth pentru autentificare
            boolean isAuthenticated = UserAuth.authenticate(username, password, userType);
            System.out.println(isAuthenticated);

            if (isAuthenticated) {
                if ("angajat".equals(userType)) {
                    EventQueue.invokeLater(() -> {
                        try {
                            EmployeeUI employeeUI = new EmployeeUI();
                            employeeUI.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Eroare la deschiderea ferestrei de angajat: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                } else if ("admin".equals(userType)) {
                    EventQueue.invokeLater(() -> {
                        try {
                            AdminUI adminUI = new AdminUI();
                            adminUI.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Eroare la deschiderea ferestrei de admin: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }

            } else {
                JOptionPane.showMessageDialog(LogIn.this, "Autentificare eșuată", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
