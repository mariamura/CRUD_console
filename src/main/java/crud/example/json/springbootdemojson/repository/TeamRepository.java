package crud.example.json.springbootdemojson.repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Team;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TeamRepository {

    private static final String fileName = "teams.json";

    public Team getById(Long id) {
        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        List<Team> targetCollection = new Gson().fromJson(readFile(), targetClassType);
        return targetCollection
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Team> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        return new Gson().<ArrayList<Team>>fromJson(readFile(), targetClassType);
    }

    private Team save(Team team) {
        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        List<Team> targetCollection = new Gson().fromJson(readFile(), targetClassType);

        Team maxById = targetCollection
                .stream()
                .max(Comparator.comparing(Team::getId))
                .orElse(null);

        Long maxId = Objects.nonNull(maxById) ? maxById.getId() : 0L;
        team.setId(maxId+1);
        return team;
    }

    public Team update(Team team) {
        Team teamNew = new Team(
                team.getId(),
                team.getName(),
                team.getDevelopers());

        deleteById(team.getId());
        save(teamNew);
        return teamNew;
    }

    private void deleteById(Long id)  {

        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        List<Team> targetCollection = new Gson().fromJson(readFile(), targetClassType);

        for(Team team: targetCollection) {
            if(team.getId().equals(id)){
                targetCollection.remove(team);
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
        try(FileWriter fw = new FileWriter(TeamRepository.fileName)) {
            fw.write(in);
        }catch (IOException e) {
            System.out.println("Error while writing to the file: " + e);
        }
    }

    public static String getPath() {
        String path = "src\\main\\resources\\";
        File f = new File(path + "\\" + TeamRepository.fileName);
        return f.getAbsolutePath();
    }
}
