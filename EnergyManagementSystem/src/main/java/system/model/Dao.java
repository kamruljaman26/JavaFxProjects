package system.model;

import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    /**
     * find and return the object
     *
     * @param id object id
     * @return if found return object, otherwise null
     */
    T get(String id);

    /**
     * @return all objects as a list
     */
    List<T> getAll();

    /**
     * Store the object
     *
     * @param t
     * @return
     */
    boolean save(T t);

    /**
     * Update the object
     *
     * @param key
     * @param t
     * @return
     */
    boolean update(String key, T t);

    /**
     * Delete the object
     *
     * @param t
     * @return
     */
    boolean delete(T t);
}