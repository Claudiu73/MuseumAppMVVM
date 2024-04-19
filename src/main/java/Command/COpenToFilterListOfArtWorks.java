package Command;

import Repo.UserRepository;
import View.ArtWorkListView;
import ViewModel.AdminViewModel;
import ViewModel.ArtWorkListViewModel;

public class COpenToFilterListOfArtWorks implements ICommand{

    private UserRepository userRepository;
    private AdminViewModel viewModel;
    public COpenToFilterListOfArtWorks(UserRepository userRepository, AdminViewModel viewModel)
    {
        this.userRepository = userRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        ArtWorkListView artWorkListView = new ArtWorkListView();
    }
}
