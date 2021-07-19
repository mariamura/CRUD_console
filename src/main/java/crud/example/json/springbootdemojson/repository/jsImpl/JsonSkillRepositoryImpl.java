package crud.example.json.springbootdemojson.repository.jsImpl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;
import crud.example.json.springbootdemojson.utils.FileUtils;
import crud.example.json.springbootdemojson.repository.SkillRepository;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

public class JsonSkillRepositoryImpl implements SkillRepository {

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

        skill.setId(getLastId()+1);

        if(targetCollection==null) {
            targetCollection = new ArrayList<>();
            targetCollection.add(skill);
        }
        else targetCollection.add(skill);

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

        if(targetCollection.stream().anyMatch(n->n.getId().equals(id))) {
            for (Skill skill : targetCollection) {
                if (skill.getId().equals(id)) {
                    targetCollection.remove(skill);
                    break;
                }
            }
            String in = new Gson().toJson(targetCollection);
            FileUtils.writeToFile(in, fileName);
        } else throw new NullPointerException();
    }

    public Long getLastId() {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        if(targetCollection==null || targetCollection.isEmpty()) {
            return 0L;
        } else {
            Skill maxById = targetCollection.stream()
                    .max(Comparator.comparing(Skill::getId)).orElse(null);
            return maxById.getId();
        }
    }

}
