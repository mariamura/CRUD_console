package crud.example.json.springbootdemojson.repository.JsonImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crud.example.json.springbootdemojson.model.Team;
import crud.example.json.springbootdemojson.repository.TeamRepository;

import java.lang.reflect.Type;
import java.util.List;

public class JsonTeamRepositoryImpl implements TeamRepository {

    private static final String filename = "teams.json";

    public JsonTeamRepositoryImpl() {
    }


    @Override
    public Team getById(Integer integer) {
        List<Team> listTeam = new Gson().fromJson(filename, (Type) Team.class);
        Team requireTeam = null;
        for(Team t: listTeam){
            if(t.getId()==integer) {
                requireTeam = t;
                break;
            }
        }
        return requireTeam;
    }

    @Override
    public void delete(Team item) {
        List<Team> listTeam = new Gson().fromJson(filename, (Type) Team.class);
        for(Team t: listTeam){
            if(t.equals(item)) {
                listTeam.remove(t.getId());
                break;
            }
        }
    }

    @Override
    public void update(Team item) {
        delete(getById(item.getId()));
        GsonBuilder gsonBuildr = new GsonBuilder();
    }

    @Override
    public void save(Team item) {

    }

    @Override
    public List<Team> getAll() {
        List<Team> listTeam = new Gson().fromJson(filename, (Type) Team.class);
        return listTeam;
    }
}
