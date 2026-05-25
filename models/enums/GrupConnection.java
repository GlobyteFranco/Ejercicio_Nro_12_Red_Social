package models.enums;

import java.util.Optional;

import models.interfaces.TypeConnection;

public enum GrupConnection implements TypeConnection {
    MIEMBRO, ADMINISTRADOR, CREADOR;

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
