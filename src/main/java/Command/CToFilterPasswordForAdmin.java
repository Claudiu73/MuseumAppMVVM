package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import ViewModel.UsersFilterViewModel;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CToFilterPasswordForAdmin implements ICommand{

    private UserRepository userRepository;
    private UsersFilterViewModel viewModel;
    public CToFilterPasswordForAdmin(UserRepository userRepository, UsersFilterViewModel viewModel)
    {
        this.userRepository =userRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        try {
            List<User> allUsers = userRepository.getAllUsers();
            DefaultListModel<String> model = new DefaultListModel<>();

            String passwordFilter = viewModel.getPassword().trim().toLowerCase();

            List<User> filteredUsers = allUsers.stream()
                    .filter(user -> user.getPassword().toLowerCase().contains(passwordFilter) || passwordFilter.isEmpty())
                    .collect(Collectors.toList());

            for (User user : filteredUsers) {
                model.addElement(user.toString());
            }

            viewModel.setListModelUser(model);
        } catch (DAOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Eroare la filtrarea dupa parola.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

}
