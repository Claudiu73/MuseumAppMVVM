package Command;

import Repo.ArtWorkRepository;
import View.ArtWorkListView;
import ViewModel.ArtWorkListViewModel;
import ViewModel.MainScreenViewModel;

public class COpenVisitor implements ICommand{

    private MainScreenViewModel viewModel;

    public COpenVisitor( MainScreenViewModel viewModel)
    {

        this.viewModel = viewModel;
    }
    @Override
        public void execute() {
        ArtWorkListView artWorkListView = new ArtWorkListView();
        ArtWorkListViewModel artWorkListViewModel = new ArtWorkListViewModel();
    }
}
