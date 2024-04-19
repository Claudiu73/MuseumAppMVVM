package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import ViewModel.EmployeeViewModel;

import javax.swing.*;
import java.util.List;

public class CListArtWorks implements ICommand {

    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;

    public CListArtWorks(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel) {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }

    @Override
    public void execute() {
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            SwingUtilities.invokeLater(() -> {
                DefaultListModel<String> listModel = viewModel.getListModel();
                listModel.clear();
                for (ArtWork artwork : artworks) {
                    listModel.addElement(artwork.toString());
                    System.out.println(artwork);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
