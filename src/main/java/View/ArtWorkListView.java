package View;

import ViewModel.ArtWorkListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArtWorkListView extends JFrame {
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton anButton, titluButton, tipButton, artistButton, cautaButton;
    private JFrame frame;
    private JPanel panel;
    private ArtWorkListViewModel artWorkListViewModel;

    public ArtWorkListView() {
        artWorkListViewModel = new ArtWorkListViewModel();
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
        performListArtWorksForVisitor();

        frame.setVisible(true);
    }

    private void initializeUI() {
        frame.setLayout(null);

        listModel = new DefaultListModel<>();
        list1 = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(list1);
        artWorkListViewModel.setListModelArts(listModel);
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

        JLabel labelYearFilter = new JLabel("Filtru autor:");
        labelYearFilter.setForeground(Color.WHITE);
        labelYearFilter.setBounds(400, 235, 100, 25);

        textField2 = new JTextField();
        textField2.setBounds(475, 235, 150, 25);

        anButton = new JButton("Autor");
        anButton.setBackground(Color.LIGHT_GRAY);
        anButton.setBounds(635, 235, 80, 25);

        JLabel labelAuthorFilter = new JLabel("Filtru an:");
        labelAuthorFilter.setForeground(Color.WHITE);
        labelAuthorFilter.setBounds(400, 275, 100, 25);

        textField3 = new JTextField();
        textField3.setBounds(475, 275, 150, 25);

        artistButton = new JButton("An");
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
        titluButton.addActionListener(e -> {
            artWorkListViewModel.setTitle(textField1.getText());
            performToFilterTitleForVisitor();});
        anButton.addActionListener(e -> {
            artWorkListViewModel.setAuthor(textField2.getText());
            performToFilterAuthorForVisitor();});
        artistButton.addActionListener(e -> {
            artWorkListViewModel.setYear(Integer.parseInt(textField3.getText().trim()));
            performToFilterYearForVisitor();});
        tipButton.addActionListener(e -> {
            artWorkListViewModel.setType(textField4.getText());
            performToFilterTypeForVisitor();});
        cautaButton.addActionListener(e -> {
            artWorkListViewModel.setTitle(textField1.getText());
            performSearchArtWorkByVisitor();
        });
    }

    private void performSearchArtWorkByVisitor()
    {
        artWorkListViewModel.SearchArtWorkByVisitor();
    }

    private void performListArtWorksForVisitor()
    {
        artWorkListViewModel.ListArtWorksForVisitor();
    }

    private void performToFilterTitleForVisitor() {
        artWorkListViewModel.setTitle(textField1.getText());
        artWorkListViewModel.ToFilterTitleForVisitor();
        list1.setModel(artWorkListViewModel.getListModelArts());
    }

    private void performToFilterAuthorForVisitor()
    {
        artWorkListViewModel.setAuthor(textField2.getText());
        artWorkListViewModel.ToFilterAuthorForVisitor();
        list1.setModel(artWorkListViewModel.getListModelArts());
    }


    private void performToFilterYearForVisitor()
    {
        artWorkListViewModel.setYear(Integer.parseInt(textField3.getText()));
        artWorkListViewModel.ToFilterYearForVisitor();
        list1.setModel(artWorkListViewModel.getListModelArts());
    }

    private void performToFilterTypeForVisitor()
    {
        artWorkListViewModel.setType(textField4.getText());
        artWorkListViewModel.ToFilterTypeForVisitor();
        list1.setModel(artWorkListViewModel.getListModelArts());
    }
}
