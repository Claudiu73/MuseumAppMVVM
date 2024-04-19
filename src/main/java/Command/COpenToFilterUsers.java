package Command;

import View.UsersFilterUI;
import ViewModel.AdminViewModel;

public class COpenToFilterUsers  implements ICommand{
    private AdminViewModel viewModel;
    private UsersFilterUI usersFilterUI;
    public COpenToFilterUsers(AdminViewModel viewModel)
    {
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        usersFilterUI = new UsersFilterUI();
        usersFilterUI.setVisible(true);
    }
}
