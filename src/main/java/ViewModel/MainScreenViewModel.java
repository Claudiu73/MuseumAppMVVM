package ViewModel;

import Command.COpenVisitor;
import Repo.UserRepository;

public class MainScreenViewModel {

    private LogInViewModel logInViewModel;
    private UserRepository userRepository;

    public COpenVisitor cOpenVisitor;

    public MainScreenViewModel(LogInViewModel logInViewModel, UserRepository userRepository) {

        this.logInViewModel = logInViewModel;
        this.userRepository = userRepository;
        this.cOpenVisitor = new COpenVisitor( this);
    }

    public void OpenVisitor()
    {
        cOpenVisitor.execute();
    }

}
