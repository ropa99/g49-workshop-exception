package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
    /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     *
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames() {
        BufferedReader reader = null;
        List<String> names = null;

        try {
            reader = Files.newBufferedReader(Paths.get("firstname_males.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return names;
    }

    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     *
     * @return list of first name
     */
    public static List<String> getFemaleFirstNames() {
        List<String> names = null;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt"))) {
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return names;
    }

    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     *
     * @return List <String> of last names
     * @throws IOException if an error occurs while reading the file
     */
    public static List<String> getLastNames() throws IOException {
        List<String> names = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("lastnames.txt"))) {
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        }
        return names;
    }

    public static void saveLastNames(List<String> lastNames) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"))) {
            for (String toWrite : lastNames) {
                writer.append(toWrite).append(",");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveFemaleNames(List<String> femaleNames) {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"))) {
            for (String toWrite : femaleNames) {
                writer.append(toWrite).append(",");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveMaleNames(List<String> maleNames) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))) {
            for (String toWrite : maleNames) {
                writer.append(toWrite).append(",");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}