package javapractice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract class SampleAbstractClass {
    protected int value;

    public SampleAbstractClass(int value) {
        this.value = value;
    }

    public void printValue() {
        System.out.println(value);
    }

    abstract void setValue(int value);

}


class ConcreteClass extends SampleAbstractClass {

    public ConcreteClass(int value) {
        super(value);
    }

    @Override
    void setValue(int value) {
        this.value = value;
    }
}

public class AbstractClassExample {
    public static void main(String[] args) {
        SampleAbstractClass obj = new ConcreteClass(5);
        obj.printValue();
        obj.setValue(10);
        obj.printValue();
    }
}
