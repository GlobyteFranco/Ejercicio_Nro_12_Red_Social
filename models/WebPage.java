package models;

import java.util.HashSet;
import java.util.Set;

import models.interfaces.Connectable;

public class WebPage implements Connectable {
    private int id;
    private String url;
    private Set<User> connectionsList = new HashSet<>();

    public WebPage(int id, String url) {
        this.id = id;
        this.url = url;
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

    // *Boilerplate */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WebPage other = (WebPage) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<User> getConnectionsList() {
        return connectionsList;
    }

    public void setConnectionsList(Set<User> connectionsList) {
        this.connectionsList = connectionsList;
    }

    @Override
    public String toString() {
        return "WebPage [id=" + id + ", url=" + url + ", connectionsList=" + connectionsList + "]";
    }

}
