package crud.example.json.springbootdemojson;

import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.repository.SkillRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class StartProgram {
	public static void main(String[] args) throws IOException {

		Skill s = new Skill(1L, "java");
		Skill s1 = new Skill(2L, "php");
		Skill s2 = new Skill(3L, "sql");

		ArrayList<Skill> ls = new ArrayList<>();
		ls.add(s);
		ls.add(s1);
		ls.add(s2);


		String in = new Gson().toJson(ls);
		FileWriter fw = new FileWriter(SkillRepository.fileName);
		fw.write(in);
		fw.close();




	}
}
