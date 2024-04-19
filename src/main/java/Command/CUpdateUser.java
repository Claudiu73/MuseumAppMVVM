package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import ViewModel.AdminViewModel;

import javax.swing.*;

public class CUpdateUser implements ICommand{

    private UserRepository userRepository;
    private AdminViewModel viewModel;

    public CUpdateUser(UserRepository userRepository, AdminViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }

    @Override
    public void execute()
    {
        String username = viewModel.getUsername().trim();
        String password = viewModel.getPassword().trim();
        String type = viewModel.getUserType().trim();

        User userToBeUpdated;
        try{
            userToBeUpdated = userRepository.getUserByUsername(username);
        }
        catch(DAOException e)
        {
            JOptionPane.showMessageDialog(null, "Eroare la cautarea username-ului.", "Eroare", JOptionPane.ERROR_MESSAGE);

            return;
        }

        if(userToBeUpdated != null)
        {
            userToBeUpdated.setPassword(password);
            userToBeUpdated.setUserType(type);

            try{
                userRepository.updateUser(userToBeUpdated);
                System.out.println("User Actualizat.");
                JOptionPane.showMessageDialog(null, "Userul actualizat cu succes.", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                viewModel.ListUsers();
            }
            catch(DAOException e) {
                JOptionPane.showMessageDialog(null, "Actualizarea a esuat", "Eroare", JOptionPane.ERROR_MESSAGE);

                e.printStackTrace();
            }
        }else
            {
                JOptionPane.showMessageDialog(null, "User-ul nu a fost gasit.", "Eroare", JOptionPane.ERROR_MESSAGE);

            }
    }
}
