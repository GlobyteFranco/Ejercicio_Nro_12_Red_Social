package repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import models.User;
import models.enums.ConnectionsEnum;
import models.interfaces.Connectable;

public class ConnectablesRepositorie {
    private User registeredUser;
    private Map<Connectable, ConnectionsEnum> mapRepositorie = new HashMap<>();
    private int id = 0;

    public ConnectablesRepositorie() {

    }

    public ConnectablesRepositorie(Map<Connectable, ConnectionsEnum> mapEntry) {
        this.mapRepositorie = mapEntry;
    }

    public boolean addConnectableToMap(Connectable connectable, ConnectionsEnum connectionsEnum) {
        if (mapRepositorie.containsKey(connectable)) {
            mapRepositorie.put(connectable, connectionsEnum);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkExistance(int id) {
        return mapRepositorie.keySet().stream()
                .anyMatch(nodo -> nodo.getConnectableId() == id);
    }

    public boolean isUserRegistered() {
        if (registeredUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<Connectable> obtainSelectedUser(int id) {
        return mapRepositorie.keySet().stream().filter(conectable -> conectable.getConnectableId() == id).findFirst();

    }

    public Optional<ConnectionsEnum> obtainSelectedUserType(int id) {
        return mapRepositorie.entrySet().stream()
                .filter(entry -> entry.getKey().getConnectableId() == id)
                .map(entry -> entry.getValue())
                .findFirst();

    }

    // *Boilerplate */

    public User getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
        id += 1;
    }

    public Map<Connectable, ConnectionsEnum> getMapRepositorie() {
        return mapRepositorie;
    }

    public void setMapRepositorie(Map<Connectable, ConnectionsEnum> mapRepositorie) {
        this.mapRepositorie = mapRepositorie;
    }

    public int getId() {
        return id;
    }

    // ! Deprecated Code:

    // ! DEPRECATED --> Responsability passed to User class

    public boolean checkSelfExistance(int id) {
        return registeredUser.checkAllConnections().keySet().stream()
                .anyMatch(nodo -> nodo.getConnectableId() == id);
    }

}
