package lowleveldesign.messagequeue.models;

import java.util.List;

public class Consumer {
    String id;
    ConsumerGroup consumerGroup;
    List<Partition> partitions;
}
