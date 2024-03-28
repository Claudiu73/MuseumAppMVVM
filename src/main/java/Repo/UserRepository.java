package Repo;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUser{
    @Override
    public void addUser(User user) throws DAOException {
        String sql = "INSERT INTO users (username, password, usertype) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserType());
            statement.executeUpdate();
            System.out.println("User adaugat cu succes.");
        } catch (SQLException e) {
            throw new DAOException("Error adding user", e);
        }
    }

    @Override
    public void deleteUser(String username) throws DAOException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting user", e);
        }
    }

    @Override
    public void updateUser(User user) throws DAOException {
        String sql = "UPDATE users SET username = ?, password = ?, usertype = ? WHERE id = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserType());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating user", e);
        }
    }

    @Override
    public User getUserByUsername(String username) throws DAOException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("usertype"));
                }

            }
        } catch (SQLException e) {
            throw new DAOException("Error fetching user by id", e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("usertype")));
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving all users", e);
        }
        return users;
    }
}