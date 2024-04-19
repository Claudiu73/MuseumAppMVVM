package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.ArtWorkListViewModel;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CToFilterTitleForVisitator implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private ArtWorkListViewModel viewModel;

    public CToFilterTitleForVisitator(ArtWorkRepository artWorkRepository, ArtWorkListViewModel viewModel) {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }

    @Override
    public void execute() {
        try {
            List<ArtWork> allArtworks = artWorkRepository.getAllArtworks();
            DefaultListModel<String> model = new DefaultListModel<>();

            String titleFilter = viewModel.getTitle().trim().toLowerCase();

            List<ArtWork> filteredArtworks = allArtworks.stream()
                    .filter(artwork -> artwork.getTitle().toLowerCase().contains(titleFilter) || titleFilter.isEmpty())
                    .collect(Collectors.toList());

            for (ArtWork artwork : filteredArtworks) {
                model.addElement(artwork.toString());
                System.out.println(model);
            }

            viewModel.setListModelArts(model);
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la filtrarea operelor de artă: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
