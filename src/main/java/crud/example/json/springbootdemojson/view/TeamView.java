package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.TeamController;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamView {

    private static Scanner sc = new Scanner(System.in);

    private static String teamMenu =
            "================\n" +
            "1. Create new Team\n"+
            "2. Get Team by id\n" +
            "3. Update Team\n" +
            "4. Delete Team by id\n" +
            "5. Exit\n" +
            "================\n";

    private static TeamController teamController = new TeamController();

    public static void startTeam() {
        boolean exit = false;
        do {
            System.out.println(teamMenu);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1":
                    save();
                    exit = true;
                    break;
                case "2":
                    read();
                    exit = true;
                    break;
                case "3":
                    update();
                    exit = true;
                    break;
                case "4":
                    delete();
                    exit = true;
                    break;
                case "5":
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public static void save() {
        System.out.println("Team name:");
        String teamName = sc.nextLine();
        List<Developer> newDs = new ArrayList<>();
        try{
            Team newTeam = new Team(1L, teamName, newDs);
            teamController.save(newTeam);

        }catch (Exception e){
            System.out.println("Error during new team creation: " + e);
        }
    }

    public static void update() {
        System.out.println("Team id:");
        Long id = sc.nextLong();
        System.out.println("Team name:");
        String teamName = sc.nextLine();



        try{
            //
        }catch (Exception e) {
            System.out.println("Error while Team delete");
        }
    }

    public static void delete() {
        System.out.println("Team id:");
        Long id = sc.nextLong();
        try{
            teamController.deleteById(id);
            System.out.println("Team with id: " + id + " was deleted." );
        }catch (Exception e) {
            System.out.println("Error while Team delete");
        }
    }

    public static void read() {
        System.out.println("Team id:");
        Long id = sc.nextLong();
        try{
            System.out.println(teamController.getById(id).toString());
        }catch (Exception e) {
            System.out.println("Error while Team read");
        }
    }

}
