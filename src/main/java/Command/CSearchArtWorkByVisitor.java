package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.ArtWorkListViewModel;

import javax.swing.*;

public class CSearchArtWorkByVisitor implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private ArtWorkListViewModel viewModel;

    public CSearchArtWorkByVisitor(ArtWorkRepository artWorkRepository, ArtWorkListViewModel viewModel)
    {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
     public void execute() {
        String searchTitle = viewModel.getTitle().trim();
        if (searchTitle.isEmpty()) {
            viewModel.setTitle("Trebuie completat titlul operei de arta!");
            return;
        }
        try {
            ArtWork searchedArtwork = artWorkRepository.getArtworkByName(searchTitle);
            if (searchedArtwork == null) {
               // showError("Eroare la căutarea operei de artă: " + viewModel.getTitle());
                JOptionPane.showMessageDialog(null, "Eroare la cautarea acestei opere de arta.", "Eroare", JOptionPane.ERROR_MESSAGE);

            } else {
                String details = "Titlu: " + searchedArtwork.getTitle() +
                        "\nArtist: " + searchedArtwork.getArtist() +
                        "\nAn: " + searchedArtwork.getYear() +
                        "\nTip: " + searchedArtwork.getType();
                JOptionPane.showMessageDialog(null, details, "Detalii Opera de Artă", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la cautarea acestei opere de arta.", "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
