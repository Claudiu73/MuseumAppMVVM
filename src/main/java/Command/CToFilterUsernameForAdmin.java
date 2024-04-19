package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import ViewModel.UsersFilterViewModel;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CToFilterUsernameForAdmin implements ICommand{

    private UserRepository userRepository;
    private UsersFilterViewModel viewModel;

    public CToFilterUsernameForAdmin(UserRepository userRepository, UsersFilterViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }

    @Override
    public void execute() {
        try {
            List<User> allUsers = userRepository.getAllUsers();
            DefaultListModel<String> model = new DefaultListModel<>();

            String usernameFilter = viewModel.getUsername().trim().toLowerCase();

            List<User> filteredUsers = allUsers.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(usernameFilter) || usernameFilter.isEmpty())
                    .collect(Collectors.toList());

            for (User user : filteredUsers) {
                model.addElement(user.toString());
            }

            viewModel.setListModelUser(model);
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la filtrarea utilizatorilor.", "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
