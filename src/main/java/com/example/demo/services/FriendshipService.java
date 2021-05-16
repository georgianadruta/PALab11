package com.example.demo.services;

import com.example.demo.Database;
import com.example.demo.model.Friendship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * an helpful class for sql methods
 */
public class FriendshipService {
    private static final Logger logger = Logger.getLogger(String.valueOf(FriendshipService.class));
    private static final Database db = Database.getInstance();
    public static List<Friendship> friendshipList = new ArrayList<>();


    public static void addFriendship(String key, String id, String name) {
        String query = "INSERT INTO friendships(primaryKey, strangeKey, name) VALUES('" + key + "','" + id + "','" + name + "')";
        db.doUpdate(query);
        friendshipList.add(new Friendship(key, id, name));
        logger.info(name + " has been added to the list.");
    }


    public static void addFriendship(Friendship friendship) {
        String query = "INSERT INTO friendships(primaryKey, strangeKey, name) values('" + friendship.getPrimaryKey() + "','" + friendship.getStrangeKey() + "','" + friendship.getName() + "')";
        db.doUpdate(query);
        friendshipList.add(new Friendship(friendship.getPrimaryKey(), friendship.getStrangeKey(), friendship.getName()));
        logger.info(friendship.getName() + " has been added to the database.");
    }


    public static void updateFriendship(String id, String newName) {
        String query = "UPDATE friendships SET name= '" + newName + "' where primaryKey='" + id + "'";
        db.doUpdate(query);
        logger.info("The person's friend with id=" + id + " got updated to " + newName);
    }


    public static void deletePlayer(String id) {
        String query = "DELETE FROM friendships WHERE primaryKey='" + id + "'";
        db.doUpdate(query);
        logger.info("The player with the id " + id + " has been deleted successfully");
    }

    public static List<Friendship> showList() {
        ResultSet rs = db.setResultSet("SELECT id, name from player");
        try {
            while (rs.next()) {
                Friendship friendship = new Friendship(rs.getString(1), rs.getString(2), rs.getString(3));
                friendshipList.add(friendship);
            }
        } catch (Exception exception) {
            logger.info("Empty table");
        }
        return friendshipList;
    }

    public static List<Friendship> showFriendship(String id) {
        List<Friendship> friendList = new ArrayList<>();
        ResultSet rs = db.setResultSet("SELECT primaryKey, strangeKey, name FROM friendships WHERE primaryKey= '" + id + "'");
        try {
            while (rs.next()) {
                if (rs.getString(1).equals(id)) {
                    friendList.add(new Friendship(rs.getString(1), rs.getString(2), rs.getString(3)));
                }
            }
        } catch (SQLException e) {
            logger.info("Friendships not found for person with id=" + id);
        }
        return friendList;
    }
}
