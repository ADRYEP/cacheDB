package Cache;

import Exceptions.DuplicatedKeyException;
import Exceptions.KeyNotFoundException;
import Structure.TreeMap;

import java.io.IOException;

public class Cache implements ICache{

    private String folderName = "cache";
    private TreeMap<String, String> cache;
    private FileHandler creator = new FileHandler();

    public Cache() throws IOException {
        if (!FileHandler.existFile(this.folderName)){
            FileHandler.createFolder(this.folderName);
            cache = new TreeMap<>();
        } else {
            this.cache = new TreeMap<>();
            String[] keys = FileHandler.readFolder(this.folderName);
            for(String key : keys) {
                String value = FileHandler.readFile(getFileName(key));
                this.cache.put(key, value);
            }
        }
    }

    public String[] getAll() {
        if (cache.isEmpty()) {
            return null;
        }
        Object[] keys = cache.keys();
        String[] output = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            output[i] = (String) keys[i];
        }
        return output;
    }

    @Override
    public String get(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        return cache.get(key);
    }

    @Override
    public String getOrDefault(String key, String defaultValue) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            return defaultValue;
        }
        return cache.get(key);
    }

    @Override
    public boolean exists(String key) {
        boolean cacheContains = cache.contains(key);
        boolean fileExists = FileHandler.existFile(getFileName(key).toString());

        if (cacheContains && fileExists) {
            System.out.println(key + " exists");
            return true;
        } else {
            System.out.println(key + " does not exist");
            return false;
        }
    }

    @Override
    public void put(String key, String value) throws IOException {
        cache.put(key, value);
        String fileName = getFileName(key).toString();

        if(FileHandler.existFile(fileName)){
            FileHandler.writeInFile(fileName, value);
        }
    }

    @Override
    public void addNew(String key, String value) throws IOException {
        if (cache.contains(key)) {
            throw new DuplicatedKeyException("Key already exists");
        }
        String fileName = getFileName(key).toString();
        cache.put(key, value);
        if(FileHandler.createFile(fileName)){
            FileHandler.writeInFile(fileName, value);
        }
    }

    @Override
    public boolean remove(String key) throws KeyNotFoundException {
        if (cache.contains(key) && creator.existFile(getFileName(key).toString())) {
            FileHandler.deleteFile(getFileName(key));
            return true;
        } else {
            System.out.println(key + " does not exist");
            return false;
        }
    }

    @Override
    public int size() {
        return cache.size();
    }
    private String getFileName(String file) {
        return this.folderName + "/" + file + ".txt";
    }
}


