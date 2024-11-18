package UTP5.zad2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Futil {

    public static void processDir(String dirName, String resultFileName) {
        Charset inputCharset = Charset.forName("Cp1250");
        Charset outputCharset = Charset.forName("UTF-8");

        try {
            Stream<Path> paths = Files.walk(Paths.get(dirName));

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(resultFileName), outputCharset)) {
                paths.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".txt"))
                        .forEach(p -> {
                            try (Stream<String> lines = Files.lines(p, inputCharset)) {
                                lines.forEach(line -> {
                                    try {
                                        writer.write(line);
                                        writer.newLine();
                                    } catch (IOException e) {
                                        throw new UncheckedIOException(e);
                                    }
                                });
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
