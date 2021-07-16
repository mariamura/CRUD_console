package crud.example.json.springbootdemojson.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T save(T t);

    T getById(ID id);

    void deleteById(ID id);

    List<T> getAll();

    T update(T t);

    ID getLastId();

}
