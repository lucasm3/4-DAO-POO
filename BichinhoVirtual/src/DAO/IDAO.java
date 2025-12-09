package DAO;
import java.util.List;

public interface IDAO<T> {
    void create(T obj);
    T read(int id);
    java.util.List<T> readAll();
    void update(T obj);
    void delete(int id);
}
