package Problems.Streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsAPI {

    public static void main(String[] args) {
        Employees employee1 = new Employees("Bhushan",  "Pune",  25, 80000,  "Software Engineer");
        Employees employee2 = new Employees("Abhushan", "APune", 28, 90000,  "Manager");
        Employees employee3 = new Employees("BBhushan", "BPune", 28, 100000, "IT guy");
        Employees employee4 = new Employees("CBhushan", "CPune", 28, 110000, "Software Engineer 2");
        Employees employee5 = new Employees("BBhushan", "BPune", 28, 100000, "Manager");
        List<Employees> employees = List.of(employee1, employee2, employee3, employee4, employee5);

        System.out.println(findMaxSalaryPerson(employees));
        System.out.println(findAge(employees).get());
        System.out.println(findAge(employees).get());
        System.out.println(findDesignetion(employees));
    }

    // find max salary of employee
    public static String findMaxSalaryPerson(List<Employees> employees){
          Employees employee = employees.stream()
                  .max(Comparator.comparing(Employees::getSalary))
                  .orElse(null);
        return employee.getName();
    }


    // find How many employees in the age group count most emp and return the age
    public static Optional<Integer> findAge(List<Employees> employees){
        Optional<Integer> age = employees.stream()
                .collect(Collectors.groupingBy(Employees::getAge, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey) ;
        return age;
    }

    // count employee with perticular designation
    public static String findDesignetion(List<Employees> employees) {

        Optional<String> name = employees.stream()
                .collect(Collectors.groupingBy(Employees::getDesignation, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey) ;
        return name.get();
    }


}
