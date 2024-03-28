package View;

import Presenter.ArtWorkListPresenter;
import Presenter.IArtWorkUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArtWorkListView extends JFrame implements IArtWorkUI {
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JTextField textField1;
    private JButton cautaButton;

    private JScrollPane ScrollPane;

    private ArtWorkListPresenter artWorkListPresenter;

    public ArtWorkListView() {

        artWorkListPresenter = new ArtWorkListPresenter(this);
        listModel = new DefaultListModel<>();
        initializeUI();
        artWorkListPresenter.fetchAndDisplayArtworks();
    }

    private void initializeUI() {
        setTitle("Lista Opere de Arta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        listModel = new DefaultListModel<>();
        list1 = new JList<>(listModel);
        ScrollPane = new JScrollPane(list1);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(ScrollPane, gbc);

        textField1 = new JTextField();
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx = 0.8;
        gbc.weighty = 0;
        add(textField1, gbc);

        cautaButton = new JButton("Caută");
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        add(cautaButton, gbc);

        cautaButton.addActionListener(filterArtworks());
    }

    public ActionListener filterArtworks()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artWorkListPresenter.filterArtworks();
            }
        };
        return e;
    }

    public void setList1(JList<String> list1) {
        this.list1 = list1;
    }

    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
        list1.setModel(this.listModel);
    }
    public void setTextField1(String textField1) {
        this.textField1.setText(textField1);
    }

    public void setCautaButton(JButton cautaButton) {
        this.cautaButton = cautaButton;
    }

    public JList<String> getList1() {
        return list1;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public JButton getCautaButton() {
        return cautaButton;
    }


}
