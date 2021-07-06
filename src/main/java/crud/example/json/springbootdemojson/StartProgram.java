package crud.example.json.springbootdemojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.repository.SkillRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class StartProgram {
	public static void main(String[] args) throws IOException {

		Skill s = new Skill(1, "java");
		Skill s1 = new Skill(2, "php");
		Skill s2 = new Skill(3, "sql");
		ArrayList<Skill> ls = new ArrayList<>();
		ls.add(s);
		ls.add(s1);
		ls.add(s2);

		String in = new Gson().toJson(ls);
		FileWriter fw = new FileWriter(SkillRepository.file_name);
		fw.write(in);
		fw.close();


	}
}
