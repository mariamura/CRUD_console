package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.DeveloperController;
import crud.example.json.springbootdemojson.controller.SkillController;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    private static Scanner sc = new Scanner(System.in);

    private static String devMenu =
                    "================\n" +
                    "1. Create new Developer\n"+
                    "2. Get all Developers\n" +
                    "3. Get Developer by id\n" +
                    "4. Update Developer\n" +
                    "5. Delete Developer by id\n" +
                    "6. Main menu\n" +
                    "7. Exit\n" +
                    "================\n";

    private static DeveloperController developerController = new DeveloperController();
    private static SkillController skillController = new SkillController();

    public static void startDev() throws Exception {
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
            System.out.println("Dev firstName:");
            String first = sc.nextLine();
            System.out.println("Dev lastName:");
            String last = sc.nextLine();
            List<Skill> newSkills = new ArrayList<>();
            boolean exit = false;
            Long skillId;
            do {
                System.out.println("Add skills (y/n):");
                if(sc.nextLine().equals("n")) {
                    exit = true;
                    break;
                }
                skillController.getAll().stream().forEach
                        (n->System.out.println(n.getId() + ": " + n.getName()));
                skillId = sc.nextLong();
                Long finalSkillId = skillId;
                if(newSkills.stream().anyMatch(n->n.getId().equals(finalSkillId))) {
                    System.out.println("Developer is already have this skill");
                }
                else {
                    newSkills.add(skillController.getById(finalSkillId));
                }
                System.out.println("Add more skills (y/n)");
                String answer = sc.next();
                if(answer.equals("y")) {
                }
                else{
                    exit = true;
                }
            }while (!exit);
            Developer newDev = new Developer(1L, first, last, newSkills);
            developerController.save(newDev);
            System.out.println("Developer with id: " + newDev.getId() + " was created.");
            startDev();
        }catch (Exception e){
            System.out.println("Error during new dev creation: " + e);
        }
    }

    private static void readAll() {
        System.out.println("================\n");
        developerController.getAll().stream().
                forEach(n-> System.out.println(n.getId()+ ": "+ n.getFirstName() + " " + n.getLastName()));
        System.out.println("================\n");
        System.out.println("'m' to main menu\n");

        try{
            String userInput = sc.nextLine();
            if(userInput.equals("m")) startDev();
            else throw new NumberFormatException("Incorrect input!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void update() {
        System.out.println("Dev id:");
        try{
            Long id = sc.nextLong();
            System.out.println("================\n"+
                    "1. update first name\n"+
                    "2. update last name\n" +
                    "3. update skills\n" +
                    "4. exit\n" +
                    "================\n");
            Developer developer = developerController.getById(id);
            List<Skill> newSkills = developer.getSkills();
            Long skillId;
            String userIn = sc.next();
            switch (userIn) {
                case "1" -> {
                    System.out.println("Enter new first name:");
                    String newFirstName = sc.next();
                    developer.setFirstName(newFirstName);
                    developerController.update(developer);
                }
                case "2" -> {
                    System.out.println("Enter new last name:");
                    String newLastName = sc.next();
                    developer.setLastName(newLastName);
                    developerController.update(developer);
                }
                case "3" -> {
                    System.out.println("Add skills (enter id):");
                    newSkills.stream().forEach
                            (n -> System.out.println(n.getId() + ": " + n.getName()));
                    skillId = sc.nextLong();
                    Long finalSkillId = skillId;
                    if ( skillController.getAll().stream().anyMatch(n -> n.getId().equals(finalSkillId))) {
                        System.out.println("Developer is already have this skill.");
                    } else {
                        newSkills.add(skillController.getById(skillId));
                    }

                }
                case "4" -> startDev();
                default -> throw new Exception("Incorrect input!");
            }
            System.out.println("Developer with id: " + id + " was updated.");
        }catch (Exception e) {
            System.out.println("Error while developer update: " + e);
        }
    }

    private static void delete() {
        System.out.println("Developer id:");
        try{
            Long id = sc.nextLong();
            developerController.deleteById(id);
            System.out.println("Developer with id: " + id + " was deleted.");
        }catch (Exception e) {
            System.out.println("Error while developer delete: " + e);
        }
    }

    private static void read() {
        System.out.println("Developer id:");
        try{
            Long id = sc.nextLong();
            System.out.println(developerController.getById(id));
        }catch (Exception e) {
            System.out.println("Error while developer read: " + e);
        }
    }

}
