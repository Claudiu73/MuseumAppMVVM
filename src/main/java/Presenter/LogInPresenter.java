package Presenter;

import Repo.DAOException;
import Repo.UserRepository;
import Model.User;
import View.AdminUI;
import View.EmployeeUI;
import View.LogIn;

import javax.swing.*;
import java.awt.*;


public class LogInPresenter {

    private ILogInUI view;

    private UserRepository userRepository;


    public LogInPresenter(ILogInUI view)
    {
        this.view = view;
        this.userRepository = new UserRepository();
    }
    public static boolean authenticate(String username, String password, String userType) {
        UserRepository userRepository = new UserRepository();

        try {
            User user = userRepository.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password) && user.getUsername().equals(username) && user.getUserType().equalsIgnoreCase(userType)) {

                return true;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void attemptLogin() {
        String username = view.getTextField1().trim();
        String password = new String(view.getPasswordField1().trim());
        String userType = view.getUserType();
        System.out.println("Încercare de logare...");

        boolean isAuthenticated = LogInPresenter.authenticate(username, password, userType);
        System.out.println(isAuthenticated);

        if (isAuthenticated) {
            if ("angajat".equals(userType)) {
                openEmployeeUI();
            } else if ("admin".equals(userType)) {
                openAdminUI();
            }
        } else {
            showMessageError("Autentificare esuata.");
        }
    }

    public void openEmployeeUI() {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeUI employeeUI = new EmployeeUI();
                employeeUI.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessageError("Eroare la conectarea Angajatului");
            }
        });
    }

    public void openAdminUI() {
        EventQueue.invokeLater(() -> {
            try {
                AdminUI adminUI = new AdminUI();
                adminUI.showScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessageError("Eroare la conectarea Admin-ului");
            }
        });
    }

    public void showMessageError(String message) {
        if (view != null) {
            JOptionPane.showMessageDialog((Component) view, message, "Eroare!", JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println("Error: view is not initialized.");
        }
    }

}