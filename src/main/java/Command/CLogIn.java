package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import View.AdminUI;
import View.EmployeeUI;
import ViewModel.LogInViewModel;

import javax.swing.*;

public class CLogIn implements ICommand {
    private LogInViewModel viewModel;
    private UserRepository userRepository; // Referință pentru repository

    public CLogIn(LogInViewModel viewModel, UserRepository userRepository) {
        this.viewModel = viewModel;
        this.userRepository = userRepository; // Inițializare în constructor
    }

    @Override
    public void execute() {
        String username = viewModel.getUsername();
        String password = viewModel.getPassword();
        String userType = viewModel.getUserType();

        System.out.println("Attempt to login with: " + username + ", " + password + ", " + userType);

        try {
            User user = userRepository.getUserByUsername(username.trim());
            if (user != null && user.getPassword().equals(password.trim()) && user.getUserType().equalsIgnoreCase(userType)) {
                SwingUtilities.invokeLater(() -> {
                    switch (userType.toLowerCase()) {
                        case "angajat":
                            new EmployeeUI().setVisible(true);
                            break;
                        case "admin":
                            new AdminUI().setVisible(true);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Tip de utilizator necunoscut.", "Eroare", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null, "Autentificare eșuată.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la autentificare: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


}
