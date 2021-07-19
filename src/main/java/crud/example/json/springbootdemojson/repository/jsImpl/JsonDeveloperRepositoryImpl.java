package crud.example.json.springbootdemojson.repository.jsImpl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.repository.DeveloperRepository;
import crud.example.json.springbootdemojson.utils.FileUtils;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {

    public static final String fileName = "developers.json";

    public Developer getById(Long id) {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        return targetCollection
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Developer> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        return new Gson().<ArrayList<Developer>>fromJson(FileUtils.readFile(fileName), targetClassType);
    }

    public Developer save(Developer developer) {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        developer.setId(getLastId()+1);

        if(targetCollection==null) {
            targetCollection = new ArrayList<>();
            targetCollection.add(developer);
        }
        else targetCollection.add(developer);

        FileUtils.writeToFile(new Gson().toJson(targetCollection), fileName);
        return developer;
    }

    public Developer update(Developer developer) {
        Developer developerNew = new Developer(
                developer.getId(),
                developer.getFirstName(),
                developer.getLastName(),
                developer.getSkills());

        deleteById(developer.getId());
        save(developerNew);
        return developerNew;
    }

    public void deleteById(Long id)  {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        if(targetCollection.stream().anyMatch(n->n.getId().equals(id))) {
            for(Developer developer : targetCollection) {
                if(developer.getId().equals(id)) {
                    targetCollection.remove(developer);
                    break;
                }
            }
            String in = new Gson().toJson(targetCollection);
            FileUtils.writeToFile(in, fileName);
        } else throw new RuntimeException();
    }

    public Long getLastId() {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);
        if(targetCollection==null || targetCollection.isEmpty()) {
            return 0L;
        } else {
            Developer maxById = targetCollection.stream()
                    .max(Comparator.comparing(Developer::getId)).orElse(null);
            return maxById.getId();
        }
    }
}
