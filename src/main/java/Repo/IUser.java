package Repo;

import Model.User;
import java.util.List;

public interface IUser {
    void addUser(User user) throws DAOException;
    void deleteUser(int userId) throws DAOException;
    void updateUser(User user) throws DAOException;
    User getUserByUsername(String username) throws DAOException;
    List<User> getAllUsers() throws DAOException;
}
