package com.example.demo.services;

import com.example.demo.custom.CustomException;
import com.example.demo.database.Database;
import com.example.demo.models.Friendship;
import com.example.demo.models.Person;
import lombok.var;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 * an helpful class for sql methods
 */
public class FriendshipService {
    private static final Logger logger = Logger.getLogger(String.valueOf(FriendshipService.class));
    public static List<Friendship> friendshipList = new ArrayList<>();


    /**
     * add friendship in friendships table
     */
    public static void addFriendship(String key, String id, String anotherId) {
        friendshipList.add(new Friendship(key, id, anotherId));
        Connection con = Database.getConnection();
        try {
            var statement = con.prepareStatement("INSERT INTO FRIENDSHIPS VALUES (?,?,?)");
            statement.setString(1, key);
            statement.setString(2, id);
            statement.setString(3, anotherId);
            statement.executeQuery();
        } catch (SQLException exception) {
            logger.info("Exception in addPerson method.");
        }
    }

    /**
     * add friendship in friendships table
     */
    public static void addFriendship(Friendship friendship) {
        friendshipList.add(friendship);
        Connection con = Database.getConnection();
        try {
            var statement = con.prepareStatement("INSERT INTO FRIENDSHIPS VALUES (?,?,?)");
            statement.setString(1, friendship.getId());
            statement.setString(2, friendship.getPersonId());
            statement.setString(3, friendship.getAnotherPersonId());
            statement.executeQuery();
        } catch (SQLException exception) {
            logger.info("Exception in addPerson method.");
        }
    }

    /**
     * update friendship in friendships table
     */
    public static void updateFriendship(String id, String anotherId) {
        var con = Database.getConnection();
        for (Friendship friendship : friendshipList) {
            if (friendship.getId().equals(id)) {
                friendship.setAnotherPersonId(anotherId);
                break;
            }
        }
        try {
            var statement = con.prepareStatement("UPDATE FRIENDSHIPS SET ANOTHERPERSONID=? WHERE ID=?");
            statement.setString(1, anotherId);
            statement.setString(2, id);
            statement.executeQuery();
        } catch (SQLException exception) {
            logger.info("Exception in updateFriendship method.");
        }
    }

    /**
     * delete friendship with the given id in friendships table
     */
    public static void deleteFriendship(String id) {
        var con = Database.getConnection();
        try {
            var statement = con.prepareStatement("DELETE FROM FRIENDSHIPS WHERE ID=?");
            statement.setString(1, id);
            statement.executeQuery();
            friendshipList.removeIf(friendship -> friendship.getId().equals(id));
        } catch (SQLException exception) {
            logger.info("Exception in deleteFriendship method.");
        }
    }

    /**
     * get the friendships table
     */
    public static List<Friendship> showList() {
        if (friendshipList.size() > 0) {
            return friendshipList;
        } else {
            throw new CustomException("Empty table");
        }
    }

    /**
     * determining the first k most connected (popular) persons in the network
     */
    public static String getKMostConnected(int k) {
        List<Person> list = new ArrayList<>();
        for (Map.Entry<Person, Integer> elem : getFrequencyList().entrySet()) {
            if (elem.getValue() <= k) {
                list.add(elem.getKey());
            }
        }
        if (list.size() > 0) {
            return "List: " + list;
        } else {
            throw new CustomException("Empty list.");
        }
    }

    /**
     * determining the first k least connected (popular) persons in the network
     */
    public static String getKLeastConnected(int k) {
        List<Person> list = new ArrayList<>();
        for (Map.Entry<Person, Integer> elem : getFrequencyList().entrySet()) {
            if (elem.getValue() >= k) {
                list.add(elem.getKey());
            }
        }
        if (list.size() > 0) {
            return "List: " + list;
        } else {
            throw new CustomException("Empty list.");
        }
    }

    /**
     * helpful method to determining the first k least/most connected (popular) persons in the network
     * returns a map by form (key: person, value: the number of friends)
     */
    private static Map<Person, Integer> getFrequencyList() {
        Map<Person, Integer> frequencyList = new HashMap<>();
        for (int i = 0; i < PersonService.personList.size(); i++) {
            int frequency = 0;
            for (Friendship friendship : friendshipList) {
                if (PersonService.personList.get(i).getId().equals(friendship.getPersonId()) ||
                        PersonService.personList.get(i).getId().equals(friendship.getAnotherPersonId())) {
                    frequency++;
                }
            }
            frequencyList.put(PersonService.personList.get(i), frequency);
        }
        return frequencyList;
    }
}
