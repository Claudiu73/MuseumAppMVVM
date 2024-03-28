package Presenter;

import Repo.ArtWorkRepository;
import View.ArtWorkListView;
import View.LogIn;


public class MainScreenPresenter {
    private IMainScreenUI view;
    private IArtWorkUI view1;
    public MainScreenPresenter(IMainScreenUI view) {
        this.view = view;
    }

    public void openArtWorkListView() {
        ArtWorkRepository repository = new ArtWorkRepository();
        ArtWorkListPresenter artWorkListPresenter = new ArtWorkListPresenter(view1);
        ArtWorkListView artWorkListView = new ArtWorkListView();

        artWorkListPresenter.setView(artWorkListView);
        artWorkListView.setVisible(true);
    }

    public void openLogInView(String userType) {
        LogIn logIn = new LogIn(userType);
        logIn.setVisible(true);
    }
}
