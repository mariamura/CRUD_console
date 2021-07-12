package crud.example.json.springbootdemojson.model;

import java.util.List;

public class Team {

    private Long id;
    private String name;
    private List<Developer> developers;
    private TeamStatus teamStatus;

    public Team(Long id, String name, List<Developer> developers, TeamStatus teamStatus) {
        this.id = id;
        this.name = name;
        this.developers = developers;
        this.teamStatus = teamStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public TeamStatus getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developers=" + developers +
                ", teamStatus=" + teamStatus +
                '}';
    }
}
