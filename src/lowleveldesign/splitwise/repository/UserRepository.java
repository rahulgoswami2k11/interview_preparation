package lowleveldesign.splitwise.repository;

import lombok.Builder;
import lombok.Data;
import lowleveldesign.splitwise.models.User;

import java.util.Map;

@Data
@Builder
public class UserRepository {
    private Map<String, User> userMap;
}
