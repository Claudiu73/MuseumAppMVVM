package Command;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;
import View.UsersFilterUI;
import ViewModel.UsersFilterViewModel;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CToFilterUserTypeForAdmin implements ICommand{

    private UserRepository userRepository;
    private UsersFilterViewModel viewModel;
    public CToFilterUserTypeForAdmin(UserRepository userRepository, UsersFilterViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        try {
            List<User> allUsers = userRepository.getAllUsers();
            DefaultListModel<String> model = new DefaultListModel<>();

            String userTypeFilter = viewModel.getUserType().trim().toLowerCase();

            List<User> filteredUsers = allUsers.stream()
                    .filter(user -> user.getUserType().toLowerCase().contains(userTypeFilter) || userTypeFilter.isEmpty())
                    .collect(Collectors.toList());

            for (User user : filteredUsers) {
                model.addElement(user.toString());
            }

            viewModel.setListModelUser(model);
        } catch (DAOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Eroare la filtrarea utilizatorilor dupa tip.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

}
