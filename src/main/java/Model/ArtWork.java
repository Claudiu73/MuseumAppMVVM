package Model;

public class ArtWork {

    private int id;
    private String title;
    private String artist;
    private int year;
    private String type; // Ex: painting, sculpture

    // Constructor without description
    public ArtWork(String title, String artist, int year, String type) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdToString() {
        return String.valueOf(id);
    }

    public int getId() {
        return id;
    }
}

