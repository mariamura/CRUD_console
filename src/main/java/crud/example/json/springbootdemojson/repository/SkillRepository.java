package crud.example.json.springbootdemojson.repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Skill;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class SkillRepository {

    public static final String fileName = "skills.json";

    public Skill getById(Long id) {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);
        return targetCollection
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Skill> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        return new Gson().<ArrayList<Skill>>fromJson(FileUtils.readFile(fileName), targetClassType);
    }

    public Skill save(Skill skill) {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        Skill maxById = targetCollection
                .stream()
                .max(Comparator.comparing(Skill::getId))
                .orElse(null);

        Long maxId = Objects.nonNull(maxById) ? maxById.getId() : 0L;
        skill.setId(maxId+1);
        targetCollection.add(skill);
        FileUtils.writeToFile(new Gson().toJson(targetCollection), fileName);
        return skill;
    }

    public Skill update(Skill skill) {
        Skill skillNew = new Skill(skill.getId(), skill.getName());
        deleteById(skill.getId());
        save(skillNew);
        return skillNew;
    }

    public void deleteById(Long id)  {

        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        for(Skill skill: targetCollection) {
            if(skill.getId().equals(id)){
                targetCollection.remove(skill);
                break;
            }
        }
        String in = new Gson().toJson(targetCollection);
        FileUtils.writeToFile(in, fileName);
    }
}
