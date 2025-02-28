/*

Assumptions:
    - Addresses aren't caps/puncuation sensitive:
        123 main st seattle wa == 123 Main St. Seattle WA
    - Data always follows format:
        First, Last, Addr, City, State, Age

*/

import java.io.*;
import java.util.*;

// Parses input and initializes objects
public class Main {
    public static void main(String[] args) {
        List<Person> people = parseInputData();
        List<Household> households = generateHouseholds(people);
        outputResults(people, households);
    }

    private static List<Person> parseInputData() {
        List<Person> people = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.substring(1, line.length() - 1);
                Person person = getPerson(trimmedLine);
                people.add(person);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }

        return people;
    }

    private static Person getPerson(String trimmedLine) {
        String[] parts = trimmedLine.split("\",\"");

        // Normalize the data
        String firstName = parts[0];
        String lastName = parts[1];
        String street = parts[2]
                .toUpperCase()
                .replaceAll("[.,]", "")
                .replaceAll("\\s+", " ")
                .trim();

        String city = parts[3].toUpperCase();
        String state = parts[4].toUpperCase();
        int age = Integer.parseInt(parts[5]);

        return new Person(firstName, lastName, street, city, state, age);
    }

    private static List<Household> generateHouseholds(List<Person> people) {
        Map<String, Household> households = new HashMap<>();

        for (Person person : people) {
            String fullAddress = person.getFullAddress();

            if (!households.containsKey(fullAddress)) {
                households.put(fullAddress, new Household(fullAddress));
            }

            households.get(fullAddress).addOccupant(person);
        }

        return households.values().stream().toList();
    }

    private static void outputResults(List<Person> people, List<Household> households) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {

            // Output 1
            writer.println("Each household and number of occupants:");
            households.forEach(household -> {
                writer.println(household.getFullAddress() + ": " + household.getOccupantCount());
            });
            writer.println();

            // Output 2
            writer.println("Occupants over 18 sorted by Last Name then First Name:");
            people.stream()
                    .filter(person -> person.getAge() > 18)
                    .sorted(Comparator.comparing(Person::getLastName)
                            .thenComparing(Person::getFirstName))
                    .forEach(writer::println);

        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}