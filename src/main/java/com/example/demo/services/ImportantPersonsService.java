package com.example.demo.services;

import com.example.demo.models.Friendship;
import com.example.demo.models.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportantPersonsService {
    private final int[][] adjacencyMatrix;
    private final Map<Person, Integer> frequencyList;


    public ImportantPersonsService() {
        List<Person> personList = PersonService.personList;
        List<Friendship> friendshipList = FriendshipService.friendshipList;
        this.adjacencyMatrix = new int[personList.size()][personList.size()];
        this.frequencyList = new HashMap<>(personList.size());
        for (Friendship friendship : friendshipList) {
            String m = friendship.getPersonId();
            String n = friendship.getAnotherPersonId();
            adjacencyMatrix[Integer.parseInt(m)][Integer.parseInt(n)] = adjacencyMatrix[Integer.parseInt(n)][Integer.parseInt(m)] = 1;
            if (frequencyList.get(getPersonWithId(m)) == null) {
                frequencyList.put(getPersonWithId(m), 1);
            } else {
                frequencyList.put(getPersonWithId(m), frequencyList.get(getPersonWithId(m)) + 1);
            }
            if (frequencyList.get(getPersonWithId(n)) == null) {
                frequencyList.put(getPersonWithId(n), 1);
            } else {
                frequencyList.put(getPersonWithId(n), frequencyList.get(getPersonWithId(n)) + 1);
            }
        }
    }

    private Person getPersonWithId(String id) {
        for (Person person : PersonService.personList) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    private Person getTheMostImportantPerson() {
        Person importantPerson = null;
        int max = 0;
        for (Map.Entry<Person, Integer> person : frequencyList.entrySet()) {
            if (person.getValue() > max) {
                max = person.getValue();
                importantPerson = person.getKey();
            }
        }
        frequencyList.remove(importantPerson);
        return importantPerson;
    }

    public List<Person> getImportantPersonList() {
        List<Person> importantPersonList = new ArrayList<>();
        while (isConnected()) {
            Person person = getTheMostImportantPerson();
            importantPersonList.add(person);
            frequencyList.remove(person);
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                adjacencyMatrix[Integer.parseInt(person.getId())][j] = 0;
            }
        }
        return importantPersonList;
    }

    /**
     * Mark the current node as visited and print the node.
     * Traverse all the adjacent and unmarked nodes and call the recursive function with index of adjacent node and a visited array..
     *
     * @param vertex  the start vertex
     * @param visited initialized all the vertices to the flag not visited(in main)
     */
    public void dfs(int vertex, boolean[] visited) {
        if (!visited[vertex]) {
            visited[vertex] = true;
            for (int neighbour = 0; neighbour < adjacencyMatrix[vertex].length; neighbour++) {
                if (adjacencyMatrix[vertex][neighbour] == 1 && !visited[neighbour]) {
                    dfs(neighbour, visited);
                }
            }
        }
    }

    /**
     * Check if the graph is connected using the visited array from the dfs function.
     * The graph is connected if we’ve visited all the vertex, visited[i]=true for any vertex i.
     */
    public boolean isConnected() {
        boolean[] visited = new boolean[PersonService.personList.size()];
        dfs(0, visited);
        for (boolean b : visited)
            if (!b) {
                return false;
            }
        return true;
    }

}
