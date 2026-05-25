package models;

import java.util.HashMap;
import java.util.Map;

import models.interfaces.Connectable;
import models.interfaces.TypeConnection;

public class User implements Connectable {
    private int id;
    private String name;
    private String lastName;
    private int edad;
    private Map<Connectable, TypeConnection> connectionsList = new HashMap<>();

    public User(int id, String name, String lastName, int edad) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.edad = edad;
    }

    public boolean addConnection(Connectable connection, TypeConnection typeConnection) {
        if (!connectionsList.containsKey(connection)) {
            connectionsList.put(connection, typeConnection);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean searchById(int id) {
        return connectionsList.keySet().stream().anyMatch(value -> value.getConnectableId() == id);
    }

    @Override
    public boolean removeConnection(int id) {
        if (searchById(id)) {
            connectionsList.keySet().removeIf(value -> value.getConnectableId() == id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getConnectableId() {
        return this.id;
    }

    public Map<Connectable, TypeConnection> checkAllConnections() {
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
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

    // *Boilerplate */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEdad() {
        return edad;
    }

    public Map<Connectable, TypeConnection> getConnectionsList() {
        return connectionsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setConnectionsList(Map<Connectable, TypeConnection> connectionsList) {
        this.connectionsList = connectionsList;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", edad=" + edad + ", connectionsList="
                + connectionsList + "]";
    }

}