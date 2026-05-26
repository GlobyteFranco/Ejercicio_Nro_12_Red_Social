package views;

import java.util.Map;
import java.util.Scanner;

import controllers.SocialController;

public class SocialView {
    SocialController controller;
    Scanner scanner;

    public SocialView(SocialController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void startProgram() {
        System.out.println("Bienvenido/a al programa!!");
        displayActionMenu();
    }

    public boolean checkRegister() {
        return controller.checkRegister();
    }

    // !Luego de registrarme me sigue presentando el menu de invitado
    public void displayActionMenu() {
        while (!controller.checkRegister()) {
            try {
                System.out.println(
                        "Action Menu \n\n  [1]--> Check available connections \n[2]-->Sign up\n  [0]--> End Program");
                System.out.println("Elija un valor...");
                int selectedOption = scanner.nextInt();
                while (!(selectedOption >= 0 && selectedOption <= 2)) {
                    System.out.println("Valor fuera de rango, por favor intentelo de nuevo");
                    selectedOption = scanner.nextInt();
                }
                chooseView(selectedOption);

            } catch (Exception e) {
                System.out.println("El valor elegido fue invalido, por favor elija uno correcto");
                scanner.nextLine();
            }
        }

        while (controller.checkRegister()) {
            try {
                System.out.println(
                        "Action Menu \n\n [1]--> Check available connections \n [3]--> Add Connection \n [4]--> Check Self Connections \n [5]--> Remove Connection \n [6]--> Check Users Info \n [0]--> End Program");
                System.out.println("Elija un valor...");
                int selectedOption = scanner.nextInt();
                while (!(selectedOption >= 0 && selectedOption <= 6)) {
                    System.out.println("Valor fuera de rango, por favor intentelo de nuevo");
                    selectedOption = scanner.nextInt();
                }
                chooseView(selectedOption);

            } catch (Exception e) {
                System.out.println("El valor elegido fue invalido, por favor elija uno correcto");
                scanner.nextLine();
            }
        }

    }

    public void chooseView(int value) {
        int selectedValue;
        switch (value) {

            case 1:// *Mostrar conexiones disponibles */
                showAvailableConnections(controller.obtainAllConnectables());
                break;
            case 2:// *Menu para iniciar sesion */
                showSingUpMenu();
                break;
            case 3:// *Sumar conexion */
                selectedValue = showAndSelectConnections();
                while (!controller.checkUserExistance(selectedValue)) {
                    System.out.println("The selected connection couldnt be found, please try again");
                    selectedValue = showAndSelectConnections();
                }
                String enumConnectionType = selectFromFamilyType(selectedValue);

                controller.addConnection(selectedValue, enumConnectionType);

                break;
            case 4:// *Obtener conexiones propias */
                showSelfConnections();
                break;
            case 5:// *Remover Conexion*/
                System.out.println("Enter the connection to remove");
                selectedValue = showAndSelectSelfConnections();
                while (!controller.checkUserSelfExistance(selectedValue)) {
                    System.out.println("The selected connection couldnt be found, please try again");
                    selectedValue = showAndSelectSelfConnections();
                }
                controller.removeSelfConnection(selectedValue);
                // llamar al metodo de controller para borrar el seguidor
                break;
            case 6:// *Obtener informacion propia */
                obtainAndShowRegisteredUserInfo();
                break;
            case 0:// *Terminar programa */
                byeBye();
                break;

            default:
                System.err.println("Dunno");
                break;
        }
    }

    public void showAvailableConnections(Map<String, String> mapData) {
        System.out.println("Showing available connections \n\n");
        mapData.forEach((key, value) -> System.out.println(key + "\n" + value));
    }

    public void showSingUpMenu() {
        System.out.println("Go on and enter your personal data");
        scanner.nextLine();
        while (true) {
            try {
                System.out.println("Name: ");
                String name = scanner.nextLine();
                System.out.println("Last Name: ");
                String lastName = scanner.nextLine();
                System.out.println("Age: ");
                int age = scanner.nextInt();
                while (age < 18 || age > 99) {
                    System.out.println("cmooonnnn, set a real age dude");
                    age = scanner.nextInt();
                }

                controller.signUpUser(name, lastName, age);
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Forbidden value entered... Try again");
            }
        }
    }

    public void byeBye() {
        System.out.println("Thanks for playing!!");
        System.exit(0);
    }

    public int showAndSelectConnections() {
        showAvailableConnections(controller.obtainAllConnectables());
        int selectedValue;
        while (true) {

            try {
                System.out.println("Enter any of the shown Ids");
                selectedValue = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Forbidden value... Please try again");
                scanner.nextLine();
            }
        }
        return selectedValue;
    }

    public String selectFromFamilyType(int id) {
        // !La logica de esta funcion es un horror. Debe de haber algun patron que nos
        // !sirva
        String parameter = controller.showFamilyTypeEnum(id);
        switch (parameter) {

            case "USER":
                int valueEntered;
                System.out.println(
                        "Choose the type of connection wanted... \n [1]--> Friend [2]--> Relative [3]--> Work Colleague [4]--> Barely Known");
                while (true) {

                    try {
                        valueEntered = scanner.nextInt();
                        while (valueEntered < 1 || valueEntered > 4) {
                            System.out.println("Incorrect range of numbers, please try again");
                            valueEntered = scanner.nextInt();

                        }
                        switch (valueEntered) {
                            case 1:
                                return "AMIGO";
                            case 2:
                                return "FAMILIA";

                            case 3:
                                return "TRABAJO";

                            case 4:
                                return "CONOCIDO";

                            default:
                                return "NONE";
                        }
                    } catch (Exception e) {
                        System.err.println("forbidden value entered");
                        scanner.nextLine();
                    }
                }
            case "GROUP":
                int valueEntered1;
                System.out.println(
                        "Choose the type of connection wanted... \n [1]--> Member [2]--> Administrator [3]--> Creator ");
                while (true) {

                    try {
                        valueEntered1 = scanner.nextInt();
                        while (valueEntered1 < 1 || valueEntered1 > 4) {
                            System.out.println("Incorrect range of numbers, please try again");
                            valueEntered1 = scanner.nextInt();

                        }
                        switch (valueEntered1) {
                            case 1:
                                return "MIEMBRO";
                            case 2:
                                return "ADMINISTRADOR";

                            case 3:
                                return "CREADOR";

                            default:
                                return "NONE";
                        }
                    } catch (Exception e) {
                        System.err.println("forbidden value entered");
                        scanner.nextLine();

                    }
                }
            case "WEB_PAGE":
                int valueEntered2;
                System.out.println(
                        "Choose the type of connection wanted... \n [1]--> Follower [2]--> Moderator ");
                while (true) {

                    try {
                        valueEntered2 = scanner.nextInt();
                        while (valueEntered2 < 1 || valueEntered2 > 4) {
                            System.out.println("Incorrect range of numbers, please try again");
                            valueEntered2 = scanner.nextInt();

                        }
                        switch (valueEntered2) {
                            case 1:
                                return "SEGUIDOR";
                            case 2:
                                return "MODERADOR";

                            default:
                                return "NONE";
                        }
                    } catch (Exception e) {
                        System.err.println("forbidden value entered");
                        scanner.nextLine();

                    }
                }

            default:
                return "NONE";
        }
    }

    public int showAndSelectSelfConnections() {
        showAvailableConnections(controller.obtainSelfConnections());
        int selectedValue;
        while (true) {

            try {
                System.out.println("Enter any of the shown Ids");
                selectedValue = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Forbidden value... Please try again");
                scanner.nextLine();

            }
        }
        return selectedValue;
    }

    public void showSelfConnections() {
        showAvailableConnections(controller.obtainSelfConnections());

    }

    public void obtainAndShowRegisteredUserInfo() {
        String userInfo = controller.obtainAllUserInfo();
        System.out.println("Displaying users info...\n" + userInfo);
    }
}
