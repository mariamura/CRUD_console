package crud.example.json.springbootdemojson.repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crud.example.json.springbootdemojson.model.Skill;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

public class SkillRepository {

    public static String file_name = "C:\\MariamM\\console_demo_crud\\src\\main\\resources\\skills.json";

    public Skill getById(long id) throws IOException {

        
        String file_content = "";
        FileReader fr = new FileReader(SkillRepository.file_name);
        while(fr.ready()) {
            file_content += (char)fr.read();
        }


        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        ArrayList<Skill> targetCollection = new Gson().fromJson(file_content, targetClassType);

        return targetCollection
                .stream()
                .filter(n -> n.getId()==id)
                .findFirst().get();
    }

    public List<Skill> getAll() throws IOException {

        String file_content = "";
        FileReader fr = new FileReader(SkillRepository.file_name);
        while(fr.ready()) {
            file_content += (char)fr.read();
        }


        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        return new Gson().<ArrayList<Skill>>fromJson(file_content, targetClassType);
    }

    public Skill save(Skill skill) throws IOException {

        String file_content = "";
        FileReader fr = new FileReader(SkillRepository.file_name);
        while(fr.ready()) {
            file_content += (char)fr.read();
        }


        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        ArrayList<Skill> targetCollection = new Gson().fromJson(file_content, targetClassType);


        Skill maxById = targetCollection
                .stream()
                .max(Comparator.comparing(Skill::getId))
                .orElseThrow(NoSuchElementException::new);
        long maxId = maxById.getId();
        skill.setId(maxId+1);
        return skill;
    }

    public Skill update(Skill skill) throws IOException {
        Skill skill_new = new Skill(skill.getId(), skill.getName());
        deleteById(skill.getId());
        save(skill_new);
        return skill_new;
    }

    public void deleteById(Long id) throws IOException {

        String file_content = "";
        FileReader fr = new FileReader(SkillRepository.file_name);
        while(fr.ready()) {
            file_content += (char)fr.read();
        }
        fr.close();


        Type targetClassType = new TypeToken<ArrayList<Skill>>() { }.getType();
        ArrayList<Skill> targetCollection = new Gson().fromJson(file_content, targetClassType);


        for(Skill skill: targetCollection) {
            if(skill.getId()==id){
                targetCollection.remove(skill);
                break;
            }
        }
        String in = new Gson().toJson(targetCollection);
        FileWriter fw = new FileWriter(file_name);
        fw.write(in);
        fw.close();
    }
}
