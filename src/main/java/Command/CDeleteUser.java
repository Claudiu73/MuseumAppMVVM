package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import View.AdminUI;
import ViewModel.AdminViewModel;

import javax.swing.*;

public class CDeleteUser implements ICommand{

    private UserRepository userRepository;
    private AdminViewModel viewModel;

    public CDeleteUser(UserRepository userRepository, AdminViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        String username = viewModel.getUsername().trim();

        if(username.isEmpty()) {
            //showMessageError("Vă rugăm introduceți username-ul pentru ștergere.");
            return;
        }

        UserRepository userRepository = new UserRepository();
        try {
            User user = userRepository.getUserByUsername(username);
            if (user == null) {
                JOptionPane.showMessageDialog(null, "Utilizatorul nu exista.", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int response = JOptionPane.showConfirmDialog(null, "Sunteți sigur că doriți să ștergeți utilizatorul: '" + username + "'?", "Confirmare stergere utilizator", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                userRepository.deleteUser(username);
                JOptionPane.showMessageDialog(null, "Utilizator sters cu succes.", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Stergerea a avut succes");
            }
            viewModel.ListUsers();
        }
        catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la stergerea utilizatorului.", "Eroare", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
