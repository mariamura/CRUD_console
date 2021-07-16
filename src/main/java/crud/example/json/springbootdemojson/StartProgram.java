package crud.example.json.springbootdemojson;

import crud.example.json.springbootdemojson.view.ConsoleStarter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartProgram {
	public static void main(String[] args) throws Exception {
		ConsoleStarter.start();
		/*Skill s = new Skill(1L, "java");
		Skill s1 = new Skill(2L, "php");
		Skill s2 = new Skill(3L, "sql");
		Skill s3 = new Skill(4L, "js");

		ArrayList<Skill> ls = new ArrayList<>();
		ls.add(s);
		ls.add(s1);
		ls.add(s2);
		ls.add(s3);

		String in = new Gson().toJson(ls);
		FileWriter fw = new FileWriter(FileUtils.getPath(SkillRepository.fileName));
		fw.write(in);

		ArrayList<Skill> mishaL = new ArrayList<>();
		mishaL.add(s);
		mishaL.add(s1);

		ArrayList<Skill> ninaL = new ArrayList<>();
		ninaL.add(s2);
		mishaL.add(s3);

		Developer misha = new Developer(1L, "Misha", "Vazovskiy", mishaL);
		Developer nina = new Developer(1L, "Nina", "McGonagal", ninaL);

		ArrayList<Developer> developers = new ArrayList<>();
		developers.add(misha);
		developers.add(nina);

		String developerJson = new Gson().toJson(developers);
		FileWriter fw2 = new FileWriter(FileUtils.getPath(DeveloperRepository.fileName));
		fw2.write(developerJson);


		Team wings = new Team(1L, "Wings", developers);

		ArrayList<Team> teamList = new ArrayList<>();
		teamList.add(wings);

		String teamsJson = new Gson().toJson(teamList);
		FileWriter fw3 = new FileWriter(FileUtils.getPath(TeamRepository.fileName));
		fw3.write(teamsJson);


		fw.close();
		fw2.close();
		fw3.close();*/
	}
}
