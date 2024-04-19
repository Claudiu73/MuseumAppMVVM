package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.EmployeeViewModel;

import javax.swing.*;

public class CUpdateArtWork implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;

    private CListArtWorks cListArtWorks;

    public CUpdateArtWork(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel)
    {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
        this.cListArtWorks = new CListArtWorks(artWorkRepository, viewModel);
    }

    @Override
    public void execute() {

        String title = viewModel.getTitle().trim();
        String artist = viewModel.getAuthor().trim();
        int year = Integer.parseInt(viewModel.getYear().toString());
        String type = viewModel.getType().trim();

        ArtWork artworkToBeUpdated;
        try {
            artworkToBeUpdated = artWorkRepository.getArtworkByName(title);

        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la cautarea operei de arta.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (artworkToBeUpdated != null) {
            artworkToBeUpdated.setArtist(artist);
            artworkToBeUpdated.setYear(year);
            artworkToBeUpdated.setType(type);

            try {
                artWorkRepository.updateArtwork(artworkToBeUpdated);
                JOptionPane.showMessageDialog(null, "Opera de arta a fost actualizata cu succes", "Succes", JOptionPane.INFORMATION_MESSAGE);
                cListArtWorks.execute();
            } catch (DAOException e) {
                JOptionPane.showMessageDialog(null, "Eroare la actualizare.", "Eroare", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Opera de arta nu a fost gasita.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
