package lowleveldesign.splitwise.repository;

import lombok.Builder;
import lombok.Data;
import lowleveldesign.splitwise.models.Group;

import java.util.Map;

@Data
@Builder
public class GroupRepository {
    private Map<String, Group> groupMap;
}
