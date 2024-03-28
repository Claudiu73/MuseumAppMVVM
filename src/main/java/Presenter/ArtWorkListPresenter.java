package Presenter;

import Model.ArtWork;
import Repo.ArtWorkRepository;
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
        String searchText = view.getTextField1().trim();

        try {
            ArtWorkRepository artWorkRepository = new ArtWorkRepository();
            List<ArtWork> allArtworks = artWorkRepository.getAllArtworks();

            List<ArtWork> filteredArtworks = allArtworks.stream()
                    .filter(artwork -> artwork.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                            artwork.getArtist().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
            updateList(filteredArtworks);

        } catch (Exception e) {
            e.printStackTrace();
            showError("Eroare la filtrarea operelor de artă");
        }
    }

    private void updateList(List<ArtWork> artworks) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        //listModel.clear();
        for (ArtWork artwork : artworks) {
            listModel.addElement(artwork.toString());

        }

        view.setListModel(listModel);
        System.out.println("Am ajuns pana aiciii");
    }
    public void setView(IArtWorkUI view) {
        this.view = view;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog((Component) view, message, "Eroare", JOptionPane.ERROR_MESSAGE);
    }

}
