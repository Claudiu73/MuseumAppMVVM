package Model;

public class MuseumAdmin extends User {
    // Atribute specifice pentru un administrator, de exemplu:
    private boolean superUser;

    // Constructor
    public MuseumAdmin(String username, String password, boolean superUser) {
        super(username, password, "admin"); // "admin" este tipul de utilizator
        this.superUser = superUser;
    }

    // Getters and Setters
    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    // Alte metode specifice administratorilor
}
