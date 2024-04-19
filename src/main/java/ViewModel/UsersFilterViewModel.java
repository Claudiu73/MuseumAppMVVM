package ViewModel;

import Command.CListUsersToBeFiltered;
import Command.CToFilterPasswordForAdmin;
import Command.CToFilterUserTypeForAdmin;
import Command.CToFilterUsernameForAdmin;
import Repo.UserRepository;

import javax.swing.*;

public class UsersFilterViewModel {

    private UserRepository userRepository;
    private DefaultListModel<String> listModelUser = new DefaultListModel<>();
    private JList<String> listUser;
    private String username;
    private String password;
    private String userType;
    public CListUsersToBeFiltered cListUsersToBeFiltered;
    public CToFilterUsernameForAdmin cToFilterUsernameForAdmin;
    public CToFilterPasswordForAdmin cToFilterPasswordForAdmin;
    public CToFilterUserTypeForAdmin cToFilterUserTypeForAdmin;
    public UsersFilterViewModel()
    {
        this.userRepository = new UserRepository();
        this.cListUsersToBeFiltered = new CListUsersToBeFiltered(userRepository, this);
        this.cToFilterUsernameForAdmin = new CToFilterUsernameForAdmin(userRepository, this);
        this.cToFilterPasswordForAdmin = new CToFilterPasswordForAdmin(userRepository, this);
        this.cToFilterUserTypeForAdmin = new CToFilterUserTypeForAdmin(userRepository, this);
    }

    public DefaultListModel<String> getListModelUser() {
        return listModelUser;
    }

    public void setListModelUser(DefaultListModel<String> listModelUser) {
        this.listModelUser = listModelUser;
    }

    public JList<String> getListUser() {
        return listUser;
    }

    public void setListUser(JList<String> listUser) {
        this.listUser = listUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void ListUsersToBeFiltered()
    {
        cListUsersToBeFiltered.execute();
    }

    public void ToFilterUsernameForAdmin()
    {
        cToFilterUsernameForAdmin.execute();
    }

    public void ToFilterPasswordForAdmin()
    {
        cToFilterPasswordForAdmin.execute();
    }

    public void ToFilterUserTypeForAdmin()
    {
        cToFilterUserTypeForAdmin.execute();
    }
}
