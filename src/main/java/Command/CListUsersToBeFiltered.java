package Command;

import Model.User;
import Repo.UserRepository;
import ViewModel.UsersFilterViewModel;

import javax.swing.*;
import java.util.List;

public class CListUsersToBeFiltered implements ICommand{

    private UserRepository userRepository;
    private UsersFilterViewModel viewModel;

    public CListUsersToBeFiltered(UserRepository userRepository, UsersFilterViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        try {
            List<User> users = userRepository.getAllUsers();
            SwingUtilities.invokeLater(()-> {
                DefaultListModel<String> userModel = viewModel.getListModelUser();
                userModel.clear();
                for (User user : users) {
                    userModel.addElement(user.toString());
                    System.out.println(user);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
