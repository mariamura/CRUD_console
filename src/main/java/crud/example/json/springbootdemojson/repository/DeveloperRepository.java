package crud.example.json.springbootdemojson.repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Developer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DeveloperRepository {

    public static final String fileName = "developers.json";

    public Developer getById(Long id) {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(readFile(), targetClassType);
        return targetCollection
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Developer> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        return new Gson().<ArrayList<Developer>>fromJson(readFile(), targetClassType);
    }

    private Developer save(Developer developer) {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(readFile(), targetClassType);

        Developer maxById = targetCollection
                .stream()
                .max(Comparator.comparing(Developer::getId))
                .orElse(null);

        Long maxId = Objects.nonNull(maxById) ? maxById.getId() : 0L;
        developer.setId(maxId+1);
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

    private void deleteById(Long id)  {

        Type targetClassType = new TypeToken<ArrayList<Developer>>() { }.getType();
        List<Developer> targetCollection = new Gson().fromJson(readFile(), targetClassType);

        for(Developer developer: targetCollection) {
            if(developer.getId().equals(id)){
                targetCollection.remove(developer);
                break;
            }
        }
        String in = new Gson().toJson(targetCollection);
        writeToFile(in);
    }

    private String readFile() {
        String fileContent = "";
        try(FileReader fr = new FileReader(getPath())){
            while(fr.ready()) {
                fileContent += (char)fr.read();
            }
        }catch (IOException e) {
            System.out.println("Error while file reading: " + e);
        }
        return fileContent;
    }

    private void writeToFile(String in) {
        try(FileWriter fw = new FileWriter(DeveloperRepository.fileName)) {
            fw.write(in);
        }catch (IOException e) {
            System.out.println("Error while writing to the file: " + e);
        }
    }

    public static String getPath() {
        String path = "src\\main\\resources\\";
        File f = new File(path + "\\" + DeveloperRepository.fileName);
        return f.getAbsolutePath();
    }
}
