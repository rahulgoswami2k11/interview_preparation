package javapractice.threading;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
enum PrinterType {
    ODD,
    EVEN
}

@Getter
@Setter
@AllArgsConstructor
class State {
    private PrinterType currPrinterType;
    private PrinterType nextPrinterType;
}

@Getter
@Setter
class Printer implements Runnable {

    private final State state;

    private int maxValue;

    private int currValue;

    private PrinterType printerType;

    public Printer(State state, int startValue, int maxValue, PrinterType printerType) {
        this.state = state;
        this.maxValue = maxValue;
        this.currValue = startValue;
        this.printerType = printerType;
    }

    @Override
    public void run() {
        while(currValue <= maxValue) {
            synchronized (state) {
                while(state.getCurrPrinterType() != this.printerType) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(currValue);
                currValue += 2;
                PrinterType stateCurrPrinterType = state.getCurrPrinterType();
                state.setCurrPrinterType(state.getNextPrinterType());
                state.setNextPrinterType(stateCurrPrinterType);
                state.notifyAll();
            }
        }
    }
}

public class PrintEvenOddExample {
    public static void main(String[] args) {
        State state = new State(PrinterType.ODD, PrinterType.EVEN);
        Thread evenThread = new Thread(new Printer(state, 2, 20, PrinterType.EVEN));
        Thread oddThread = new Thread(new Printer(state, 1, 20, PrinterType.ODD));

        evenThread.start();
        oddThread.start();

    }
}
