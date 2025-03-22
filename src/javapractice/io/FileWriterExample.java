package javapractice.io;

import java.io.*;

public class FileWriterExample {
    public static void main(String[] args) throws IOException {
        File file = new File("./src/source.txt");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.append("GET key1");
            bufferedWriter.newLine();
            bufferedWriter.append("SET key2 value2");
            bufferedWriter.newLine();
            System.out.println("Data written successfully.");
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }
    }
}
