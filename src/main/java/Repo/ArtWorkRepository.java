package Repo;

import Model.ArtWork;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtWorkRepository implements IArtWork{

    @Override
    public void addArtwork(ArtWork artwork) throws DAOException {
        String sql = "INSERT INTO artworks (title, artist, year, type) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, artwork.getTitle());
            statement.setString(2, artwork.getArtist());
            statement.setInt(3, artwork.getYear());
            statement.setString(4, artwork.getType());
            statement.executeUpdate();
            System.out.println("Opera de arta adaugata cu succes.");        }
        catch (SQLException e) {
            throw new DAOException("Error adding artwork", e);
        }

    }

    @Override
    public ArtWork getArtworkByName(String name) throws DAOException {
        String sql = "SELECT * FROM artworks WHERE title = ?";
        ArtWork artwork = null;

        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = ((ResultSet) resultSet).getInt("id");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                int year = resultSet.getInt("year");
                String type = resultSet.getString("type");
                artwork = new ArtWork(title, artist, year, type);
                artwork.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Error fetching artwork by name", e);
        }
        return artwork;
    }


    @Override
    public List<ArtWork> getAllArtworks() throws DAOException {
        List<ArtWork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM artworks";

        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                int year = resultSet.getInt("year");
                String type = resultSet.getString("type");
                ArtWork artwork = new ArtWork(title, artist, year, type);
                artwork.setId(id);
                artworks.add(artwork);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving all artworks", e);
        }
        return artworks;
    }


    @Override
    public void updateArtwork(ArtWork artwork) throws DAOException {

        String sql = "UPDATE artworks SET title = ?, artist = ?, year = ?, type = ? WHERE id = ?";

        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, artwork.getTitle());
            statement.setString(2, artwork.getArtist());
            statement.setInt(3, artwork.getYear());
            statement.setString(4, artwork.getType());
            statement.setInt(5, artwork.getId());


            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Opera de artă a fost actualizată cu succes.");
            } else {
                System.out.println("Nu s-a găsit opera de artă cu ID-ul specificat pentru actualizare.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating artwork", e);
        }
    }


    @Override
    public void deleteArtwork(String title) throws DAOException {
        String sql = "DELETE FROM artworks WHERE title=?";

        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, title);

            int affectedRows = statement.executeUpdate();
            System.out.println("Stergerea a reusit.");
            if (affectedRows == 0) {
                throw new DAOException("Ștergerea operelor de artă a eșuat; niciun rând afectat.");
            }
        } catch (SQLException e) {
            throw new DAOException("Eroare la ștergerea operelor de artă", e);
        }
    }

}
