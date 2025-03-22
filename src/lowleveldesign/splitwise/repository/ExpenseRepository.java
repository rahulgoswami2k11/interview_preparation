package lowleveldesign.splitwise.repository;

import lombok.Builder;
import lombok.Data;
import lowleveldesign.splitwise.models.Expense;
import lowleveldesign.splitwise.models.User;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ExpenseRepository {
    private Map<User, List<Expense>> userExpenseMap;
}
