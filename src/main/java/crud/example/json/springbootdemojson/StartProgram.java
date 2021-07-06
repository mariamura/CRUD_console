package crud.example.json.springbootdemojson;

import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.repository.SkillRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class StartProgram {
	public static void main(String[] args) throws IOException {
		Skill s = new Skill(1, "java");

		String in = new Gson().toJson(s);
		FileWriter fr = new FileWriter(SkillRepository.file_name);
		fr.write(in);
		fr.close();

	}
}
