package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.model.TeamStatus;

import java.util.Scanner;

public class ConsoleStarter {

    private static Scanner sc = new Scanner(System.in);

    private static String mainMenu =
            "================\n" +
            "1. Teams\n" +
            "2. Developers\n" +
            "3. Skills\n" +
            "4. TeamStatus\n" +
            "5. Exit\n" +
            "================\n";

    public static void start() throws Exception {
        boolean exit = false;
        do {
            System.out.println(mainMenu);
            String userInput = sc.next();
            switch (userInput) {
                case "1":
                    TeamView.startTeam();
                    exit = true;
                    break;
                case "2":
                    DeveloperView.startDev();
                    exit = true;
                    break;
                case "3":
                    SkillView.startSkill();
                    exit = true;
                    break;
                case "4":
                    TeamStatusView.startTeamSt();
                    exit = true;
                    break;
                case "5":
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
