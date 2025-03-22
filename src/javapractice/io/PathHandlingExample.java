package javapractice.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PathHandlingExample {
    public static void main(String[] args) throws IOException {
        Path source = Path.of("./src/source.txt");

        List<String> list = new ArrayList<>();
        try(Stream<String> stream = Files.lines(source)) {
            stream.forEach(list::add);
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }

        System.out.println(list);
    }
}
