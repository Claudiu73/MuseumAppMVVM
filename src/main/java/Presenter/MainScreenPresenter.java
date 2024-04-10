package Presenter;

import Repo.ArtWorkRepository;
import View.ArtWorkListView;
import View.LogIn;

public class MainScreenPresenter {
    private IMainScreenUI view;

    public MainScreenPresenter(IMainScreenUI view) {
        this.view = view;
    }

    public void openArtWorkListView() {
        ArtWorkListView artWorkListView = new ArtWorkListView();
        ArtWorkListPresenter artWorkListPresenter = new ArtWorkListPresenter(artWorkListView);

        artWorkListPresenter.setView(artWorkListView);
        artWorkListView.setPresenter(artWorkListPresenter);

        artWorkListView.showScreen();
    }

    public void openLogInView(String userType) {
        LogIn logIn = new LogIn(userType);
        logIn.showScreen();
    }
}
