package crud.example.json.springbootdemojson.repository;

import crud.example.json.springbootdemojson.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {

    public static String file_name = "C:\\MariamM\\console_demo_crud\\src\\main\\resources\\skills.json";

    public Skill getById(long id) {
        
        return new Skill(1, "noname");
    }

    public List<Skill> getAll() {

        return new ArrayList<>();
    }

    public Skill save(Skill skill) {

        return new Skill(1, "noname");
    }

    public Skill update(Skill skill) {

        return new Skill(1, "noname");
    }

    public void deleteById(Long id) {

    }
}
