package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import ViewModel.AdminViewModel;
import ViewModel.EmployeeViewModel;

import javax.swing.*;
import java.util.List;

public class CListArtWorksForAdmin implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private AdminViewModel viewModel;

    public CListArtWorksForAdmin(ArtWorkRepository artWorkRepository, AdminViewModel viewModel) {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            SwingUtilities.invokeLater(() -> {
                DefaultListModel<String> listModel = viewModel.getListModelArt();
                listModel.clear(); // Curăță modelul listei
                for (ArtWork artwork : artworks) {
                    listModel.addElement(artwork.toString()); // Adaugă fiecare operă de artă în modelul listei
                    System.out.println(artwork);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
