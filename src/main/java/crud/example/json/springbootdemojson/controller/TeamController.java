package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.model.Team;
import crud.example.json.springbootdemojson.repository.TeamRepository;
import crud.example.json.springbootdemojson.repository.jsImpl.JsonTeamRepositoryImpl;
import java.util.List;

public class TeamController {

    TeamRepository jsonTeamRepository = new JsonTeamRepositoryImpl();

    public List<Team> getAll(){
        return jsonTeamRepository.getAll();
    }

    public Team getById(Long id){
        return jsonTeamRepository.getById(id);
    }

    public void save(Team team){
        jsonTeamRepository.save(team);
    }

    public void update(Team team){
        jsonTeamRepository.update(team);
    }

    public void deleteById(Long id){
        jsonTeamRepository.deleteById(id);
    }

    public void printAll() {
        jsonTeamRepository.getAll().stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getName()));
    }

}
