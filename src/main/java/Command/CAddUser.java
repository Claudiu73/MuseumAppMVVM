package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import ViewModel.AdminViewModel;

import javax.swing.*;

public class CAddUser implements ICommand{

    private UserRepository userRepository;
    private AdminViewModel viewModel;

    public CAddUser(UserRepository userRepository, AdminViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        String username = viewModel.getUsername().trim();
        String password = viewModel.getPassword().trim();
        String usertype = viewModel.getUserType().trim();

        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty()) {
            viewModel.setUsername("Trebuie completat username-ul!");
            viewModel.setPassword("Trebuie completata parola!");
            viewModel.setUserType("Trebuie completat tipul de utilizator!");
            JOptionPane.showMessageDialog(null, "Trebuie completate toate spatiile.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User(viewModel.getUsername(), viewModel.getPassword(), viewModel.getUserType());

        try {
            userRepository.addUser(newUser);
            JOptionPane.showMessageDialog(null, "Utilizator adaugat cu succes", "Succes", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Adaugarea a avut succes.");
            viewModel.ListUsers();
            //fetchAndDisplayUsers();
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(null, "Eroare la adaugarea utilizatorului", "Eroare", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
