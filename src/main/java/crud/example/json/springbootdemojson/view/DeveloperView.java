package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.DeveloperController;
import crud.example.json.springbootdemojson.controller.TeamController;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private static Scanner sc = new Scanner(System.in);

    private static String devMenu =
            "================\n" +
                    "1. Create new Developer\n"+
                    "2. Get Developer by id\n" +
                    "3. Update Developer\n" +
                    "4. Delete Developer by id\n" +
                    "5. Exit\n" +
                    "================\n";

    private static DeveloperController developerController = new DeveloperController();

    public static void startDev() {
        boolean exit = false;
        do {
            System.out.println(devMenu);
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
        System.out.println("Dev firstName:");
        String first = sc.nextLine();
        System.out.println("Dev lastName:");
        String last = sc.nextLine();
        System.out.println("Dev id:");
        Long id = sc.nextLong();
        List<Skill> newDs = new ArrayList<>();
        try{
            Developer newDev = new Developer(id, first, last, newDs);
            developerController.save(newDev);
        }catch (Exception e){
            System.out.println("Error during new dev creation: " + e);
        }
    }

    public static void update() {
        System.out.println("Dev id:");
        Long id = sc.nextLong();
        try{
            developerController.deleteById(id);
        }catch (Exception e) {
            System.out.println("Error while dev delete");
        }
    }

    public static void delete() {
        System.out.println("Dev id:");
        Long id = sc.nextLong();
        try{
            developerController.deleteById(id);
        }catch (Exception e) {
            System.out.println("Error while dev delete");
        }
    }

    public static void read() {
        System.out.println("Dev id:");
        Long id = sc.nextLong();
        try{
            developerController.getById(id);
        }catch (Exception e) {
            System.out.println("Error while dev read");
        }
    }

}
