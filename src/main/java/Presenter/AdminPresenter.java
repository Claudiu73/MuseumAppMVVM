package Presenter;

import Model.ArtWork;
import Model.User;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import Repo.UserRepository;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class AdminPresenter {
    private IAdminUI view;
    private ArtWorkRepository artWorkRepository;
    private UserRepository userRepository;

    public AdminPresenter(IAdminUI view) {
        this.view = view;
        this.userRepository = new UserRepository();
        this.artWorkRepository = new ArtWorkRepository();
    }

    public void onAddUserClicked() {
        String username = view.getUsernameField().trim();
        String password = view.getPasswordField().trim();
        String usertype = view.getUserTypeField().trim();

        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty()) {
            view.setUsernameField("Trebuie completat username-ul!");
            view.setPasswordField("Trebuie completata parola!");
            view.setUserTypeField("Trebuie completat tipul de utilizator!");
            showMessageError("Toate câmpurile trebuie completate!");
            return;
        }

        User newUser = new User(username, password, usertype);

        try {
            userRepository.addUser(newUser);
            showMessageSucces("Utilizator adăugat cu succes.");
        } catch (DAOException ex) {
            System.out.println("Eroare.");
            showMessageError("Eroare la adăugarea utilizatorului");
        }
    }

    public void fetchAndDisplayUsers() {
        UserRepository userRepository = new UserRepository();
        try {
            List<User> users = userRepository.getAllUsers();
            DefaultListModel<String> userModel = new DefaultListModel<>();

            for (User user : users) {
                userModel.addElement(user.toString());
            }

            view.getUserList().setModel(userModel);
        } catch (Exception e) {
            showMessageError("Eroare la actualizarea listei de utilizatori.");
            e.printStackTrace();
        }
    }

    public void fetchAndDisplayArtworks()
    {
        ArtWorkRepository artWorkRepository = new ArtWorkRepository();
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            DefaultListModel<String> artworkModel = new DefaultListModel<>();

            for(ArtWork artWork : artworks)
            {
                artworkModel.addElement(artWork.toString());
            }

            view.getArtWorkList().setModel(artworkModel);
        } catch (Exception e) {
            e.printStackTrace();
            showMessageError("Eroare la afisarea operelor de arta din muzeu.");
        }
    }

    public void addDeleteUserListener() {
        String username = view.getUsernameField().trim();

        if(username.isEmpty()) {
            showMessageError("Vă rugăm introduceți username-ul pentru ștergere.");
            return;
        }

        UserRepository userRepository = new UserRepository();
        try {
            User user = userRepository.getUserByUsername(username);
            if (user == null) {
                showMessageError("Utilizatorul nu există.");
                return;
            }

            int response = showMessageYesOrNo("Sunteți sigur că doriți să ștergeți utilizatorul " + view.getUsernameField() + "?");
            if (response == JOptionPane.YES_OPTION) {
                userRepository.deleteUser(username);
                showMessageSucces("Utilizatorul a fost șters cu succes.");
            }
        }
        catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSearchUserListener() {

        String username = view.getUsernameField().trim();
        if (username.isEmpty()) {
            showMessageError("Vă rugăm introduceți un username pentru căutare.");
            return;
        }

        try {
            User user = userRepository.getUserByUsername(username);

            if (user != null) {
                showMessageSucces("Utilizator găsit: \nUsername: " + user.getUsername() + "\nParolă: " + user.getPassword() + "\nTip: " + user.getUserType() + "\nRezultat Căutare.");
            } else {

                showMessageError("Nu a fost găsit niciun utilizator cu username-ul " + username + ".\n" + "\nUtilizator inexistent");
            }

        } catch (DAOException ex) {
            showMessageError("Eroare la căutarea utilizatorului.");
        }
    }

    public void filterArtworks() {
        String searchText = view.getArtWorkFilter().trim();

        try {
            ArtWorkRepository artWorkRepository = new ArtWorkRepository();
            List<ArtWork> allArtworks = artWorkRepository.getAllArtworks();

            List<ArtWork> filteredArtworks = allArtworks.stream()
                    .filter(artwork -> artwork.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                            artwork.getArtist().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
            updateList(filteredArtworks);
        } catch (Exception e) {
            e.printStackTrace();
            showMessageError("Eroare la filtrarea operelor de artă");
        }
    }

    private void updateList(List<ArtWork> artworks) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (ArtWork artwork : artworks) {
            listModel.addElement(artwork.toString());
        }
        view.setArtWorksList(listModel);
    }

    public int showMessageYesOrNo(String message) {
        return JOptionPane.showConfirmDialog((Component) view, message, "Confirmare ștergere", JOptionPane.YES_NO_OPTION);
    }

    public void showMessageError(String message) {
        JOptionPane.showMessageDialog((Component)view, message, "Eroare!", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessageSucces(String message) {
        JOptionPane.showMessageDialog((Component) view, message, "Succes!", JOptionPane.INFORMATION_MESSAGE);
    }

}
