package dao;

import java.util.List;

public interface EntityDAO<T> {
    void create(T t);
    void delete(T t);
    void update(T t);
    T find(int id);
    List<T> findAll();

}
