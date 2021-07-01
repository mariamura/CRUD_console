package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.model.Team;
import crud.example.json.springbootdemojson.view.ConsoleMessage;
import crud.example.json.springbootdemojson.view.TeamView;

import java.util.Scanner;

public class TeamController {
    static void startTC() {
        TeamView.startTV();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        do {
            String response = sc.next();
            switch (response) {
                case "1" -> create();
                case "2" -> read();
                case "3" -> update();
                case "4" -> delete();
                case "5" -> isExit = true;
                default -> System.out.println("Error!");
            }
        }while(!isExit);
        sc.close();
    }

    private static void delete() {
        System.out.println(ConsoleMessage.ENTER_ID);
        Scanner sc = new Scanner(System.in);
        Integer idByUser = sc.nextInt();

    }

    private static void read() {
        System.out.println(ConsoleMessage.ENTER_ID);
        Scanner sc = new Scanner(System.in);
    }

    private static void update() {
        System.out.println(ConsoleMessage.ENTER_ID);
        Scanner sc = new Scanner(System.in);
    }

    private static void create() {
        System.out.println(ConsoleMessage.ENTER_ID);
        Scanner sc = new Scanner(System.in);
        //
    }

}
