package com.pluralsight.streams;

import com.pluralsight.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {
    // goes from 118 lines to 56
    public static void main(String[] args) {
        List<Person> people = createPeopleList();

        System.out.print("Enter a name to search: ");
        Scanner scanner = new Scanner(System.in);
        String searchName = scanner.nextLine();

        List<Person> matchingPeople = people.stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(searchName)
                        || p.getFirstName().equalsIgnoreCase(searchName))
                .toList();

        System.out.println("People with matching name:");
        matchingPeople.forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName()));

        double averageAge = people.stream().map(Person::getAge).reduce(0, Integer::sum) / (double) people.size();
        System.out.printf("Average age: %.1f%n", averageAge);

//        List<Integer> sortedAges = people.stream().map(Person::getAge).sorted().toList();
        List<Person> sortedAges = people.stream().sorted(Comparator.comparing(Person::getAge)).toList();

        int maxIndex = sortedAges.size() - 1;
        System.out.printf("Oldest person's age: %s %s %d%n", sortedAges.get(maxIndex).getFirstName(), sortedAges.get(maxIndex).getLastName(), sortedAges.get(maxIndex).getAge());
        System.out.printf("Youngest person's age: %s %s %d%n", sortedAges.get(0).getFirstName(), sortedAges.get(0).getLastName(), sortedAges.get(0).getAge());
    }


    // Builds the test data in one place, so main stays short
    private static List<Person> createPeopleList() {
        List<Person> people = new ArrayList<>();
        // Add at least 10 people to the list
        people.add(new Person("John", "Doe", 25));
        people.add(new Person("Jane", "Smith", 30));
        people.add(new Person("Michael", "Johnson", 35));
        people.add(new Person("Taylor", "Brown", 22));
        people.add(new Person("David", "Miller", 40));
        people.add(new Person("Sarah", "Clark", 28));
        people.add(new Person("Victor", "Anderson", 33));
        people.add(new Person("Jessica", "Wilson", 27));
        people.add(new Person("Matthew", "Martinez", 31));
        people.add(new Person("Olivia", "Taylor", 29));

        return people;
    }
}
