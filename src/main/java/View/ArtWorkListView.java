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
    private JTextField textField1, textField2, textField3, textField4;
    private JButton anButton, titluButton, tipButton, artistButton, cautaButton;
    private JFrame frame;
    private JPanel panel;
    private ArtWorkListPresenter artWorkListPresenter;

    public ArtWorkListView() {
        artWorkListPresenter = new ArtWorkListPresenter(this);
        listModel = new DefaultListModel<>();
        frame = new JFrame("Lista Opere de Arta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\sirbu\\Desktop\\Cauta\\Faculta 3.2\\PS\\MuseumApp\\FundalVizitator.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Bine ati venit!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(150, 10, 500, 30);
        panel.add(titleLabel); // Adaugă label-ul la panel

        frame.setContentPane(panel);
        initializeUI();
        artWorkListPresenter.fetchAndDisplayArtworks();

        frame.setVisible(true);
    }

    private void initializeUI() {
        frame.setLayout(null);

        list1 = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        scrollPane.setBounds(20, 200, 350, 150);
        frame.add(scrollPane);

        setupComponents();
    }

    private void setupComponents() {
        JLabel labelTitleFilter = new JLabel("Filtru titlu:");
        labelTitleFilter.setForeground(Color.WHITE);
        labelTitleFilter.setBounds(400, 205, 100, 25);

        textField1 = new JTextField();
        textField1.setBounds(475, 205, 150, 25);

        titluButton = new JButton("Titlu");
        titluButton.setBackground(Color.LIGHT_GRAY);
        titluButton.setBounds(635, 205, 80, 25);

        JLabel labelYearFilter = new JLabel("Filtru an:");
        labelYearFilter.setForeground(Color.WHITE);
        labelYearFilter.setBounds(400, 235, 100, 25);

        textField2 = new JTextField();
        textField2.setBounds(475, 235, 150, 25);

        anButton = new JButton("An");
        anButton.setBackground(Color.LIGHT_GRAY);
        anButton.setBounds(635, 235, 80, 25);

        JLabel labelAuthorFilter = new JLabel("Filtru autor:");
        labelAuthorFilter.setForeground(Color.WHITE);
        labelAuthorFilter.setBounds(400, 275, 100, 25);

        textField3 = new JTextField();
        textField3.setBounds(475, 275, 150, 25);

        artistButton = new JButton("Autor");
        artistButton.setBackground(Color.LIGHT_GRAY);
        artistButton.setBounds(635, 275, 80, 25);

        cautaButton = new JButton("Cauta");
        cautaButton.setBackground(Color.LIGHT_GRAY);
        cautaButton.setBounds(300, 365, 200, 25);

        JLabel labelTypeFilter = new JLabel("Filtru tip:");
        labelTypeFilter.setForeground(Color.WHITE);
        labelTypeFilter.setBounds(400, 305, 100, 25);

        textField4 = new JTextField();
        textField4.setBounds(475, 305, 150, 25);

        tipButton = new JButton("Tip");
        tipButton.setBackground(Color.LIGHT_GRAY);
        tipButton.setBounds(635, 305, 80, 25);

        frame.add(labelTitleFilter);
        frame.add(textField1);
        frame.add(titluButton);
        frame.add(labelYearFilter);
        frame.add(textField2);
        frame.add(anButton);
        frame.add(labelAuthorFilter);
        frame.add(textField3);
        frame.add(artistButton);
        frame.add(labelTypeFilter);
        frame.add(textField4);
        frame.add(tipButton);
        frame.add(cautaButton);

        addEventHandlers();
    }
    private void addEventHandlers() {
        titluButton.addActionListener(onTitleButtonClicked());
        anButton.addActionListener(onYearButtonClicked());
        artistButton.addActionListener(onArtistButtonClicked());
        tipButton.addActionListener(onTypeButtonClicked());
        cautaButton.addActionListener(onSearchButtonClicked());
    }
    public ActionListener onSearchButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artWorkListPresenter.onSearchButtonClicked();
            }
        };
        return e;
    }
    public ActionListener onTitleButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artWorkListPresenter.filterArtworks();
            }
        };
        return e;
    }

    public ActionListener onArtistButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artWorkListPresenter.filterArtworks();
            }
        };
        return e;
    }

    public ActionListener onYearButtonClicked()
    {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artWorkListPresenter.filterArtworks();
            }
        };
        return e;
    }

    public ActionListener onTypeButtonClicked()
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
        this.list1.setModel(listModel);
    }
    public void setTextField1(String textField1) {
        this.textField1.setText(textField1);
    }

    public void setAnButton(JButton anButton) {
        this.anButton = anButton;
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

    public JButton getAnButton() {
        return anButton;
    }

    public String getTextField2() {
        return textField2.getText();
    }

    public String getTextField3() {
        return textField3.getText();
    }

    public String getTextField4() {
        return textField4.getText();
    }

    public void setTextField2(String textField2) {
        this.textField2.setText(textField2);
    }

    public void setTextField3(String textField3) {
        this.textField3.setText(textField3);
    }

    public void setTextField4(String textField4) {
        this.textField4.setText(textField4);
    }

    public void setPresenter(ArtWorkListPresenter presenter) {
        this.artWorkListPresenter = presenter;
    }

    public void showScreen() {
        frame.setVisible(true);
    }
}
