package crud.example.json.springbootdemojson.repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crud.example.json.springbootdemojson.model.Skill;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

public class SkillRepository {

    public static String fileName = "console_demo_crud\\src\\main\\resources\\skills.json";

    public Skill getById(Long id) {

        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> targetCollection = new Gson().fromJson(getPath(fileName), targetClassType);

        return targetCollection
                .stream()
                .filter(n -> n.getId()==id)
                .findFirst().orElse(null);
    }

    public List<Skill> getAll() {

        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        return new Gson().<ArrayList<Skill>>fromJson(readFile(), targetClassType);
    }

    public Skill save(Skill skill) {

        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> targetCollection = new Gson().fromJson(getPath(fileName), targetClassType);

        Skill maxById = targetCollection
                .stream()
                .max(Comparator.comparing(Skill::getId))
                .orElse(null);

        long maxId = Objects.nonNull(maxById) ?maxById.getId() : 0L;
        skill.setId(maxId+1);
        return skill;
    }

    public Skill update(Skill skill) {
        Skill skill_new = new Skill(skill.getId(), skill.getName());
        deleteById(skill.getId());
        save(skill_new);
        return skill_new;
    }

    private void deleteById(Long id)  {

        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        List<Skill> targetCollection = new Gson().fromJson(getPath(fileName), targetClassType);


        for(Skill skill: targetCollection) {
            if(skill.getId()==id){
                targetCollection.remove(skill);
                break;
            }
        }
        String in = new Gson().toJson(targetCollection);
        writeToFile(in);
    }

    private String readFile() {
        String fileContent = "";
        try(FileReader fr = new FileReader(getPath(fileName))){
            while(fr.ready()) {
                fileContent += (char)fr.read();
            }
        }catch (IOException e) {
            System.out.println("Error while file reading: " + e);
        }
        return fileContent;
    }

    private void writeToFile(String in) {
        try(FileWriter fw = new FileWriter(fileName)) {
            fw.write(in);
        }catch (IOException e) {
            System.out.println("Error while writing to the file: " + e);
        }
    }

    private String getPath(String filename) {
        File f = new File(filename);
        return f.getPath();
    }
}
