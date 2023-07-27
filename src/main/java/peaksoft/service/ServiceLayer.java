package peaksoft.service;

import java.util.List;

public interface ServiceLayer <T>{

    T save(T t);

    T findById(int id);

    List<T> findAll();

    T update(int id, T t);

    void deleteById(int id);

}
