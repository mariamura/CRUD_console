package crud.example.json.springbootdemojson.repository;

import crud.example.json.springbootdemojson.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {

    private final String file_name = "C:\\MariamM\\crud_console\\src\\resources\\skills.json";

    public Skill getById(long id){
        return new Skill();
    }

    public List<Skill> getAll(){
        return new ArrayList<>();
    }

    public Skill save(Skill skill) {
        return new Skill();
    }

    public Skill update(Skill skill){
        return new Skill();
    }

    public void deleteById(Long id){

    }
}
