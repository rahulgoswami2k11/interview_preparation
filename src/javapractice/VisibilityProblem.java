package javapractice;

public class VisibilityProblem {
    private static boolean flag = true;

    private static synchronized boolean getFlag() {
        return flag;
    }

    private static synchronized void setFlag(boolean flag) {
        VisibilityProblem.flag = flag;
    }

    public static void main(String[] args) {
        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            setFlag(false); // Writer thread updates the flag

            System.out.println("Flag set to false by writer thread.");
        });

        Thread readerThread = new Thread(() -> {
            while (getFlag()) {
                // Reader thread may never see the updated value of flag
            }
            System.out.println("Flag is now false, reader thread exits.");
        });

        readerThread.start();
        writerThread.start();

    }
}