package View;

import Model.ArtWork;
import Repo.ArtWorkRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ArtWorkListView extends JFrame {
    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JTextField textField1;
    private JButton cautaButton;

    private JScrollPane ScrollPane;

    public ArtWorkListView() {
        setTitle("Lista Opere de Arta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        listModel = new DefaultListModel<>();
        list1 = new JList<>(listModel);
        ScrollPane = new JScrollPane(list1);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Componenta se întinde pe toată lățimea rândului
        gbc.insets = new Insets(10, 10, 10, 10); // Adaugă un spațiu pentru estetică
        add(ScrollPane, gbc);

        gbc.gridwidth = 1; // Resetare la comportamentul implicit
        gbc.gridy++; // Mergem la următorul rând pentru componentele de căutare
        gbc.weighty = 0; // Nu alocăm spațiu suplimentar pe verticală pentru aceste componente
        gbc.weightx = 0.8; // Câmpul de text folosește majoritatea spațiului orizontal
        gbc.fill = GridBagConstraints.HORIZONTAL;

        textField1 = new JTextField();
        add(textField1, gbc);

        gbc.weightx = 0.2; // Butonul folosește mai puțin spațiu orizontal
        gbc.gridx++; // Mutăm butonul pe coloana următoare
        cautaButton = new JButton("Caută");
        add(cautaButton, gbc);

        cautaButton.addActionListener(e -> filterArtworks(textField1.getText()));
        fetchAndDisplayArtworks();
    }

    private void fetchAndDisplayArtworks() {
        try {
            ArtWorkRepository repository = new ArtWorkRepository();
            List<ArtWork> artworks = repository.getAllArtworks();
            updateList(artworks);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la încărcarea operelor de artă: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterArtworks(String searchText) {
        try {
            ArtWorkRepository repository = new ArtWorkRepository();
            List<ArtWork> artworks = repository.getAllArtworks();
            // Filtrează operele de artă bazate pe textul introdus
            List<ArtWork> filteredArtworks = artworks.stream()
                    .filter(artwork -> artwork.getArtist().toLowerCase().contains(searchText.toLowerCase()) || artwork.getTitle().toLowerCase().contains(searchText.toLowerCase()))
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
            listModel.addElement(artwork.getArtist() + " - " + artwork.getTitle());
        }
    }
}
