package StrreamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streams {

    static List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Employee ("Ahmed", "Taiwo", 5000.00, List.of("project 1", "project 2")));
        employeeList.add(new Employee ("Adelakun", "Abeni", 4500.00, List.of("project 3", "project 4")));
        employeeList.add(new Employee ("Igwe", "Chris", 3000.00, List.of("project 5", "project 6")));
    }


    public static void main(String[] args) {

        //Foreach
        employeeList.stream()
                .forEach(System.out::println);

        System.out.println("....................................................................");

        //Map
        List<Employee> incrementSalary = employeeList.stream()
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                ))
                .collect(Collectors.toList());
        System.out.println(incrementSalary);

        System.out.println("....................................................................");
        //filter
        List<Employee> filterSalary = employeeList.stream()
                .filter(employee -> employee.getSalary() < 5000)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                ))
                .collect(Collectors.toList());
        System.out.println(filterSalary);

        System.out.println("....................................................................");
        //filter + findAny orElse
        Employee firstEmployee = employeeList.stream()
                .filter(employee -> employee.getSalary() >= 5000)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                ))
                .findAny()
                .orElse(null);
        System.out.println(firstEmployee);

        System.out.println("....................................................................");

        //flatMap
        String allProjects = employeeList.stream()
                .map(Employee::getProjects)
                .flatMap(Collection::stream)
                .collect(Collectors.joining(","));
        System.out.println(allProjects);

        System.out.println("....................................................................");

        // short circuit
        List<Employee> shortCircuit = employeeList.stream()
                .skip(1)
                .limit(1)
                .collect(Collectors.toList());
        System.out.println(shortCircuit);

        System.out.println("....................................................................");

        // Finite Data
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("....................................................................");

        //sort
        List<Employee> compareTo = employeeList.stream()
                .sorted((o1, o2) -> o1.getSalary()
                        .compareTo(o2.getSalary()))
                .collect(Collectors.toList());
        System.out.println(compareTo);

        List<Employee> compareTo1 = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
        System.out.println(compareTo1);


        System.out.println("....................................................................");

        //max/min
        employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
                //.orElseThrow(NoSuchElementException::new);

        employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary))
               // .ifPresent(System.out::println)
                .orElseThrow(NoSuchElementException::new);

        System.out.println("....................................................................");

        //reduce
       Double salarySum = employeeList.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.println(salarySum);

        System.out.println("....................................................................");

        Map<String, List<Employee>> collectByGroup = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getFirstName));

        collectByGroup.forEach((s, employees) -> {
            System.out.println(s);
            employees.forEach(System.out::println);
        });


    }
}
