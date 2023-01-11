package Cache;

import Exceptions.DuplicatedKeyException;
import Exceptions.KeyNotFoundException;

import java.io.IOException;

public interface ICache {
    String[] getAll();
    /**
     * Get the value associated with the key passed as argument.
     * @param key Key to look for
     * @return The value associated with the key
     * @throws KeyNotFoundException if key does not exist.
     */
    String get(String key) throws KeyNotFoundException;
    /**
     * Return the value of key passed as argument. Otherwise, return the
     * default value passed as second argument.
     * @param key Key to look for
     * @param defaultValue Value returned when key does not exist.
     * @return The value associated with the key or the defaultValue if key was not
    found.
     */
    String getOrDefault(String key, String defaultValue) throws KeyNotFoundException;
    /**
     * Check is a key exists in cache.
     * @param key Key to look for
     * @return True if key exists.
     */
    boolean exists(String key);
    /**
     * Add or update the value associated to a key.
     * @param key Key to be stored.
     * @param value Value to be stored.
     */
    void put(String key, String value) throws IOException;
    /**
     * Add a value to a new key. If key already exists, it throws an exception.
     * @param key Key to be stored.
     * @param value Value to be stored.
     * @throws DuplicatedKeyException the key already exists.
     */
    void addNew(String key, String value) throws IOException;
    /**
     * Remove a key and its value.
     *
     * @param key Key to be stored.
     * @return
     * @throws KeyNotFoundException if key does not exist.
     */
    boolean remove(String key) throws KeyNotFoundException;
    /**
     * Count the keys (and values) stored in cache.
     * @return Count of keys.
     */
    int size();
}
