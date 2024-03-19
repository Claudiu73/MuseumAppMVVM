package Repo;
import Model.ArtWork;

import java.util.List;

public interface IArtWork {
    void addArtwork(ArtWork artwork) throws DAOException;
    ArtWork getArtworkByName(String name) throws DAOException;
    List<ArtWork> getAllArtworks() throws DAOException;
    void updateArtwork(ArtWork artwork) throws DAOException;
    void deleteArtwork(String artist) throws DAOException;
}
