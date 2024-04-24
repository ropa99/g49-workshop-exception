package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.util.List;
import se.lexicon.exceptions.workshop.Exception.DuplicateNameException;
import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

    public static void main(String[] args) {
        try {
            List<String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();
            System.out.println(maleFirstNames);
            List<String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();

            List<String> lastNames = null;
            lastNames = CSVReader_Writer.getLastNames();

            NameService nameService = new NameService(maleFirstNames, femaleFirstNames, lastNames);

            Person test = nameService.getNewRandomPerson();
            System.out.println(test);

            System.out.println("------------------------------------");
            nameService.addFemaleFirstName("Jane");
            nameService.addMaleFirstName("John");
            nameService.addLastName("Doe");
        } catch (IOException | DuplicateNameException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}