package models.interfaces;

public interface Connectable {
    public boolean searchById(int id);

    public boolean removeConnection(int id);

    public int getConnectableId();
}
