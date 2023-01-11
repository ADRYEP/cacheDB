package app;

import Cache.Cache;
import Cache.ICache;
import Exceptions.DuplicatedKeyException;
import Exceptions.KeyNotFoundException;
import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "CacheDB command line application",
        version = "1.0",
        mixinStandardHelpOptions = true,
        description = "Command line application that allow us to use a Cache based DB"

)
public class CacheDB implements Callable<Integer> {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CacheDB()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Possible commands: getAll, get, add, put, remove, size, exists");
        return 0;
    }

    @CommandLine.Command(
            name = "getAll",
            description = "Get all keys"
    )
    public Integer getAll() throws Exception {
        Cache cache = new Cache();
        try {
            String[] keys = cache.getAll();
            for (String key : keys) {
                System.out.println(key);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @CommandLine.Command(
            name="get",
            description = "Get a value"
    )
    public Integer get(@CommandLine.Parameters(arity = "1", paramLabel = "Key",description = "The key") String key) throws IOException, KeyNotFoundException {
        Cache cache = new Cache();
        try {
            System.out.println(cache.get(key));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    @CommandLine.Command(
            name = "add",
            description = "Add the value associated to a key."
    )
    public Integer add(
            @CommandLine.Parameters(arity = "1", paramLabel = "Key", description = "Key to use in the command.") String key,
            @CommandLine.Parameters(arity = "1", paramLabel = "Value", description = "Value to use in the command.") String value) throws IOException, DuplicatedKeyException {
        Cache cache = new Cache();
        try {
            cache.addNew(key, value);
            System.out.println(value + " added to " + key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @CommandLine.Command(
            name = "put",
            description = "Updates the value associated to a key. Value must have apostrophes."
    )
    public Integer put(
            @CommandLine.Parameters(arity = "1", paramLabel = "Key", description = "Key to use in the command.") String key,
            @CommandLine.Parameters(arity = "1", paramLabel = "Value", description = "Value to use in the command.") String value) throws IOException {
        Cache cache = new Cache();
        try {
            cache.put(key, value);
            System.out.println(key + " updated with " + value);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @CommandLine.Command(
            name = "remove",
            description = "Delete a key"
    )
    public Integer remove(@CommandLine.Parameters(arity = "1", paramLabel = "Key", description = "Key to use in command") String key) throws IOException, KeyNotFoundException {
        Cache cache = new Cache();
        try {
            cache.remove(key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @CommandLine.Command(
            name = "size",
            description = "Get the size of the cache"
    )
    public Integer size() throws IOException {
        Cache cache = new Cache();
        try {
            System.out.println(cache.size());
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @CommandLine.Command(
            name="exists",
            description = "Check if a key exist"
    )
    public Integer exist(@CommandLine.Parameters(arity = "1", paramLabel = "Key",description = "The key") String key) throws IOException, KeyNotFoundException {
        Cache cache = new Cache();
        try {
            System.out.println(cache.exists(key));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

}
