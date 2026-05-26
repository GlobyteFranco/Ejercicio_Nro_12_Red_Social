package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import models.User;
import models.enums.ConnectionsEnum;
import models.enums.GrupConnection;
import models.enums.UserConnection;
import models.enums.WebPageConnection;
import models.interfaces.TypeConnection;
import repositories.ConnectablesRepositorie;

// if(!checkRegister())return"No hay usuario registrado";
public class SocialController {
    ConnectablesRepositorie repositorie;

    public SocialController(ConnectablesRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    public boolean checkRegister() {
        return repositorie.isUserRegistered();
    }

    public void signUpUser(String name, String lastName, int age) {
        repositorie.setRegisteredUser(new User(repositorie.getId(), name, lastName, age));
    }

    public boolean addConnection(int id, String stringedTypeConnection) {

        TypeConnection typeConnection = checkTypeConnectionType(stringedTypeConnection);// validar de que tipo de
                                                                                        // TypeConnectio y usar el
                                                                                        // respectivo metodo estatico
                                                                                        // //todo ACA HACER LA FABRICA
                                                                                        // DE TYPE CONNECTIONS ENUMS Y
                                                                                        // VER SI PUEDO IMPLEMENTARLA EN
                                                                                        // LA VIEW
        if (checkUserExistance(id)) {
            repositorie.obtainSelectedUser(id)
                    .ifPresent(conectable -> repositorie.getRegisteredUser().addConnection(conectable, typeConnection));
            return true;
        } else {
            return false;
        }

    }

    public TypeConnection checkTypeConnectionType(String param) {
        if (param == null)
            return WebPageConnection.MODERADOR;
        switch (param.toUpperCase()) {
            case "MIEMBRO":

                break;
            case "ADMINISTRADOR":
                return GrupConnection.ADMINISTRADOR;

            case "CREADOR":
                return GrupConnection.CREADOR;
            case "AMIGO":
                return UserConnection.AMIGO;

            case "FAMILIA":
                return UserConnection.FAMILIA;

            case "TRABAJO":

                return UserConnection.TRABAJO;
            case "CONOCIDO":
                return UserConnection.CONOCIDO;
            case "SEGUIDOR":
                return WebPageConnection.SEGUIDOR;
            case "MODERADOR":
                return WebPageConnection.MODERADOR;

            default:
                return WebPageConnection.MODERADOR;

        }
        return UserConnection.CONOCIDO;

    }

    public String showFamilyTypeEnum(int id) {// Encontramos y devolvemos el tipo de familia del usuario elegido para
                                              // que pueda elegir una de sus opciones
        if (checkUserExistance(id)) {
            ConnectionsEnum connectionEnum = repositorie.obtainSelectedUserType(id).get();
            return connectionEnum.toString();
        } else {
            return "NONE";
        }

    }

    public Map<String, String> obtainAllConnectables() {
        return repositorie.getMapRepositorie().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(), // Transforma la clave a String
                        entry -> entry.getValue().toString() // Transforma el valor a String
                ));
    }

    public boolean checkUserExistance(int id) {
        if (!checkRegister())
            return false;
        return repositorie.checkExistance(id);
    }

    public boolean checkUserSelfExistance(int id) {
        if (!checkRegister())
            return false;
        return repositorie.getRegisteredUser().searchById(id);
    }

    public Map<String, String> obtainSelfConnections() {
        if (!checkRegister())
            return new HashMap<>();
        return repositorie.getRegisteredUser().checkAllConnections().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.toString(),

                        entry -> entry.getValue().toString()));
    }

    public void removeSelfConnection(int id) {
        if (!checkRegister())
            return;
        repositorie.getRegisteredUser().removeConnection(id);
    }

    public String obtainAllUserInfo() {
        if (!checkRegister())
            return "No hay usuario registrado";
        return repositorie.getRegisteredUser().toString();
    }
}
