package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.ArtWorkListViewModel;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CToFilterTypeForVisitor implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private ArtWorkListViewModel viewModel;
    public CToFilterTypeForVisitor(ArtWorkRepository artWorkRepository, ArtWorkListViewModel viewModel)
    {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        try {
            List<ArtWork> allArtworks = artWorkRepository.getAllArtworks();
            DefaultListModel<String> model = new DefaultListModel<>();

            String typeFilter = viewModel.getType().trim().toLowerCase();

            List<ArtWork> filteredArtworks = allArtworks.stream()
                    .filter(artwork -> artwork.getType().toLowerCase().contains(typeFilter) || typeFilter.isEmpty())
                    .collect(Collectors.toList());

            for (ArtWork artwork : filteredArtworks) {
                model.addElement(artwork.toString());
            }

            viewModel.setListModelArts(model);
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la filtrarea operelor de artă după tip: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
