package crud.example.json.springbootdemojson.repository;

import crud.example.json.springbootdemojson.model.Team;
import java.util.*;

public interface TeamRepository extends GenericRepository<Team, Long> {

    Team save(Team team);

    Team getById(Long id);

    void deleteById(Long id);

    List<Team> getAll();

    Team update(Team team);

    Long getLastId();
}
