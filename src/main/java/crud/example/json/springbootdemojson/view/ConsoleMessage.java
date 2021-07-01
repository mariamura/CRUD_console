package crud.example.json.springbootdemojson.view;


public enum ConsoleMessage {
    ENTER_ID("Enter ID"),
    ERROR("ERROR!");

    String message;

    ConsoleMessage(String s) {
        message = s;
    }
}
