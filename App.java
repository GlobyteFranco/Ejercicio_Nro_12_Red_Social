import java.util.Scanner;

import controllers.SocialController;
import repositories.ConnectablesRepositorie;
import utilities.SocialUtil;
import views.SocialView;

public class App {
    public static void main(String[] args) {
        SocialView socialView = new SocialView(
                new SocialController(new ConnectablesRepositorie(SocialUtil.hardcodedData())),
                new Scanner(System.in));
        System.out.println("Starting Program...");
        socialView.startProgram();

    }
}