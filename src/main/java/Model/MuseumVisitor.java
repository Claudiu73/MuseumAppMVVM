package Model;

public class MuseumVisitor extends User {
    // Atribute specifice pentru un vizitator, de exemplu:
    private String favoriteArtStyle;

    // Constructor
    public MuseumVisitor(String username, String password, String favoriteArtStyle) {
        super(username, password, "visitor"); // "visitor" este tipul de utilizator
        this.favoriteArtStyle = favoriteArtStyle;
    }

    // Getters and Setters
    public String getFavoriteArtStyle() {
        return favoriteArtStyle;
    }

    public void setFavoriteArtStyle(String favoriteArtStyle) {
        this.favoriteArtStyle = favoriteArtStyle;
    }

    // Aici pot fi adăugate alte metode specifice vizitatorilor
}

