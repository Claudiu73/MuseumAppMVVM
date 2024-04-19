package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.ArtWorkListViewModel;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CToFilterYearForVisitor implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private ArtWorkListViewModel viewModel;

    public CToFilterYearForVisitor(ArtWorkRepository artWorkRepository, ArtWorkListViewModel viewModel)
    {
        this.artWorkRepository =artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        try {
            List<ArtWork> allArtworks = artWorkRepository.getAllArtworks();
            DefaultListModel<String> model = new DefaultListModel<>();

            String yearFilter = String.valueOf(viewModel.getYear()).trim();

            List<ArtWork> filteredArtworks = allArtworks.stream()
                    .filter(artwork -> String.valueOf(artwork.getYear()).equals(yearFilter) || yearFilter.isEmpty())
                    .collect(Collectors.toList());

            for (ArtWork artwork : filteredArtworks) {
                model.addElement(artwork.toString());
            }

            viewModel.setListModelArts(model);
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la filtrarea operelor de artă după an: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
