package crud.example.json.springbootdemojson.repository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import crud.example.json.springbootdemojson.model.Developer;
import crud.example.json.springbootdemojson.model.Skill;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {

    Developer save(Developer developer);

    Developer getById(Long id);

    void deleteById(Long id);

    List<Developer> getAll();

    Developer update(Developer developer);

    Long getLastId();

}
