package Presenter;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmployeePresenter {
    private IEmployeeUI view;
    private ArtWorkRepository artWorkRepository;

    public EmployeePresenter(IEmployeeUI view) {
        this.view = view;
        this.artWorkRepository = new ArtWorkRepository();
    }
    public void fetchAndDisplayArtworks() {
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            view.getListModel().clear();
            for (ArtWork artwork : artworks) {
                view.getListModel().addElement(artwork.toString());
            }
        } catch (Exception e) {
            showErrorMessage("Eroare la actualizarea listei." + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onAddButtonClicked() {
        String titlu = view.getTextField1().trim();
        String artist = view.getTextField2().trim();
        int an;
        try {
            an = Integer.parseInt(view.getTextField3().trim());
        } catch (NumberFormatException e) {
            showErrorMessage("Anul trebuie să fie un număr întreg.");
            return;
        }
        String tip = view.getTextField4().trim();

        if (titlu.isEmpty() || artist.isEmpty() || tip.isEmpty()) {
            view.setTextField1("Trebuie completat titlul operei de arta!");
            view.setTextField2("Trebuie completat artistul operei de arta!");
            view.setTextField3("Trebuie completat anul crearii operei de arta!");
            view.setTextField4("Trebuie completat tipul operei de arta!");
            return;
        }

        ArtWork newArtWork = new ArtWork(titlu, artist, an, tip);
        try {
            artWorkRepository.addArtwork(newArtWork);
            showMessage("Opera de artă a fost adăugată cu succes.");
        } catch (Exception e) {
            showErrorMessage("Eroare la adăugarea operei de artă.");
            e.printStackTrace();
        }
    }

    public void onDeleteButtonClicked() {

        String titleToDelete = view.getTextField1().trim();
        if (titleToDelete.isEmpty()) {
            showMessage("Vă rugăm introduceți titlul operei de artă pentru ștergere.");
            view.setTextField1("Trebuie completat titlul operei de arta!");
            return;
        }

        int response = JOptionPane.showConfirmDialog((Component) view, "Sunteți sigur că doriți să ștergeți opera de artă cu titlul: " + titleToDelete + "?", "Confirmare ștergere", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try {
                artWorkRepository.deleteArtwork(titleToDelete);
                showMessage("Opera de artă a fost ștearsă cu succes.");
            } catch (DAOException daoException) {
                showErrorMessage("Eroare la stergerea operei de arta.");
                daoException.printStackTrace();
            }
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
                showErrorMessage("Eroare la căutarea operei de artă: " + view.getTextField1());
            } else {
                String details = "Titlu: " + searchedArtwork.getTitle() +
                        "\nArtist: " + searchedArtwork.getArtist() +
                        "\nAn: " + searchedArtwork.getYear() +
                        "\nTip: " + searchedArtwork.getType();
                showArtWorkFoundDetails(details);
            }
        } catch (DAOException e) {
            showErrorMessage("Eroare la căutarea operei de artă");
            e.printStackTrace();
        }
    }

    public void showArtWorkFoundDetails(String details) {
        JOptionPane.showMessageDialog((Component) view, details, "Detalii Opera de Artă", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog((Component) view, message, "Eroare", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog((Component) view, message, "Informatie", JOptionPane.INFORMATION_MESSAGE);
    }

}
