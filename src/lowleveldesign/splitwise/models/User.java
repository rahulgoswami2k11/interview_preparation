package lowleveldesign.splitwise.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    String userId;
    String email;
}
