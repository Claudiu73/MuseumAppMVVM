package Command;

import Repo.ArtWorkRepository;
import ViewModel.EmployeeViewModel;
import Model.ArtWork;

import javax.swing.*;

public class CAddArtWork implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;
    private CListArtWorks cListArtWorks;

    public CAddArtWork(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel)
    {
    this.artWorkRepository = artWorkRepository;
    this.viewModel = viewModel;
    this.cListArtWorks = new CListArtWorks(artWorkRepository, viewModel);}

    @Override
    public void execute() {

        ArtWork newArtWork = new ArtWork(viewModel.getTitle(), viewModel.getAuthor(), viewModel.getYear(), viewModel.getType());
        try {
            artWorkRepository.addArtwork(newArtWork);
            System.out.println("Opera de artă a fost adăugată cu succes.");
            cListArtWorks.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Trebuie completate toate spatiile", "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

