package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import ViewModel.ArtWorkListViewModel;

import javax.swing.*;
import java.util.List;

public class CListArtWorksForVisitor implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private ArtWorkListViewModel viewModel;

    public CListArtWorksForVisitor(ArtWorkRepository artWorkRepository, ArtWorkListViewModel viewModel)
    {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute()
    {
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            SwingUtilities.invokeLater(() -> {
                DefaultListModel<String> listModel = viewModel.getListModelArts();
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
