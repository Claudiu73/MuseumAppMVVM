package View;

import Model.ArtWork;
import Model.User;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import Repo.UserRepository;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class AdminUI extends JFrame {
    private JList<String> list1;
    private JList<String> list2;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton adaugaButton, stergeButton, cautaButton, actualizeazaButton, cautaOperaButton;

    private DefaultListModel<String> listModel;

    public AdminUI() {
        listModel = new DefaultListModel<>();
        initializeUI();
        fetchAndDisplayArtworks();
        fetchAndDisplayUsers();
    }

    private void initializeUI() {
        setTitle("Interfață Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4, 4);

        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        initializeLists(gbc);
        initializeTextFieldsAndLabels(gbc);
        initializeButtonsCentered(gbc);


        pack();
        setLocationRelativeTo(null); // Centrează fereastra pe ecran
    }

    private void initializeLists(GridBagConstraints gbc) {
        list1 = new JList<>(listModel); // Utilizează modelul inițializat
        JScrollPane scrollPaneArtworks = new JScrollPane(list1);

        list2 = new JList<>();
        JScrollPane scrollPaneUsers = new JScrollPane(list2);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 5;
        gbc.weightx = 0.25; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        add(scrollPaneArtworks, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0.25;
        add(scrollPaneUsers, gbc);

        gbc.gridheight = 1; gbc.weightx = 0; // Reset
    }

    private void initializeTextFieldsAndLabels(GridBagConstraints gbc) {
        String[] labels = {"Username:", "Password:", "Type:", "Lista Opere:"};
        JTextField[] textFields = {textField1 = new JTextField(20), textField2 = new JTextField(20),
                textField3 = new JTextField(20), textField4 = new JTextField(20)};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 1;
            gbc.gridy = i;
            add(new JLabel(labels[i]), gbc);

            gbc.gridx = 2;
            gbc.gridwidth = 2;
            add(textFields[i], gbc);
        }
    }

    private void initializeButtonsCentered(GridBagConstraints gbc) {
        // Create a panel for buttons to align them in center
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        adaugaButton = new JButton("Adaugă");
        stergeButton = new JButton("Șterge");
        cautaButton = new JButton("Caută User");
        actualizeazaButton = new JButton("Actualizează");
        cautaOperaButton = new JButton("Caută Opera");

        adaugaButton.addActionListener(e -> onAddUserClicked());
        actualizeazaButton.addActionListener(e -> fetchAndDisplayUsers());
        stergeButton.addActionListener(e -> addDeleteUserListener());
        cautaButton.addActionListener(e -> addSearchUserListener());
        cautaOperaButton.addActionListener(e -> filterArtworks());



        // Add buttons to the panel
        buttonPanel.add(adaugaButton);
        buttonPanel.add(stergeButton);
        buttonPanel.add(cautaButton);
        buttonPanel.add(actualizeazaButton);
        buttonPanel.add(cautaOperaButton);

        // Add the button panel to the grid, spanning across columns
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);
    }

    private void onAddUserClicked() {
        String username = textField1.getText().trim();
        String password = textField2.getText().trim();
        String usertype = textField3.getText().trim();

        UserRepository userRepository = new UserRepository();

        // Validare date introduse
        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Toate câmpurile trebuie completate!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creează un nou utilizator
        User newUser = new User(username, password, usertype);

        try {
            // Adaugă utilizatorul în baza de date folosind UserRepository

            userRepository.addUser(newUser); // Presupunem că ai o instanță a lui UserRepository accesibilă aici
            JOptionPane.showMessageDialog(this, "Utilizator adăugat cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);

            // Opțional: Actualizează lista de utilizatori afișată
            // fetchAndDisplayUsers();
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this, "Eroare la adăugarea utilizatorului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchAndDisplayUsers() {
        UserRepository userRepository = new UserRepository();
        try {
            // Presupunem că UserRepository are o metodă getAllUsers() care returnează o listă de utilizatori
            List<User> users = userRepository.getAllUsers();
            DefaultListModel<String> userModel = new DefaultListModel<>();

            for (User user : users) {
                // Presupunem că User are o metodă toString() care returnează un String reprezentativ
                userModel.addElement(user.toString());
            }

            list2.setModel(userModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare la încărcarea utilizatorilor: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void fetchAndDisplayArtworks() {

        ArtWorkRepository artWorkRepository = new
                ArtWorkRepository();

        try {

            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            updateList(artworks);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la încărcarea operelor de artă: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addDeleteUserListener() {
        String username = textField1.getText().trim(); // Presupunând că textField1 este folosit pentru introducerea numelui de utilizator

        if(username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vă rugăm introduceți username-ul pentru ștergere.", "Informație lipsă", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        UserRepository userRepository = new UserRepository();
        try {
            User user = userRepository.getUserByUsername(username);
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Utilizatorul nu există.", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int response = JOptionPane.showConfirmDialog(this, "Sunteți sigur că doriți să ștergeți utilizatorul " + username + "?", "Confirmare ștergere", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                userRepository.deleteUser(username);
                JOptionPane.showMessageDialog(this, "Utilizatorul a fost șters cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this, "Eroare la ștergerea utilizatorului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSearchUserListener() {

            String username = textField1.getText().trim(); // Preia username-ul din textField1
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vă rugăm introduceți un username pentru căutare.", "Informație lipsă", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            try {
                UserRepository userRepository = new UserRepository();
                User user = userRepository.getUserByUsername(username);

                if (user != null) {
                    // Afișează detaliile utilizatorului găsit
                    JOptionPane.showMessageDialog(this, "Utilizator găsit: \nUsername: " + user.getUsername() + "\nParolă: " + user.getPassword() + "\nTip: " + user.getUserType(), "Rezultat Căutare", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Utilizatorul nu a fost găsit
                    JOptionPane.showMessageDialog(this, "Nu a fost găsit niciun utilizator cu username-ul " + username + ".", "Utilizator inexistent", JOptionPane.ERROR_MESSAGE);
                }

            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(this, "Eroare la căutarea utilizatorului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }

    }

    private void filterArtworks() {
        ArtWorkRepository artWorkRepository = new ArtWorkRepository();
        String searchText = textField4.getText().trim();
        try {

            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            List<ArtWork> filteredArtworks = artworks.stream()
                    .filter(artwork -> artwork.getArtist().toLowerCase().contains(searchText.toLowerCase())
                            || artwork.getTitle().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
            updateList(filteredArtworks);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la filtrarea operelor de artă: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateList(List<ArtWork> artworks) {
        listModel.clear();
        for (ArtWork artwork : artworks) {
            listModel.addElement(artwork.toString()); // Presupunând că ArtWork are o metodă toString() adecvată
        }
    }



}
