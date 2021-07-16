package crud.example.json.springbootdemojson.controller;

import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.repository.DeveloperRepository;
import crud.example.json.springbootdemojson.repository.jsImpl.JsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    DeveloperRepository jsonDeveloperRepository = new JsonDeveloperRepositoryImpl();

    public List<Developer> getAll(){
        return jsonDeveloperRepository.getAll();
    }

    public Developer getById(Long id){
        return jsonDeveloperRepository.getById(id);
    }

    public void save(Developer developer){
        jsonDeveloperRepository.save(developer);
    }

    public void update(Developer developer){
        jsonDeveloperRepository.update(developer);
    }

    public void deleteById(Long id){
        jsonDeveloperRepository.deleteById(id);
    }

    public void printAll() {
        jsonDeveloperRepository.getAll().
                stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getFirstName() + " " + n.getLastName()));
    }
}
