package lowleveldesign.splitwise.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Group {
    String name;
    List<User> users;
}
