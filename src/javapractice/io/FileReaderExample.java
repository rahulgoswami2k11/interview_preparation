package javapractice.io;

import java.io.*;

public class FileReaderExample {

    public static void main(String[] args) throws IOException {
        File file = new File("./src/source.txt");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String command;

            while((command = bufferedReader.readLine()) != null) {
                System.out.println(command);
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }

}
