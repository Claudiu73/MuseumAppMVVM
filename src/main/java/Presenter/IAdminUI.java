package Presenter;

import Model.User;
import javax.swing.*;

public interface IAdminUI {
    public JList<String> getUserList();
    public JList<String> getArtWorkList();
    public void setArtWorkList(JList<String> artWorkList);

    public DefaultListModel<String> getArtWorksList();
    public void setUserList(JList<String> userList);

    public void setArtWorksList(DefaultListModel<String> artWorksList);

    public DefaultListModel<String> getUserListModel();
    public void setUserListModel(DefaultListModel<String> userListModel);

    public String getUsernameField();
    public void setUsernameField(String username);

    public String getPasswordField();
    public void setPasswordField(String password);

    public String getUserTypeField();
    public void setUserTypeField(String userType);

}
