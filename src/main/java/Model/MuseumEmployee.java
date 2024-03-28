package Model;

public class MuseumEmployee extends User {
    private String department;

    public MuseumEmployee(String username, String password, String department) {
        super(username, password, "employee");
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}

