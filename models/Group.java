package models;

import java.util.HashSet;
import java.util.Set;

import models.interfaces.Connectable;

public class Group implements Connectable {
    private int id;
    private String description;
    private Set<User> connectionsList = new HashSet<>();

    public Group(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public boolean addUser(User user) {
        this.connectionsList.add(user);
        if (!searchById(user.getConnectableId())) {
            connectionsList.add(user);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean searchById(int id) {
        return connectionsList.stream().anyMatch(value -> value.getConnectableId() == id);

    }

    @Override
    public boolean removeConnection(int id) {
        if (searchById(id)) {
            connectionsList.removeIf(value -> value.getConnectableId() == id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getConnectableId() {
        return this.id;
    }

    public Set<User> checkAllConnections() {
        return this.connectionsList;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (id != other.id)
            return false;
        return true;
    }

    // *Boilerplate */
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getConnectionsList() {
        return connectionsList;
    }

    public void setConnectionsList(Set<User> connectionsList) {
        this.connectionsList = connectionsList;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", description=" + description + ", connectionsList=" + "]";
    }

}
