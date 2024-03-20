package View;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class EmployeeUI extends JFrame {
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton adaugaButton, stergeButton, actualizeazaButton, cautaButton;
    private ArtWorkRepository artWorkRepository;

    public EmployeeUI() {
        artWorkRepository = new ArtWorkRepository();
        initializeUI();
        fetchAndDisplayArtworks();
    }

    private void initializeUI() {
        setTitle("Servicii Angajat");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        listModel = new DefaultListModel<>();
        list1 = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        JLabel lblTitlu = new JLabel("Titlu");
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.insets = new Insets(10,10,10,10);
        add(lblTitlu, gbc);

        textField1 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField1, gbc);

        JLabel lblArtist = new JLabel("Artist");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(lblArtist, gbc);

        textField2 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField2, gbc);

        JLabel lblAn = new JLabel("An");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(lblAn, gbc);

        textField3 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField3, gbc);

        JLabel lblTip = new JLabel("Tipul");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(lblTip, gbc);

        textField4 = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        add(textField4, gbc);

        adaugaButton = new JButton("Adauga");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(adaugaButton, gbc);
        adaugaButton.addActionListener(e -> onAddButtonClicked());

        stergeButton = new JButton("Sterge");
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(stergeButton, gbc);
        stergeButton.addActionListener(e -> onDeleteButtonClicked());

        actualizeazaButton = new JButton("Actualizeaza");
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(actualizeazaButton, gbc);
        actualizeazaButton.addActionListener(e -> fetchAndDisplayArtworks());

        cautaButton = new JButton("Cauta");
        gbc.gridx = 4;
        gbc.gridy = 4;
        add(cautaButton, gbc);
        cautaButton.addActionListener(e -> onSearchButtonClicked());

        pack();
        setLocationRelativeTo(null); // Center on screen
    }

    private void fetchAndDisplayArtworks() {
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            listModel.clear();
            for (ArtWork artwork : artworks) {
                listModel.addElement(artwork.toString()); // Make sure toString() is overridden in ArtWork
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading artworks: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void onAddButtonClicked() {
        // Extrage valorile din câmpurile de text
        String titlu = textField1.getText().trim();
        String artist = textField2.getText().trim();
        int an;
        try {
            an = Integer.parseInt(textField3.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Anul trebuie să fie un număr întreg.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return; // Ieșire din metodă dacă anul nu este un număr valid
        }
        String tip = textField4.getText().trim();

        // Validează valorile extrase (optional)
        if (titlu.isEmpty() || artist.isEmpty() || tip.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Toate câmpurile trebuie completate.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creează o nouă operă de artă și adaugă-o în baza de date
        ArtWork newArtWork = new ArtWork(titlu, artist, an, tip);
        try {
            artWorkRepository.addArtwork(newArtWork);
            JOptionPane.showMessageDialog(this, "Opera de artă a fost adăugată cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare la adăugarea operei de artă: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void onDeleteButtonClicked() {
        // Extrage titlul operei de artă de șters din textField1
        String titleToDelete = textField1.getText().trim();
        if (titleToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vă rugăm introduceți titlul operei de artă pentru ștergere.", "Informație lipsă", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Confirmă ștergerea cu utilizatorul
        int response = JOptionPane.showConfirmDialog(this, "Sunteți sigur că doriți să ștergeți opera de artă cu titlul: " + titleToDelete + "?", "Confirmare ștergere", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try {
                // Șterge opera de artă din baza de date
                artWorkRepository.deleteArtwork(titleToDelete);
                JOptionPane.showMessageDialog(this, "Opera de artă a fost ștearsă cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);

            } catch (DAOException daoException) {
                JOptionPane.showMessageDialog(this, "Eroare la ștergerea operei de artă: " + daoException.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                daoException.printStackTrace();
            }
        }
    }

    private void onSearchButtonClicked() {
        // Extrage titlul introdus de utilizator
        String searchTitle = textField1.getText().trim();
        if (searchTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vă rugăm introduceți titlul operei de artă pentru căutare.", "Informație lipsă", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            // Caută opera de artă după titlu
            ArtWork searchedArtwork = artWorkRepository.getArtworkByName(searchTitle);

            // Dacă nu se găsește opera de artă
            if (searchedArtwork == null) {
                JOptionPane.showMessageDialog(this, "Nu s-a găsit nicio operă de artă cu titlul: " + searchTitle, "Rezultat căutare", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Afișează detaliile operei de artă găsite
                JOptionPane.showMessageDialog(this, "Detalii opera de artă găsită:\nTitlu: " + searchedArtwork.getTitle() + "\nArtist: " + searchedArtwork.getArtist() + "\nAn: " + searchedArtwork.getYear() + "\nTip: " + searchedArtwork.getType(), "Rezultat căutare", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DAOException daoException) {
            JOptionPane.showMessageDialog(this, "Eroare la căutarea operei de artă: " + daoException.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            daoException.printStackTrace();
        }
    }

    // Add methods for button click actions here
}
