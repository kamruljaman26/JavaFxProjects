package system.model.dao;

import java.util.List;

public interface Dao<T> {

    /**
     * find and return the object
     */
    T get(String id);

    /**
     * All objects as a list
     */
    List<T> getAll();

    /**
     * Store the object
     */
    boolean save(T t);

    /**
     * Update the object
     */
    boolean update(String key, T t);

    /**
     * Delete the object
     */
    boolean delete(T t);
}