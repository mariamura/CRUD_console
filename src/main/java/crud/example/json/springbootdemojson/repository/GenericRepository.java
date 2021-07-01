package crud.example.json.springbootdemojson.repository;


import java.util.List;

public interface GenericRepository<T, ID> {

    List<T> getAll();

    T getById(ID id);

    void delete(T item);

    void update(T item);

    void save(T item);

}
