package utilities;

import java.util.HashMap;
import java.util.Map;

import models.ConnectionCreator;
import models.Group;
import models.User;
import models.WebPage;
import models.enums.ConnectionsEnum;
import models.enums.UserConnection;
import models.enums.WebPageConnection;
import models.interfaces.Connectable;

public class SocialUtil {
    public static Map<Connectable, ConnectionsEnum> hardcodedData() {
        Map<Connectable, ConnectionsEnum> baseData = new HashMap<>();

        // 2. Instanciamos los nodos usando tu Factory (ConnectionCreator)
        Connectable nodoUser1 = ConnectionCreator.createConnection(ConnectionsEnum.USER,
                Map.of("id", "1", "name", "Ana", "last_name", "Gomez", "age", "25"));

        Connectable nodoUser2 = ConnectionCreator.createConnection(ConnectionsEnum.USER,
                Map.of("id", "2", "name", "Carlos", "last_name", "Lopez", "age", "32"));

        Connectable nodoUser3 = ConnectionCreator.createConnection(ConnectionsEnum.USER,
                Map.of("id", "3", "name", "Maria", "last_name", "Silva", "age", "28"));

        Connectable nodoGroup = ConnectionCreator.createConnection(ConnectionsEnum.GROUP,
                Map.of("id", "4", "description", "Club de Programadores Java"));

        Connectable nodoWeb = ConnectionCreator.createConnection(ConnectionsEnum.WEB_PAGE,
                Map.of("id", "5", "url", "www.codigo-limpio.com"));

        // 3. Cargamos los nodos en el mapa con su respectivo tipo
        baseData.put(nodoUser1, ConnectionsEnum.USER);
        baseData.put(nodoUser2, ConnectionsEnum.USER);
        baseData.put(nodoUser3, ConnectionsEnum.USER);
        baseData.put(nodoGroup, ConnectionsEnum.GROUP);
        baseData.put(nodoWeb, ConnectionsEnum.WEB_PAGE);

        // 5. Casteamos a los tipos específicos para armar las relaciones internas
        User ana = (User) nodoUser1;
        User carlos = (User) nodoUser2;
        User maria = (User) nodoUser3;
        Group club = (Group) nodoGroup;
        WebPage web = (WebPage) nodoWeb;

        // --- HARDCODEO DE RELACIONES ---

        // Seteamos a Ana como la usuaria registrada en el sistema
        // repositorio.setRegisteredUser(ana);

        // Conexiones de Ana (User)
        ana.addConnection(carlos, UserConnection.AMIGO);
        ana.addConnection(maria, UserConnection.CONOCIDO);
        ana.addConnection(web, WebPageConnection.SEGUIDOR);

        // Conexiones de Carlos (User)
        carlos.addConnection(ana, UserConnection.AMIGO);

        // Conexiones del Grupo
        club.addUser(ana);
        club.addUser(carlos);

        // Conexiones de la WebPage (Como no hay método addUser, usamos el Getter de la
        // colección)
        web.getConnectionsList().add(ana);
        web.getConnectionsList().add(maria);

        // 6. Devolvemos el repositorio listo para usar
        return baseData;
    }
}
