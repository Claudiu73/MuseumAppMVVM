package Command;

import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.EmployeeViewModel;

import javax.swing.*;

public class CDeleteArtWork implements ICommand {
    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;
    private CListArtWorks cListArtWorks;

    public CDeleteArtWork(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel) {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
        this.cListArtWorks = new CListArtWorks(artWorkRepository, viewModel);
    }

    @Override
    public void execute() {
        String titleToDelete = viewModel.getTitle().trim();
        if (titleToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vă rugăm introduceți titlul operei de artă pentru ștergere.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(null, "Sunteți sigur că doriți să ștergeți opera de artă cu titlul: '" + titleToDelete + "'?", "Confirmare ștergere", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try {
                artWorkRepository.deleteArtwork(titleToDelete);
                JOptionPane.showMessageDialog(null, "Opera de artă a fost ștearsă cu succes.", "Ștergere Reușită", JOptionPane.INFORMATION_MESSAGE);
                cListArtWorks.execute();
            } catch (DAOException e) {
                JOptionPane.showMessageDialog(null, "Eroare la ștergerea operei de artă: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
