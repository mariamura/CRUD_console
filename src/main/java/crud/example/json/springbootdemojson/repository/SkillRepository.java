package crud.example.json.springbootdemojson.repository;

import crud.example.json.springbootdemojson.model.Skill;
import java.util.*;

public interface SkillRepository extends GenericRepository<Skill, Long> {

    Skill save(Skill skill);

    Skill getById(Long id);

    void deleteById(Long id);

    List<Skill> getAll();

    Skill update(Skill skill);

    Long getLastId();
}
