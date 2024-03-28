package Presenter;

import javax.swing.*;

public interface IEmployeeUI {

    public JList<String> getList1();

    public void setList1(JList<String> list1);

    public DefaultListModel<String> getListModel();
    public void setListModel(DefaultListModel<String> listModel);

    public String getTextField1();

    public void setTextField1(String textField1);

    public String getTextField2();
    public void setTextField2(String textField2);

    public String getTextField3();

    public void setTextField3(String textField3);

    public String getTextField4();

    public void setTextField4(String textField4);

}
