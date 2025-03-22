package lowleveldesign.cachesystem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheEvent<Key, Value> {
    private CacheAction action;
    private Key key;
    private Value value;
}
