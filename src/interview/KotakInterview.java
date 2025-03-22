package interview;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;


@Data
@AllArgsConstructor
class FileRecord {
    long timestamp;
    String pageId;
    String customerId;
}

public class KotakInterview {

    List<String> loyalCustomers2(List<FileRecord> day1, List<FileRecord> day2) {
        List<String> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i < day1.size() && j < day2.size()) {
            FileRecord record1 = day1.get(i);
            FileRecord record2 = day2.get(j);

            if (record1.customerId.equals(record2.customerId)) {
                if(!record1.pageId.equals(record2.pageId)) {
                    result.add(record1.customerId);
                    i++;
                    j++;
                } else {
                    while(i < day1.size() && j < day2.size() &&
                            Objects.equals(day1.get(i).customerId, day2.get(j).customerId) &&
                            Objects.equals(day1.get(i).pageId, day2.get(j).pageId)) {
                        i++;
                        j++;
                    }
                }
            } else {
                while(i < day1.size() && j < day2.size() &&
                        Objects.equals(day1.get(i).customerId, day2.get(j).customerId)) {
                    i++;
                    j++;
                }
            }
        }

        return result;
    }

    List<String> loyalCustomers(List<FileRecord> day1, List<FileRecord> day2) {
        Map<String, Set<String>> mapping = new HashMap<>();

        List<String> result = new ArrayList<>();
        for(FileRecord record: day1) {

            mapping.putIfAbsent(record.customerId, new HashSet<>());
            mapping.get(record.customerId).add(record.pageId);

        }

        for(FileRecord record: day2) {
            if(mapping.containsKey(record.customerId)) {
                Set<String> set = mapping.get(record.customerId);
                if(!set.contains(record.pageId) || set.size() > 1) {
                    result.add(record.customerId);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<FileRecord> day1 = List.of(
                new FileRecord(1L, "1", "1"),
                new FileRecord(1L, "2", "2")
        );

        List<FileRecord> day2 = List.of(
                new FileRecord(1L, "1", "1"),
                new FileRecord(1L, "2", "4")
        );


        KotakInterview interview = new KotakInterview();
        System.out.println(interview.loyalCustomers(day1, day2));
    }
}

