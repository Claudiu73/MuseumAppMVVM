package Presenter;

import Model.ArtWork;

import javax.swing.*;
import java.util.List;

public interface IArtWorkUI {

    public void setList1(JList<String> list1) ;
    public void setListModel(DefaultListModel<String> listModel);

    public void setTextField1(String textField1);

    public void setCautaButton(JButton cautaButton) ;


    public JList<String> getList1() ;

    public DefaultListModel<String> getListModel() ;

    public String getTextField1() ;

    public JButton getCautaButton();


}
