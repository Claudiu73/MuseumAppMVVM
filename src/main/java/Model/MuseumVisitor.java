package Model;

public class MuseumVisitor extends User {
    private String favoriteArtStyle;

    public MuseumVisitor(String username, String password, String favoriteArtStyle) {
        super(username, password, "visitor");
        this.favoriteArtStyle = favoriteArtStyle;
    }

    public String getFavoriteArtStyle() {
        return favoriteArtStyle;
    }

    public void setFavoriteArtStyle(String favoriteArtStyle) {
        this.favoriteArtStyle = favoriteArtStyle;
    }

}

