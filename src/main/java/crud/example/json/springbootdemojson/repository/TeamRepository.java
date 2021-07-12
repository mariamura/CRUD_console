package crud.example.json.springbootdemojson.repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Team;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TeamRepository {

    public static final String fileName = "teams.json";

    public Team getById(Long id) {
        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        List<Team> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);
        return targetCollection
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Team> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        return new Gson().<ArrayList<Team>>fromJson(FileUtils.readFile(fileName), targetClassType);
    }

    public Team save(Team team) {
        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        List<Team> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        Team maxById = targetCollection
                .stream()
                .max(Comparator.comparing(Team::getId))
                .orElse(null);

        Long maxId = Objects.nonNull(maxById) ? maxById.getId() : 0L;
        team.setId(maxId+1);
        targetCollection.add(team);
        FileUtils.writeToFile(new Gson().toJson(targetCollection), fileName);
        return team;
    }

    public Team update(Team team) {
        Team teamNew = new Team(
                team.getId(),
                team.getName(),
                team.getDevelopers(),
                team.getTeamStatus());
        deleteById(team.getId());
        save(teamNew);
        return teamNew;
    }

    public void deleteById(Long id)  {

        Type targetClassType = new TypeToken<ArrayList<Team>>() { }.getType();
        List<Team> targetCollection = new Gson().fromJson(FileUtils.readFile(fileName), targetClassType);

        for(Team team: targetCollection) {
            if(team.getId().equals(id)){
                targetCollection.remove(team);
                break;
            }
        }
        String in = new Gson().toJson(targetCollection);
        FileUtils.writeToFile(in, fileName);
    }
}
