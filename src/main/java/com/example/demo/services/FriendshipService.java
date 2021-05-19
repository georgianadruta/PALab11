package com.example.demo.services;

import com.example.demo.database.Database;
import com.example.demo.models.Friendship;

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


    public static String addFriendship(String key, String id, String anotherId) {
        String query = "INSERT INTO friendships(id, personId, anotherPersonId) VALUES('" + key + "','" + id + "','" + anotherId + "')";
        db.doUpdate(query);
        friendshipList.add(new Friendship(key, id, anotherId));
        return anotherId + " has been added to the list.";
    }


    public static String addFriendship(Friendship friendship) {
        String query = "INSERT INTO friendships(id, personId, anotherPersonId) values('" + friendship.getId() + "','" + friendship.getPersonId() + "','" + friendship.getAnotherPersonId() + "')";
        db.doUpdate(query);
        friendshipList.add(new Friendship(friendship.getId(), friendship.getPersonId(), friendship.getAnotherPersonId()));
        return friendship.getAnotherPersonId() + " has been added to the database.";
    }


    public static String updateFriendship(String id, String anotherId) {
        String query = "UPDATE friendships SET anotherPersonId= '" + anotherId + "' where id='" + id + "'";
        db.doUpdate(query);
        for (Friendship friendship : friendshipList) {
            if (friendship.getId().equals(id)) {
                friendship.setAnotherPersonId(anotherId);
                break;
            }
        }
        return "The person's friend with id=" + id + " got updated to " + anotherId;
    }


    public static String deleteFriendship(String id) {
        String query = "DELETE FROM friendships WHERE id='" + id + "'";
        db.doUpdate(query);
        for (int i = 0; i < friendshipList.size(); i++) {
            if (friendshipList.get(i).getId().equals(id)) {
                friendshipList.remove(i);
                return "The player with the id=" + id + " has been deleted successfully";
            }
        }
        return "The friendship with the id=" + id + " not found.";
    }

    public static List<Friendship> showList() {
        ResultSet rs = db.setResultSet("SELECT id, personId, anotherPersonId FROM friendships");
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
        ResultSet rs = db.setResultSet("SELECT id, personId, anotherPersonId FROM friendships WHERE id= '" + id + "'");
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
