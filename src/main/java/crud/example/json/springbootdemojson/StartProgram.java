package crud.example.json.springbootdemojson;

import crud.example.json.springbootdemojson.controller.GenericController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartProgram {
	public static void main(String[] args) {
		GenericController.startGC();
	}
}
