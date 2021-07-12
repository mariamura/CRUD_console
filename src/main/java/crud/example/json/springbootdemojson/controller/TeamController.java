package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.model.Team;
import crud.example.json.springbootdemojson.repository.TeamRepository;

import java.util.List;

public class TeamController {

    TeamRepository teamRepository = new TeamRepository();


    public List<Team> getAll(){
        return teamRepository.getAll();
    }

    public Team getById(Long id){
        return teamRepository.getById(id);
    }

    public void save(Team team){
        teamRepository.save(team);
    }

    public void update(Team team){
        teamRepository.update(team);
    }

    public void deleteById(Long id){
        teamRepository.deleteById(id);
    }

}
