package lowleveldesign.cachesystem.cachestorage;

import lombok.Getter;
import lowleveldesign.cachesystem.CacheAction;
import lowleveldesign.cachesystem.CacheEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FileCacheStorage<Key, Value> implements CacheStorage<Key, Value> {

    private Map<Key, Value> cacheMap;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;


    public FileCacheStorage() {
        cacheMap = new HashMap<>();
        file = new File("./src/lowleveldesign/cachesystem/cachestorage/log.txt");
        try {
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Key key, Value value) {
        try {
            fileWriter.write("ADD " + key + " " + value);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cacheMap.put(key, value);
    }

    @Override
    public Value get(Key key) {
        Value value = cacheMap.getOrDefault(key, null);
        if(value != null) {
            try {
                fileWriter.write("GET " + key);
                fileWriter.write(System.lineSeparator());
                fileWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }

    @Override
    public void remove(Key key) {
        cacheMap.remove(key);
    }

    @Override
    public int currentCacheSize() {
        return cacheMap.size();
    }

    @Override
    public List<CacheEvent<Key, Value>> getEvents() {
        List<CacheEvent<Key, Value>> events = new ArrayList<>();
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        while(true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] parts = line.split(" ");
            CacheEvent<Key, Value> cacheEvent;
            if(parts.length < 3) {
                cacheEvent = new CacheEvent<>(CacheAction.valueOf(parts[0]), getKey(parts[1]), null);
            } else {
                cacheEvent = new CacheEvent<>(CacheAction.valueOf(parts[0]), getKey(parts[1]), getValue(parts[2]));
            }
            events.add(cacheEvent);
        }

        return events;
    }

    private Key getKey(String key) {
        return (Key) key;
    }

    private Value getValue(String value) {
        return (Value) value;
    }
}
