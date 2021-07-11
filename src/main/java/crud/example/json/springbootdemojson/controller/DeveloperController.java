package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.repository.DeveloperRepository;
import crud.example.json.springbootdemojson.view.DeveloperView;

import java.util.List;

public class DeveloperController {

    DeveloperRepository developerRepository = new DeveloperRepository();

    public List<Developer> getAll(){
        return developerRepository.getAll();
    }

    public Developer getById(Long id){
        return developerRepository.getById(id);
    }

    public void save(Developer developer){
        developerRepository.save(developer);
    }

    public void update(Developer developer){
        developerRepository.update(developer);
    }

    public void deleteById(Long id){
        developerRepository.deleteById(id);
    }
}
