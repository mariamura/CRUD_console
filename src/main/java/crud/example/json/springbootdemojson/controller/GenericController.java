package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.view.ConsoleMessage;
import crud.example.json.springbootdemojson.view.MenuView;

import java.util.Scanner;

public class GenericController {
    public static void startGC() {
        MenuView.startMV();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        do {
            String response = sc.next();
            switch (response) {
                case "1" -> TeamController.startTC();
                case "2" -> DeveloperController.startDC();
                case "3" -> SkillController.startSC();
                case "4" -> TeamStatusController.startTSC();
                case "5" -> isExit = true;
                default -> System.out.println("Error!");
            }
        }while(!isExit);
        sc.close();
    }


}
