package models;

import java.util.Map;

import models.enums.ConnectionsEnum;
import models.interfaces.Connectable;

public class ConnectionCreator {
    public static Connectable createConnection(ConnectionsEnum connectionType, Map<String, String> dataMap) {
        int id;
        switch (connectionType) {
            case USER:
                id = Integer.parseInt(dataMap.get("id"));
                String name = dataMap.get("name");
                String lastName = dataMap.get("last_name");
                int age = Integer.parseInt(dataMap.get("age"));
                return new User(id, name, lastName, age);

            case GROUP:
                id = Integer.parseInt(dataMap.get("id"));
                String description = dataMap.get("description");
                return new Group(id, description);

            case WEB_PAGE:
                id = Integer.parseInt(dataMap.get("id"));
                String url = dataMap.get("url");
                return new WebPage(id, url);

            default:
                throw new IllegalArgumentException("Type connections not supported");
        }
    }
}
