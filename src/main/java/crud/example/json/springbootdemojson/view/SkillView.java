package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.SkillController;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {

    private static Scanner sc = new Scanner(System.in);

    private static String skillMenu =
            "================\n" +
                    "1. Create new Skill\n"+
                    "2. Get all Skills\n" +
                    "3. Get Skill by id\n" +
                    "4. Update Skill\n" +
                    "5. Delete Skill by id\n" +
                    "6. Main menu\n" +
                    "7. Exit\n" +
                    "================\n";

    private static SkillController skillController = new SkillController();

    public static void startSkill() throws Exception {
        boolean exit = false;
        System.out.println(skillMenu);
        String userInput = sc.nextLine();
        do {
            switch (userInput) {
                case "1":
                    save();
                    exit = true;
                    break;
                case "2":
                    readAll();
                    exit = true;
                    break;
                case "3":
                    read();
                    exit = true;
                    break;
                case "4":
                    update();
                    exit = true;
                    break;
                case "5":
                    delete();
                    exit = true;
                    break;
                case "6":
                    ConsoleStarter.start();
                case "7":
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private static void readAll() {
        System.out.println("================\n");
        skillController.getAll().
                stream().forEach(n->System.out.println(n.getId() + ":" + n.getName()));
        System.out.println("================\n");
        System.out.println("'m' to main menu\n");
        try{
            String userInput = sc.nextLine();
            if(userInput.equals("m")) startSkill();
            else throw new NumberFormatException("Incorrect input!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void save() {
        System.out.println("Skill name:");
        String skillName = sc.nextLine();
        try{
            Skill skill = new Skill(1L, skillName);
            skillController.save(skill);
            System.out.println("Skill with id: " + skill.getId() + " was created.");
            startSkill();
        }catch (Exception e){
            System.out.println("Error during new Skill creation: " + e);
        }
    }

    private static void update() {

        System.out.println("Skill id:");
        try{
            Long id = sc.nextLong();
            System.out.println("================\n"+
                    "1. update name\n"+
                    "2. exit\n" +
                    "================\n");
            Skill skill = skillController.getById(id);
            String userIn = sc.next();
            switch (userIn) {
                case "1" -> {
                    System.out.println("Enter new name:");
                    String newName = sc.next();
                    skill.setName(newName);
                    skillController.update(skill);
                }
                case "2" -> startSkill();
                default -> throw new Exception("Incorrect input!");
            }
        }catch (Exception e) {
            System.out.println("Error while Skill update: " + e);
        }
    }

    private static void delete() {
        System.out.println("Skill id:");
        Long id = sc.nextLong();
        try{
            skillController.deleteById(id);
            System.out.println("Skill with id: " + id + " was deleted.");
        }catch (Exception e) {
            System.out.println("Error while Skill delete: " + e);
        }
    }

    private static void read() {
        System.out.println("Skill id:");
        Long id = sc.nextLong();
        try{
            System.out.println(skillController.getById(id));
            startSkill();
        }catch (Exception e) {
            System.out.println("Error while Skill read: " + e);
        }
    }
}
