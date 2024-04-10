package Presenter;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ArtWorkListPresenter {
    private IArtWorkUI view;
    private ArtWorkRepository artWorkRepository;

    public ArtWorkListPresenter(IArtWorkUI view) {

        this.view = view;
        this.artWorkRepository = new ArtWorkRepository();
    }

    public void fetchAndDisplayArtworks()
    {
        ArtWorkRepository artWorkRepository = new ArtWorkRepository();
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            DefaultListModel<String> listModel = new DefaultListModel<>();

            for (ArtWork artwork : artworks)
            {
                listModel.addElement(artwork.toString());
            }

            view.getList1().setModel(listModel);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Eroare la încărcarea operelor de artă: " + e.getMessage());
        }
    }

    public void filterArtworks() {
        try {
            List<ArtWork> allArtworks = artWorkRepository.getAllArtworks();
            DefaultListModel<String> model = new DefaultListModel<>();

            String titleFilter = view.getTextField1().trim();
            String artistFilter = view.getTextField2().trim();
            String yearFilter = view.getTextField3().trim();
            String typeFilter = view.getTextField4().trim();

            List<ArtWork> filteredArtworks = allArtworks.stream()
                    .filter(artwork -> artwork.getTitle().equalsIgnoreCase(titleFilter) || titleFilter.isEmpty())
                    .filter(artwork -> artwork.getArtist().equalsIgnoreCase(artistFilter) || artistFilter.isEmpty())
                    .filter(artwork -> String.valueOf(artwork.getYear()).equals(yearFilter) || yearFilter.isEmpty())
                    .filter(artwork -> artwork.getType().equalsIgnoreCase(typeFilter) || typeFilter.isEmpty())
                    .collect(Collectors.toList());

            for (ArtWork artwork : filteredArtworks) {
                model.addElement(artwork.toString());
            }

            view.setListModel(model);
        } catch (DAOException e) {
            showError("Eroare la filtrarea operelor de artă: " + e.getMessage());
        }
    }

    public void onSearchButtonClicked() {
        String searchTitle = view.getTextField1().trim();
        if (searchTitle.isEmpty()) {
            view.setTextField1("Trebuie completat titlul operei de arta!");
            return;
        }
        try {
            ArtWork searchedArtwork = artWorkRepository.getArtworkByName(searchTitle);
            if (searchedArtwork == null) {
                showError("Eroare la căutarea operei de artă: " + view.getTextField1());
            } else {
                String details = "Titlu: " + searchedArtwork.getTitle() +
                        "\nArtist: " + searchedArtwork.getArtist() +
                        "\nAn: " + searchedArtwork.getYear() +
                        "\nTip: " + searchedArtwork.getType();
                showArtWorkFoundDetails(details);
            }
        } catch (DAOException e) {
            showError("Eroare la căutarea operei de artă");
            e.printStackTrace();
        }
    }

    public void setView(IArtWorkUI view) {
        this.view = view;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog((Component) view, message, "Eroare", JOptionPane.ERROR_MESSAGE);
    }

    public void showArtWorkFoundDetails(String details) {
        JOptionPane.showMessageDialog((Component) view, details, "Detalii Opera de Artă", JOptionPane.INFORMATION_MESSAGE);
    }

}
