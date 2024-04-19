package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import ViewModel.AdminViewModel;

import javax.swing.*;

public class CSearchUser implements ICommand{

    private UserRepository userRepository;
    private AdminViewModel viewModel;

    public CSearchUser(UserRepository userRepository, AdminViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {

        String username = viewModel.getUsername().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Introduceti un utilizator.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = userRepository.getUserByUsername(username);

            if (user != null) {
                System.out.println("S-a gasit user-ul:");
                String details = "Username: " + user.getUsername() +
                        "\nPassword: " +user.getPassword() +
                        "\nTip: " + user.getUserType();
                System.out.println(details);
                JOptionPane.showMessageDialog(null, details, "Detalii User", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Eroare la cautarea acestui utilizator.", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(null, "Eroare la cautarea acestui utilizator.", "Eroare", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
