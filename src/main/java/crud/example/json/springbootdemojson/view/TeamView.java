package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.DeveloperController;
import crud.example.json.springbootdemojson.controller.TeamController;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Team;
import crud.example.json.springbootdemojson.model.TeamStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamView {

    private static Scanner sc = new Scanner(System.in);

    private static String teamMenu =
            "================\n" +
            "1. Create new Team\n"+
            "2. Get all teams\n" +
            "3. Get Team by id\n" +
            "4. Update Team\n" +
            "5. Delete Team by id\n" +
            "6. Main menu\n" +
            "7. Exit\n" +
            "================\n";

    private static TeamController teamController = new TeamController();
    private static DeveloperController developerController = new DeveloperController();

    public static void startTeam() throws Exception {
        boolean exit = false;
        System.out.println(teamMenu);
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
        System.out.println("================\n");
        teamController.getAll().stream().
                forEach(n-> System.out.println(n.getId()+": "+n.getName()));
        System.out.println("================\n");
        System.out.println("'m' to main menu\n");

        try{
            String userInput = sc.nextLine();
            if(userInput.equals("m")) startTeam();
            else throw new NumberFormatException("Incorrect input!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void save() {
        System.out.println("Team name:");
        String teamName = sc.nextLine();
        try{
            List<Developer> newDs = new ArrayList<>();
            boolean exit = false;
            Long devId;
            do {
                System.out.println("Add developers (y/n):");
                if(sc.nextLine().equals("n")) {
                    exit = true;
                    break;
                }
                developerController.getAll().stream().forEach
                        (n->System.out.println(n.getId() + ": " + n.getFirstName() + " " + n.getLastName()));
                devId = sc.nextLong();
                Long finalDevId = devId;
                if(newDs.stream().anyMatch(n->n.getId().equals(finalDevId))) {
                    System.out.println("Developer is already in team");
                }
                else {
                    newDs.add(developerController.getById(devId));
                }
                System.out.println("Add more developers (y/n)");
                String answer = sc.next();
                if(answer.equals("y")) {
                }
                else{
                    exit = true;
                }
            }while (!exit);

            TeamStatus teamStatus;
            System.out.println("Team status:\n" +
                    "1. ACTIVE\n"+
                    "2. DELETED\n");
            String answer = sc.next();
            switch (answer) {
                case "1" -> teamStatus = TeamStatus.ACTIVE;
                case "2" -> teamStatus = TeamStatus.DELETED;
                default  -> throw new NumberFormatException("Incorrect status!");
            }
            Team newTeam = new Team(1L, teamName, newDs, teamStatus);
            teamController.save(newTeam);
            System.out.println("New team " + teamName + "with id : " + newTeam.getId() + " was created" );
            startTeam();
        }catch (Exception e){
            System.out.println("Error during new team creation: " + e);
        }
    }

    private static void update() {
        System.out.println("Team id:");
        try {
            Long id = sc.nextLong();
            System.out.println("================\n" +
                    "1. update name\n" +
                    "2. update developers\n" +
                    "3. update status\n" +
                    "4. exit\n" +
                    "================\n");
            Team team = teamController.getById(id);
            Long devId;
            String userIn = sc.next();
            switch (userIn) {
                case "1" -> {
                    System.out.println("Enter new team name:");
                    String newName = sc.next();
                    team.setName(newName);
                    System.out.println(team.toString());
                    teamController.update(team);
                }
                case "2" -> {
                    List<Developer> newDs = team.getDevelopers();
                    System.out.println("Add developers (enter id):");
                    developerController.getAll().stream().forEach
                            (n -> System.out.println(n.getId() + ": " + n.getFirstName() + " " + n.getLastName()));
                    devId = sc.nextLong();
                    Long finalDevId = devId;
                    if (newDs.stream().anyMatch(n -> n.getId().equals(finalDevId))) {
                        System.out.println("Developer is already in team.");
                    } else {
                        newDs.add(developerController.getById(devId));
                    }
                }
                case "3" -> {
                    System.out.println("Team status:\n" +
                            "1. ACTIVE\n" +
                            "2. DELETED\n");
                    String answer = sc.next();
                    if (answer.equals("1")) team.setTeamStatus(TeamStatus.ACTIVE);
                    else if (answer.equals("2")) team.setTeamStatus(TeamStatus.DELETED);
                    else throw new NumberFormatException("Incorrect status!");

                }
                case "4" -> startTeam();
                default -> throw new Exception("Incorrect input!");
            }
            System.out.println("Team with id: " + id + " was updated.");
            startTeam();
        }catch (Exception e) {
            System.out.println("Error while Team update: " + e);
        }

    }

    private static void delete() {
        System.out.println("Team id:");
        try{
            Long id = sc.nextLong();
            teamController.deleteById(id);
            System.out.println("Team with id: " + id + " was deleted.");
            startTeam();
        }catch (Exception e) {
            System.out.println("Error while Team delete: " + e);
        }
    }

    private static void read() {
        System.out.println("Team id:");
        try{
            Long id = sc.nextLong();
            System.out.println(teamController.getById(id).toString());
            startTeam();
        }catch (Exception e) {
            System.out.println("Error while Team read: " + e);
        }
    }

}
