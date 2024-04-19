package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.EmployeeViewModel;

import javax.swing.*;

public class CSearchArtWork implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;

    public CSearchArtWork(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel)
    {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        String searchTitle = viewModel.getTitle().trim();
        if (searchTitle.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trebuie completat titlul!", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            ArtWork searchedArtwork = artWorkRepository.getArtworkByName(searchTitle);
            if (searchedArtwork == null) {
                JOptionPane.showMessageDialog(null, "Nu s-a gasit titlul.", "Eroare", JOptionPane.ERROR_MESSAGE);
            } else {
                String details = "Titlu: " + searchedArtwork.getTitle() +
                        "\nArtist: " + searchedArtwork.getArtist() +
                        "\nAn: " + searchedArtwork.getYear() +
                        "\nTip: " + searchedArtwork.getType();
                System.out.println(details);
                JOptionPane.showMessageDialog(null, details, "Detalii Opera de Artă", JOptionPane.INFORMATION_MESSAGE);
            }
            } catch (DAOException ex) {
            throw new RuntimeException(ex);
        }
        }
    }

