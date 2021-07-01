package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.view.DeveloperView;
import crud.example.json.springbootdemojson.view.SkillView;

import java.util.Scanner;

public class SkillController {
    static void startSC() {
        SkillView.startSV();
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
    }

    private static void update() {
    }

    private static void read() {
    }

    private static void create() {
    }
}
