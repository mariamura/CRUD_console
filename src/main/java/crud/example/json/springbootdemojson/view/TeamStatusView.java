package crud.example.json.springbootdemojson.view;

import java.util.Scanner;

public class TeamStatusView {

    private static Scanner sc = new Scanner(System.in);

    private static String teamStMenu =
            "================\n" +
                    "1. Deleted Teams\n" +
                    "2. Active Teams\n" +
                    "3. Exit\n" +
                    "================\n";

    public static void startTeamSt() {
        boolean exit = false;
        do {
            System.out.println(teamStMenu);
            String userInput = sc.next();
            switch (userInput) {
                case "1":
                    //
                    exit = true;
                    break;
                case "2":
                    //
                    exit = true;
                    break;
                case "3":
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
