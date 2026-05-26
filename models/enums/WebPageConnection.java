package models.enums;

import java.util.Optional;

import models.interfaces.TypeConnection;

public enum WebPageConnection implements TypeConnection {
    SEGUIDOR, MODERADOR;

    public static Optional<ConnectionsEnum> fromText(String textParam) {
        if (textParam == null || textParam.isBlank()) {
            return Optional.empty();
        }

        String cleanText = textParam.trim();

        for (ConnectionsEnum connection : ConnectionsEnum.values()) {
            if (connection.name().equalsIgnoreCase(cleanText)) {
                return Optional.of(connection);
            }
        }

        return Optional.empty();
    }
}
