package com.ntahr.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

interface Condition {
    boolean test(Person person);
}

public class CommonUtil {


    public static void streams(List<Person> people) {
        Stream<Person> peopleStream = people.stream();
        peopleStream.filter(p -> p.getLastName().startsWith("Krishna"))
                .forEach(p -> System.out.println("First Name: " + p.getFirstName()));

    }

    public static void main(String... args) {
        List<Person> personList = Arrays.asList(
                new Person("Pravin", "Panicker", 39),
                new Person("Manju", "Panicker", 30),
                new Person("Tejas", "Krishna", 6),
                new Person("Neel", "Krishna", 1)
        );

        streams(personList);

        Collections.sort(personList, (person1, person2) -> person1.getFirstName().compareTo(person2.getFirstName()));
        // printAll(personList, p-> true, person -> System.out.println("Person: "+person));
        // printAll(personList, p-> p.getAge() <= 30, System.out::println); // p -> method(p) - replace by method reference

        // personList.forEach(System.out::println);

    }

    public static void printAll(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }
}

class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: " + lastName + ", Age: " + age;
    }
}