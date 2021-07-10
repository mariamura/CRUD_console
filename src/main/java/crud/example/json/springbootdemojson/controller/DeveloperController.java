package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.model.Developer;

import java.util.List;

public class DeveloperController {
    DeveloperController developerController = new DeveloperController();

    public List<Developer> getAll(){
        return developerController.getAll();
    }

    public Developer getById(Long id){
        return developerController.getById(id);
    }

    public void save(Developer developer){
        developerController.save(developer);
    }

    public void update(Developer developer){
        developerController.update(developer);
    }

    public void deleteById(Long id){
        developerController.deleteById(id);
    }
}
