package crud.example.json.springbootdemojson.model;

public enum ConsoleMessage {
    
    LINE("================\n"),
    ERROR("Incorrect input!"),
    CREATED("Object was created, id: "),
    UPDATED("Object was updated, id: "),
    DELETED("Object was deleted, id: "),
    BACK_TO_MENU("'m' to main menu"),
    DEV_WARNING("Developer is already have this skill"),
    TEAM_WARNING("Team is already have this developer"),
    ENTER_NAME("Enter name:"),
    ENTER_FIRST_NAME("Enter firstName:"),
    ENTER_LAST_NAME("Enter lastname:"),
    ENTER_ID("Enter id"),
    ADD_SKILLS("Add skills (y/n):"),
    ADD_DEVELOPERS("Add developers (y/n):");

    private final String message;

    ConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
