package DAO;


import java.util.ArrayList;
import java.util.List;
public class DAO<T> implements IDAO<T> {

    protected List<T> lista = new ArrayList<>();
    private java.util.function.Function<T, Integer> idExtractor;

    public DAO(java.util.function.Function<T, Integer> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void create(T obj) {
        lista.add(obj);
    }

    @Override
    public T read(int id) {
        for (T t : lista) {
            if (idExtractor.apply(t) == id) return t;
        }
        return null;
    }

    @Override
    public List<T> readAll() {
        return lista;
    }

    @Override
    public void update(T obj) {
        int id = idExtractor.apply(obj);
        delete(id);
        create(obj);
    }

    @Override
    public void delete(int id) {
        lista.removeIf(t -> idExtractor.apply(t) == id);
    }
}
