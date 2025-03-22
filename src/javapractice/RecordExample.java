package javapractice;


interface MYInterface {
    int value = 0;
}

record CarRecord(String name, String owner) {
    void printName() {
        System.out.println(name);
    }
    public CarRecord {
        if(this.name().length() < 4) {
            throw new RuntimeException("Short name length");
        }
    }
}

public class RecordExample {
    public static void main(String[] args) {
        CarRecord record = new CarRecord("Bullet", "Rahul");
        System.out.println(record);
        record.printName();
    }
}

