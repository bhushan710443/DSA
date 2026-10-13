package Problems.ProductBaseProbs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsAPI {

    public static void main(String[] args) {

        List<Employee> emp = List.of(new Employee(1,"Bhushan", 50000, "Engineering"),
                                     new Employee(2,"Jenil", 60000, "IT"),
                                     new Employee(3,"Mitul", 70000, "Computer"),
                                     new Employee(4,"Soniya", 80000, "Computer"));

         System.out.println(getSecondHeighstSal(emp).getName());
         System.out.println(getSecondHeighstSalary(emp));
         System.out.println(findFirstNonRepeatingChar("AVCDAVDM"));
         findFrequencyOfEachChar("AVCDAVDM");

         System.out.println(findHighestPaidEmployeeInEachDepartment(emp).toString());

    }


    public static Employee getSecondHeighstSal(List<Employee> emp){

        Employee employee= emp.stream()
                .distinct()
                .sorted(Comparator.comparing(Employee::getSal))
                .skip(1)
                .findFirst()
                .orElse(null);

        return employee;
    }

    public static double getSecondHeighstSalary(List<Employee> emp){

        Double sal= emp.stream()
                .map(Employee::getSal)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
        return sal;
    }

    public static char findFirstNonRepeatingChar(String str){

        Character res = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        return res;
    }

    public static void findFrequencyOfEachChar(String str){

        str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .forEach((character, frequency) -> 
                    System.out.println(character + " : " + frequency)
                );
    }


    public static Map<String, Employee> findHighestPaidEmployeeInEachDepartment(List<Employee> emp){

             Map<String, Employee> res =  emp.stream()
                       .collect(Collectors.groupingBy(
                               Employee::getDept,
                               Collectors.collectingAndThen(
                                       Collectors.maxBy(Comparator.comparing(Employee::getSal)
                                       ), Optional::get
                               )
                       ));

             return res;


    }







}


class Employee {

    int id;
    String  name;
    double sal;
    String dept;

    public Employee(int id, String name, double sal, String dept) {this.id = id;this.name = name;this.sal = sal;this.dept = dept;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setSal(double sal) {this.sal = sal;}
    public void setDept(String dept) {this.dept = dept;}
    public int getId() {return id;}
    public String getName() {return name;}
    public String getDept() {return dept;}
    public double getSal() {return sal;}

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sal=" + sal +
                ", dept='" + dept + '\'' +
                '}';
    }
}