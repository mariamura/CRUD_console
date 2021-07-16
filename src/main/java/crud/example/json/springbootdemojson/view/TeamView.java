package crud.example.json.springbootdemojson.view;

import crud.example.json.springbootdemojson.controller.DeveloperController;
import crud.example.json.springbootdemojson.controller.TeamController;
import crud.example.json.springbootdemojson.model.ConsoleMessage;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Team;
import crud.example.json.springbootdemojson.model.TeamStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamView {

    private static final Scanner sc = new Scanner(System.in);

    private static final String teamMenu =
                    ConsoleMessage.LINE.getMessage() +
                    "1. Create new Team\n"+
                    "2. Get all teams\n" +
                    "3. Get Team by id\n" +
                    "4. Update Team\n" +
                    "5. Delete Team by id\n" +
                    "6. Main menu\n" +
                    "7. Exit\n" +
                    ConsoleMessage.LINE.getMessage();

    private static final String teamStMenu =
                    ConsoleMessage.LINE.getMessage() +
                    "Team status:\n"+
                    "1. active\n"+
                    "2. deleted\n"+
                    ConsoleMessage.LINE.getMessage();

    private static final String teamUpMenu =
                    ConsoleMessage.LINE.getMessage() +
                    "1. update name\n" +
                    "2. update developers\n" +
                    "3. update status\n" +
                    "4. exit\n" +
                    ConsoleMessage.LINE.getMessage();

    private static final TeamController teamController = new TeamController();
    private static final DeveloperController developerController = new DeveloperController();

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
        System.out.println(ConsoleMessage.LINE.getMessage());
        teamController.printAll();
        System.out.println(ConsoleMessage.LINE.getMessage());
        System.out.println(ConsoleMessage.BACK_TO_MENU.getMessage());
        try{
            String userInput = sc.nextLine();
            if(userInput.equals("m")) startTeam();
            else throw new NumberFormatException(ConsoleMessage.ERROR.getMessage());
        } catch (Exception e) {
            System.out.println("Error during readingAll: " + e);
        }
    }

    private static void save() {
        System.out.println(ConsoleMessage.ENTER_NAME.getMessage());
        String teamName = sc.nextLine();
        try{
            List<Developer> newDs = new ArrayList<>();
            boolean exit = false;
            Long devId;
            do {
                System.out.println(ConsoleMessage.ADD_DEVELOPERS.getMessage());
                if(sc.nextLine().equals("n")) {
                    exit = true;
                    break;
                }
                developerController.printAll();
                devId = sc.nextLong();
                Long finalDevId = devId;
                if(newDs.stream().anyMatch(n->n.getId().equals(finalDevId))) {
                    System.out.println(ConsoleMessage.DEV_WARNING.getMessage());
                }
                else {
                    newDs.add(developerController.getById(devId));
                }
                System.out.println(ConsoleMessage.ADD_DEVELOPERS.getMessage());
                String answer = sc.next();
                if(answer.equals("y")) {
                }
                else{
                    exit = true;
                }
            }while (!exit);

            TeamStatus teamStatus;
            System.out.println(teamStMenu);
            String answer = sc.next();
            switch (answer) {
                case "1" -> teamStatus = TeamStatus.ACTIVE;
                case "2" -> teamStatus = TeamStatus.DELETED;
                default  -> throw new NumberFormatException(ConsoleMessage.ERROR.getMessage());
            }
            Team newTeam = new Team(1L, teamName, newDs, teamStatus);
            teamController.save(newTeam);
            System.out.println(ConsoleMessage.CREATED.getMessage() + newTeam.getId());
            startTeam();
        }catch (Exception e){
            System.out.println("Error during new team creation: " + e);
        }
    }

    private static void update() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try {
            Long id = sc.nextLong();
            System.out.println(teamUpMenu);
            Team team = teamController.getById(id);
            Long devId;
            String userIn = sc.next();
            switch (userIn) {
                case "1" -> {
                    System.out.println(ConsoleMessage.ENTER_NAME.getMessage());
                    String newName = sc.next();
                    team.setName(newName);
                    System.out.println(team.toString());
                    teamController.update(team);
                }
                case "2" -> {
                    List<Developer> newDs = team.getDevelopers();
                    System.out.println(ConsoleMessage.ADD_DEVELOPERS.getMessage());
                    developerController.printAll();
                    devId = sc.nextLong();
                    Long finalDevId = devId;
                    if (newDs.stream().anyMatch(n -> n.getId().equals(finalDevId))) {
                        System.out.println(ConsoleMessage.TEAM_WARNING.getMessage());
                    } else {
                        newDs.add(developerController.getById(devId));
                    }
                }
                case "3" -> {
                    System.out.println(teamStMenu);
                    String answer = sc.next();
                    if (answer.equals("1")) team.setTeamStatus(TeamStatus.ACTIVE);
                    else if (answer.equals("2")) team.setTeamStatus(TeamStatus.DELETED);
                    else throw new NumberFormatException(ConsoleMessage.ERROR.getMessage());
                }
                case "4" -> startTeam();
                default -> throw new Exception(ConsoleMessage.ERROR.getMessage());
            }
            System.out.println(ConsoleMessage.UPDATED.getMessage() + id);
            startTeam();
        }catch (Exception e) {
            System.out.println("Error while Team update: " + e);
        }

    }

    private static void delete() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try{
            Long id = sc.nextLong();
            teamController.deleteById(id);
            System.out.println(ConsoleMessage.DELETED.getMessage() + id);
            startTeam();
        }catch (Exception e) {
            System.out.println("Error while Team delete: " + e);
        }
    }

    private static void read() {
        System.out.println(ConsoleMessage.ENTER_ID.getMessage());
        try{
            Long id = sc.nextLong();
            System.out.println(teamController.getById(id).toString());
            startTeam();
        }catch (Exception e) {
            System.out.println("Error while Team read: " + e);
        }
    }

}
