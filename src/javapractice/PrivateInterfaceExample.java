package javapractice;


// private static, public static, private, default, public
interface MyInterface {
    private void fun1() {

    }

    private static void fun2() {

    }

    static void fun3() {

    }

    void fun4();

    default void fun5() {

    }
}

public class PrivateInterfaceExample implements MyInterface {
    @Override
    public void fun4() {

    }

    @Override
    public void fun5() {
        MyInterface.super.fun5();
    }

    public static void main(String[] args) {
        MyInterface example = new PrivateInterfaceExample();
        example.fun5();
    }
}


