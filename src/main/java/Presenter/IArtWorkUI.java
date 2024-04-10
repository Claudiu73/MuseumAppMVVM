package Presenter;

import Model.ArtWork;

import javax.swing.*;
import java.util.List;

public interface IArtWorkUI {

    public void setList1(JList<String> list1) ;
    public void setListModel(DefaultListModel<String> listModel);

    public void setTextField1(String textField1);

    public void setAnButton(JButton anButton) ;


    public JList<String> getList1() ;

    public DefaultListModel<String> getListModel() ;

    public String getTextField1() ;

    public JButton getAnButton();

    public String getTextField2();
    public String getTextField3();

    public String getTextField4();

    public void setTextField2(String textField2);

    public void setTextField3(String textField3);

    public void setTextField4(String textField4);

    public void setPresenter(ArtWorkListPresenter presenter);
    public void showScreen();

}
