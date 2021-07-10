package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.SkillController;
import crud.example.json.springbootdemojson.controller.TeamController;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SkillView {

    private static Scanner sc = new Scanner(System.in);

    private static String skillMenu =
            "================\n" +
                    "1. Create new Skill\n"+
                    "2. Get Skill by id\n" +
                    "3. Update Skill\n" +
                    "4. Delete Skill by id\n" +
                    "5. Exit\n" +
                    "================\n";

    private static SkillController skillController = new SkillController();

    public static void startSkill() {
        boolean exit = false;
        do {
            System.out.println(skillMenu);
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
        System.out.println("Skill name:");
        String skillName = sc.nextLine();
        System.out.println("Skill id:");
        Long id = sc.nextLong();
        try{
            Skill skill = new Skill(id, skillName);
            skillController.save(skill);
        }catch (Exception e){
            System.out.println("Error during new Skill creation: " + e);
        }
    }

    public static void update() {
        System.out.println("Skill id:");
        Long id = sc.nextLong();
        try{
            skillController.deleteById(id);
        }catch (Exception e) {
            System.out.println("Error while Skill delete");
        }
    }

    public static void delete() {
        System.out.println("Skill id:");
        Long id = sc.nextLong();
        try{
            skillController.deleteById(id);
        }catch (Exception e) {
            System.out.println("Error while Skill delete");
        }
    }

    public static void read() {
        System.out.println("Skill id:");
        Long id = sc.nextLong();
        try{
            skillController.getById(id);
        }catch (Exception e) {
            System.out.println("Error while Skill read");
        }
    }
}
