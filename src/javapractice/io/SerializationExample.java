package javapractice.io;

import lombok.Builder;
import lombok.Data;

import java.io.*;


@Data
@Builder
class Child implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    private int myValue;

    @Override
    public String toString() {
        return "Child{" +
                "myValue=" + myValue +
                '}';
    }
}
@Data
@Builder
public class SerializationExample implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int value;

    private transient int transientValue;

    private Child child;

    private String name;

    @Override
    public String toString() {
        return "SerializationExample{" +
                "value=" + value +
                ", transientValue=" + transientValue +
                ", child=" + child +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializationExample obj = new SerializationExample(10, 1,
                new Child(123), "Rahul");

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("./src/source.txt")
                ))) {

            objectOutputStream.writeObject(obj);
        } catch ( Exception e) {
            e.printStackTrace();
        }

        SerializationExample obj2;

        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("./src/source.txt")
                )
        )){
            obj2 = (SerializationExample) objectInputStream.readObject();

            System.out.println(obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
