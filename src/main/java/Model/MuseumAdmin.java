package Model;

public class MuseumAdmin extends User {
    private boolean superUser;

    // Constructor
    public MuseumAdmin(String username, String password, boolean superUser) {
        super(username, password, "admin");
        this.superUser = superUser;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

}
