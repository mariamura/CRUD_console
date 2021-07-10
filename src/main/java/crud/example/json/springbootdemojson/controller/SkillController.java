package crud.example.json.springbootdemojson.controller;


import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.repository.SkillRepository;

import java.util.List;

public class SkillController {
    SkillRepository skillRepository = new SkillRepository();

    public List<Skill> getAll(){
        return skillRepository.getAll();
    }

    public Skill getById(Long id){
        return skillRepository.getById(id);
    }

    public void save(Skill skill){
        skillRepository.save(skill);
    }

    public void update(Skill skill){
        skillRepository.update(skill);
    }

    public void deleteById(Long id){
        skillRepository.deleteById(id);
    }
}
