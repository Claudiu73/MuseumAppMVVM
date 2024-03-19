package Auth;

import Repo.DAOException;
import Repo.UserRepository;
import Model.User;

public class UserAuth {
    public static boolean authenticate(String username, String password, String userType) {
        UserRepository userRepository = new UserRepository();

        try {
            User user = userRepository.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password) && user.getUsername().equals(username) && user.getUserType().equalsIgnoreCase(userType)) {
                // Utilizatorul există, parola este corectă și tipurile de utilizator se potrivesc

                return true;
            }
        } catch (DAOException e) {
            e.printStackTrace(); // În practică, ar trebui să logăm această excepție corespunzător
        }

        // Dacă utilizatorul nu există, sau parola/ tipul de utilizator nu se potrivesc
        return false;
    }
}
