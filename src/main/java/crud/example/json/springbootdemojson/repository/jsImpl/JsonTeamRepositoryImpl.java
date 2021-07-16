package crud.example.json.springbootdemojson.repository.jsImpl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Team;
import crud.example.json.springbootdemojson.repository.FileUtils;
import crud.example.json.springbootdemojson.repository.TeamRepository;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

public class JsonTeamRepositoryImpl implements TeamRepository {

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

        Team maxById = collectionToStream(targetCollection)
                .max(Comparator.comparing(Team::getId))
                .orElse(null);

        Long maxId = Objects.nonNull(maxById) ?maxById.getId() : 0L;
        team.setId(maxId+1);


        List<Team> newTargetCollections = new ArrayList<>(Optional.ofNullable(targetCollection).orElse(Collections.emptyList()));;
        newTargetCollections.add(team);
        FileUtils.writeToFile(new Gson().toJson(newTargetCollections), fileName);

        return team;
    }

    //
    public Stream<Team> collectionToStream(List<Team> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }
    //

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

    public Long getLastId(){
        return 1L;
    }
}
