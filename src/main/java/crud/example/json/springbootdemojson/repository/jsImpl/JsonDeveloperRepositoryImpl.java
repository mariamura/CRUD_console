package crud.example.json.springbootdemojson.repository.jsImpl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.repository.DeveloperRepository;
import crud.example.json.springbootdemojson.repository.FileUtils;

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

        Developer maxById = collectionToStream(targetCollection)
                .max(Comparator.comparing(Developer::getId))
                .orElse(null);

        Long maxId = Objects.nonNull(maxById) ? maxById.getId() : 0L;
        developer.setId(maxId+1);

        List<Developer> newTargetCollections = new ArrayList<>(Optional.ofNullable(targetCollection).orElse(Collections.emptyList()));;
        newTargetCollections.add(developer);
        FileUtils.writeToFile(new Gson().toJson(newTargetCollections), fileName);
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

    public Long getLastId() {
        return null;
    }

    public void deleteById(Long id)  {

        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        for(Developer developer: targetCollection) {
            if(developer.getId().equals(id)){
                targetCollection.remove(developer);
                break;
            }
        }
        String in = new Gson().toJson(targetCollection);
        FileUtils.writeToFile(in, fileName);
    }

    //
    public Stream<Developer> collectionToStream(List<Developer> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }
    //
}
