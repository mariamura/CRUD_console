package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.repository.SkillRepository;
import crud.example.json.springbootdemojson.repository.jsImpl.JsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {

    SkillRepository jsonSkillRepository = new JsonSkillRepositoryImpl();

    public List<Skill> getAll(){
        return jsonSkillRepository.getAll();
    }

    public Skill getById(Long id){
        return jsonSkillRepository.getById(id);
    }

    public void save(Skill skill){
        jsonSkillRepository.save(skill);
    }

    public void update(Skill skill){
        jsonSkillRepository.update(skill);
    }

    public void deleteById(Long id){
        jsonSkillRepository.deleteById(id);
    }

    public void printAll() {
        jsonSkillRepository.getAll().stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getName()));
    }
}
