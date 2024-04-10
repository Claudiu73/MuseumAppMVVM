package Presenter;

import javax.swing.*;

public interface IUsersFilterUI {
    public JList getList1();

    public String getTextField1();

    public String getTextField2();

    public String getTextField3();

    public void setList1(DefaultListModel<String> model);
    public void setTextField1(JTextField textField1);

    public void setTextField2(JTextField textField2);

    public void setTextField3(JTextField textField3);

    public DefaultListModel<String> getUserListModel();

    public void setUserListModel(DefaultListModel<String> userListModel);
}
