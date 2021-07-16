package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.DeveloperController;
import crud.example.json.springbootdemojson.controller.SkillController;
import crud.example.json.springbootdemojson.model.ConsoleMessage;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;
import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    private final static Scanner sc = new Scanner(System.in);

    private static final String devMenu =
                    ConsoleMessage.LINE.getMessage() +
                    "1. Create new Developer\n"+
                    "2. Get all Developers\n" +
                    "3. Get Developer by id\n" +
                    "4. Update Developer\n" +
                    "5. Delete Developer by id\n" +
                    "6. Main menu\n" +
                    "7. Exit\n" +
                    ConsoleMessage.LINE.getMessage();

    private static final DeveloperController developerController = new DeveloperController();
    private static final SkillController skillController = new SkillController();

    public static void startDev() throws Exception {
        boolean exit = false;
        System.out.println(devMenu);
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

    private static void save() {
        try{
            System.out.println(ConsoleMessage.ENTER_FIRST_NAME.getMessage());
            String first = sc.nextLine();
            System.out.println(ConsoleMessage.ENTER_LAST_NAME.getMessage());
            String last = sc.nextLine();
            List<Skill> newSkills = new ArrayList<>();
            boolean exit = false;
            Long skillId;
            do {
                System.out.println(ConsoleMessage.ADD_SKILLS.getMessage());
                if(sc.nextLine().equals("n")) {
                    exit = true;
                    break;
                }
                skillController.getAll().stream().forEach
                        (n->System.out.println(n.getId() + ": " + n.getName()));

                skillId = sc.nextLong();
                Long finalSkillId = skillId;
                if(newSkills.stream().anyMatch(n->n.getId().equals(finalSkillId))) {
                    System.out.println(ConsoleMessage.DEV_WARNING.getMessage());
                }
                else {
                    newSkills.add(skillController.getById(finalSkillId));
                }
                System.out.println(ConsoleMessage.ADD_SKILLS.getMessage());
                String answer = sc.next();
                if(answer.equals("y")) {
                }
                else{
                    exit = true;
                }
            }while (!exit);
            Developer newDev = new Developer(1L, first, last, newSkills);
            developerController.save(newDev);
            System.out.println(ConsoleMessage.CREATED.getMessage() + newDev.getId());
            startDev();
        }catch (Exception e){
            System.out.println("Error during saving: " + e);
        }
    }

    private static void readAll() {
        System.out.println(ConsoleMessage.LINE.getMessage());
        developerController.getAll().stream().
                forEach(n-> System.out.println(n.getId()+ ": "+ n.getFirstName() + " " + n.getLastName()));
        System.out.println(ConsoleMessage.LINE.getMessage());
        System.out.println(ConsoleMessage.BACK_TO_MENU.getMessage());
        try{
            String userInput = sc.nextLine();
            if(userInput.equals("m")) startDev();
            else throw new NumberFormatException(ConsoleMessage.ERROR.getMessage());
        } catch (Exception e) {
            System.out.println("Error during readingAll: " + e);
        }
    }

    private static void update() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try{
            Long id = sc.nextLong();
            System.out.println(ConsoleMessage.LINE.getMessage() +
                    "1. update first name\n"+
                    "2. update last name\n" +
                    "3. update skills\n" +
                    "4. exit\n" +
                    ConsoleMessage.LINE.getMessage());
            Developer developer = developerController.getById(id);
            List<Skill> newSkills = developer.getSkills();
            Long skillId;
            String userIn = sc.next();
            switch (userIn) {
                case "1" -> {
                    System.out.println(ConsoleMessage.ENTER_FIRST_NAME.getMessage());
                    String newFirstName = sc.next();
                    developer.setFirstName(newFirstName);
                    developerController.update(developer);
                }
                case "2" -> {
                    System.out.println(ConsoleMessage.ENTER_LAST_NAME.getMessage());
                    String newLastName = sc.next();
                    developer.setLastName(newLastName);
                    developerController.update(developer);
                }
                case "3" -> {
                    System.out.println(ConsoleMessage.ADD_SKILLS.getMessage());
                    newSkills.stream().forEach
                            (n -> System.out.println(n.getId() + ": " + n.getName()));
                    skillId = sc.nextLong();
                    Long finalSkillId = skillId;
                    if ( skillController.getAll().stream().anyMatch(n -> n.getId().equals(finalSkillId))) {
                        System.out.println(ConsoleMessage.DEV_WARNING.getMessage());
                    } else {
                        newSkills.add(skillController.getById(skillId));
                    }

                }
                case "4" -> startDev();
                default -> throw new Exception(ConsoleMessage.ERROR.getMessage());
            }
            System.out.println(ConsoleMessage.UPDATED.getMessage() + id);
        }catch (Exception e) {
            System.out.println("Error while developer update: " + e);
        }
    }

    private static void delete() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try{
            Long id = sc.nextLong();
            developerController.deleteById(id);
            System.out.println(ConsoleMessage.DELETED.getMessage() + id);
        }catch (Exception e) {
            System.out.println("Error while developer delete: " + e);
        }
    }

    private static void read() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try{
            Long id = sc.nextLong();
            System.out.println(developerController.getById(id));
            startDev();
        }catch (Exception e) {
            System.out.println("Error while developer read: " + e);
        }
    }

}
