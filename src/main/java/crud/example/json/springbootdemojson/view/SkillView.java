package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.SkillController;
import crud.example.json.springbootdemojson.model.ConsoleMessage;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {

    private static final Scanner sc = new Scanner(System.in);

    private static final String skillMenu =
                    ConsoleMessage.LINE.getMessage() +
                    "1. Create new Skill\n"+
                    "2. Get all Skills\n" +
                    "3. Get Skill by id\n" +
                    "4. Update Skill\n" +
                    "5. Delete Skill by id\n" +
                    "6. Main menu\n" +
                    "7. Exit\n" +
                    ConsoleMessage.LINE.getMessage();

    private static final String updateMenu =
                    ConsoleMessage.LINE.getMessage()+
                    "1. update name\n"+
                    "2. exit\n" +
                    ConsoleMessage.LINE.getMessage();

    private static final SkillController skillController = new SkillController();

    public static void startSkill() throws Exception {
        boolean exit = false;
        System.out.println(skillMenu);
        do {
            String userInput = sc.nextLine();
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
        System.out.println(ConsoleMessage.LINE.getMessage());
        skillController.printAll();
        System.out.println(ConsoleMessage.LINE.getMessage());
        System.out.println(ConsoleMessage.BACK_TO_MENU.getMessage());
        try{
            String userInput = sc.nextLine();
            if(userInput.equals("m")) startSkill();
            else throw new NumberFormatException(ConsoleMessage.ERROR.getMessage());
        } catch (Exception e) {
            System.out.println("Error during readingAll: " + e);
        }
    }

    private static void save() {
        System.out.println(ConsoleMessage.ENTER_NAME.getMessage());
        String skillName = sc.nextLine();
        try{
            Skill skill = new Skill(1L, skillName);
            skillController.save(skill);
            System.out.println(ConsoleMessage.CREATED.getMessage() + skill.getId());
            startSkill();
        }catch (Exception e){
            System.out.println("Error during new Skill creation: " + e);
        }
    }

    private static void update() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try{
            Long id = sc.nextLong();
            System.out.println(updateMenu);
            Skill skill = skillController.getById(id);
            String userIn = sc.next();
            switch (userIn) {
                case "1" -> {
                    System.out.println(ConsoleMessage.ENTER_NAME.getMessage());
                    String newName = sc.next();
                    skill.setName(newName);
                    skillController.update(skill);
                }
                case "2" -> startSkill();
                default -> throw new Exception(ConsoleMessage.ERROR.getMessage());
            }
        }catch (Exception e) {
            System.out.println("Error while skill update: " + e);
        }
    }

    private static void delete() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        Long id = sc.nextLong();
        try{
            skillController.deleteById(id);
            System.out.println(ConsoleMessage.DELETED.getMessage() + id);
            startSkill();
        }catch (Exception e) {
            System.out.println("Error while skill delete: " + e);
        }
    }

    private static void read() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try{
            Long id = sc.nextLong();
            System.out.println(skillController.getById(id));
            startSkill();
        }catch (Exception e) {
            System.out.println("Error while skill read: " + e);
        }
    }
}
