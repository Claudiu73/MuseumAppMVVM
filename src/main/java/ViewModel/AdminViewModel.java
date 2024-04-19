package ViewModel;

import Command.*;
import Repo.ArtWorkRepository;
import Repo.UserRepository;
import View.UsersFilterUI;

import javax.swing.*;

public class AdminViewModel {
    private ArtWorkRepository artWorkRepository;
    private UserRepository userRepository;
    private UsersFilterUI usersFilterUI;
    private String title;
    private String author;
    private Integer year;
    private String type;
    private String username;
    private String password;
    private String userType;
    private DefaultListModel<String> listModelArt = new DefaultListModel<>();
    private JList<String> listArt;
    private DefaultListModel<String> listModelUser = new DefaultListModel<>();
    private JList<String> listUser;
    public CAddUser cAddUser;
    public CDeleteUser cDeleteUser;
    public CSearchUser cSearchUser;
    public CUpdateUser cUpdateUser;
    public CListArtWorksForAdmin cListArtWorksForAdmin;
    public CListUsersForAdmin cListUsersForAdmin;
    public COpenToFilterUsers cOpenToFilterUsers;
    public COpenToFilterListOfArtWorks cOpenToFilterListOfArtWorks;

    public AdminViewModel() {

        this.userRepository = new UserRepository();
        this.artWorkRepository = new ArtWorkRepository();
        this.cAddUser = new CAddUser(userRepository, this);
        this.cDeleteUser = new CDeleteUser(userRepository, this);
        this.cSearchUser = new CSearchUser(userRepository, this);
        this.cUpdateUser = new CUpdateUser(userRepository, this);
        this.cListArtWorksForAdmin = new CListArtWorksForAdmin(artWorkRepository, this);
        this.cListUsersForAdmin = new CListUsersForAdmin(userRepository, this);
        this.cOpenToFilterUsers = new COpenToFilterUsers(this);
        this.cOpenToFilterListOfArtWorks = new COpenToFilterListOfArtWorks(userRepository, this);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public DefaultListModel<String> getListModelArt() {
        return listModelArt;
    }

    public JList<String> getListArt() {
        return listArt;
    }

    public DefaultListModel<String> getListModelUser() {
        return listModelUser;
    }

    public JList<String> getListUser() {
        return listUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setListModelArt(DefaultListModel<String> listModelArt) {
        this.listModelArt = listModelArt;
    }

    public void setListArt(JList<String> listArt) {
        this.listArt = listArt;
    }

    public void setListModelUser(DefaultListModel<String> listModelUser) {
        this.listModelUser = listModelUser;
    }

    public void setListUser(JList<String> listUser) {
        this.listUser = listUser;
    }

    public void AddUser()
    {
        cAddUser.execute();
    }

    public void DeleteUser()
    {
        cDeleteUser.execute();
    }

    public void SearchUser()
    {
        cSearchUser.execute();
    }

    public void UpdateUser()
    {
        cUpdateUser.execute();
    }

    public void ListArtWorks()
    {
        cListArtWorksForAdmin.execute();
    }

    public void ListUsers()
    {
        cListUsersForAdmin.execute();
    }
    public void OpenToFilterUsers()
    {
        cOpenToFilterUsers.execute();
    }

    public void OpenToFilterListOfArtWorks()
    {
        cOpenToFilterListOfArtWorks.execute();
    }

}
