package Presenter;

import Model.User;
import Repo.DAOException;
import Repo.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class UsersFilterPresenter {

    private IUsersFilterUI view;
    private UserRepository userRepository;

    public UsersFilterPresenter(IUsersFilterUI view)
    {
        this.view = view;
        this.userRepository = new UserRepository();
    }

    public void fetchAndDisplayUsers() {
        UserRepository userRepository = new UserRepository();
        try {
            List<User> users = userRepository.getAllUsers();
            DefaultListModel<String> userModel = new DefaultListModel<>();

            for (User user : users) {
                userModel.addElement(user.toString());
            }

            view.getList1().setModel(userModel);
        } catch (Exception e) {
            showMessageError("Eroare la actualizarea listei de utilizatori.");
            e.printStackTrace();
        }
    }

    public void filterUsers() {
        try {
            List<User> allUsers = userRepository.getAllUsers();
            DefaultListModel<String> model = new DefaultListModel<>();

            String usernameFilter = view.getTextField1().trim();
            String passwordFilter = view.getTextField2().trim();
            String typeFilter = view.getTextField3().trim();

            List<User> filteredUsers = allUsers.stream()
                    .filter(user -> user.getUsername().equalsIgnoreCase(usernameFilter) || usernameFilter.isEmpty())
                    .filter(user -> user.getPassword().equalsIgnoreCase(passwordFilter) || passwordFilter.isEmpty())
                    .filter(user -> user.getUserType().equalsIgnoreCase(typeFilter) || typeFilter.isEmpty())
                    .collect(Collectors.toList());

            for (User user : filteredUsers) {
                model.addElement(user.toString());
            }

            view.setList1(model);
        } catch (DAOException e) {
            showMessageError("Eroare la filtrarea utilizatorilor: " + e.getMessage());
        }
    }


    public void showMessageError(String message) {
        JOptionPane.showMessageDialog((Component)view, message, "Eroare!", JOptionPane.ERROR_MESSAGE);
    }

}
