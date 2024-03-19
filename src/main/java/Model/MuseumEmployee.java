package Model;

public class MuseumEmployee extends User {
    // Atribute specifice pentru un angajat, de exemplu:
    private String department;

    // Constructor
    public MuseumEmployee(String username, String password, String department) {
        super(username, password, "employee"); // "employee" este tipul de utilizator
        this.department = department;
    }

    // Getters and Setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Alte metode specifice angajaților
}

