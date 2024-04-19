package Command;

import Model.User;
import Repo.UserRepository;
import ViewModel.AdminViewModel;

import javax.swing.*;
import java.util.List;

public class CListUsersForAdmin implements ICommand{

    private UserRepository userRepository;
    private AdminViewModel viewModel;

    public CListUsersForAdmin(UserRepository userRepository, AdminViewModel viewModel)
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
