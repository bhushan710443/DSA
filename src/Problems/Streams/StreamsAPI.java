package Problems.Streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsAPI {

    public static void main(String[] args) {
        Employees employee1 = new Employees(1,"Bhushan", "Pune", 1, 40000, "Software Engineer");
        Employees employee2 = new Employees(2,"ABhushan", "APune", 2, 45000, "Manager");
        Employees employee3 = new Employees(3,"BBhushan", "BPune", 3, 100000, "IT guy");
        Employees employee4 = new Employees(4,"CBhushan", "CPune", 4, 110000, "Software Engineer 2");
        Employees employee5 = new Employees(5,"DBhushan", "DPune", 5, 120000, "Software Engineer 2");
        Employees employee6 = new Employees(6,"EBhushan", "EPune", 6, 130000, "Manager");
        Employees employee7 = new Employees(7,"FBhushan", "FPune", 7, 140000, "Manager");
        Employees employee8 = new Employees(8,"Bhushan", "GPune", 8, 150000, "Manager");
        Employees employee9 = new Employees(9,"HBhushan", "HPune", 9, 160000, "Manager");
        Employees employee10 = new Employees(10,"IBhushan", "IBPune", 10, 170000, "Manager");
        Employees employee11 = new Employees(11,"JBhushan", "JPune", 11, 108000, "Manager");
        Employees employee12 = new Employees(12,"KBhushan", "KPune", 12, 109000, "Manager");
        Employees employee13 = new Employees(13,"LBhushan", "LPune", 13, 106000, "Manager");
        Employees employee14 = new Employees(14,"MBhushan", "MPune", 14, 107000, "Manager");
        Employees employee15 = new Employees(15,"NBhushan", "NPune", 15, 104000, "Manager");
        Employees employee16 = new Employees(16,"OBhushan", "OPune", 16, 105000, "Manager");
        Employees employee17 = new Employees(17,"PBhushan", "PPune", 17, 106000, "Manager");
        Employees employee18 = new Employees(18,"QBhushan", "QPune", 18, 108000, "Manager");
        Employees employee19 = new Employees(19,"RBhushan", "RPune", 19, 101000, "Manager");
        Employees employee20 = new Employees(20,"SBhushan", "SPune", 20, 102000, "Manager");

        List<Employees> employees = List.of(employee1, employee2, employee3, employee4, employee5, employee6, employee7,
                employee8, employee9, employee10, employee11, employee12, employee13, employee14, employee15, employee16,
                employee17, employee18, employee19, employee20);

        findTheTopThreeHighestPaidEmployeesInEachDepartment(employees);
        System.out.println(findSecondHighestSalaryWithoutSorting(employees));
        System.out.println(findNthHighestDistinctSalary(employees, 4));
        System.out.println(findDuplicateEmployeeNames(employees));
        System.out.println(partitionEmployeesIntoTwoGroupsBasedOnWhetherTheirSalaryIsGreaterThan50k(employees));
        System.out.println(sortEmployeesBySalaryAndSalariesAreEqualSortByName(employees));
        System.out.println(convertLisTOfEmployeesIntoMapUsingEmployeeIDAsKey(employees));
        System.out.println(countTheNumberOfEmployeesInEachDepartment(employees));
        System.out.println(findTheLowestPaidEmployeeInEachDepartment(employees));
        System.out.println(calculateTheAverageSalaryForEachDepartment(employees));
        System.out.println(findTheSecondHighestPaidEmployeeInEachDepartment(employees));
        System.out.println(findTheDepartmentWithTheHighestNumberOfEmployees(employees));
        System.out.println(findEmployeesWhoseSalaryIsGreaterThanAverageSalary(employees));
    }

    //    Find the top three highest-paid employees in each department.
    public static void findTheTopThreeHighestPaidEmployeesInEachDepartment(List<Employees> emp) {

        Map<String, List<Employees>> map = emp.stream()
                .collect(Collectors.groupingBy(
                        Employees::getDepart,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Employees:: getSalary).reversed())
                                        .limit(3)
                                        .collect(Collectors.toList())
                        )
                ));

        map.forEach((department, employeeList) -> {
            System.out.println(department + ":");
            System.out.println(employeeList.toString() + ",,,,,,,");

        });

    }


//    Find the second-highest salary without sorting the entire list.
    public static Integer findSecondHighestSalaryWithoutSorting(List<Employees> emp) {

        Integer maxSal=emp.stream()
                .map(Employees::getSalary)
                .distinct()
                .max(Integer::compareTo)
                .orElse(0);
        return emp.stream()
                .map(Employees::getSalary)
                .filter(sal-> sal < maxSal)
                .max(Integer::compareTo)
                .orElse(0);
    }

//    Find the Nth-highest distinct salary.
    public static Integer findNthHighestDistinctSalary(List<Employees> emp, int n) {

        return  emp.stream()
                .map(Employees::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(n-1)
                .findFirst()
                .orElse(0);
    }

//    Find duplicate employee names.
    public static List<String> findDuplicateEmployeeNames(List<Employees> emp) {

       return emp.stream()
                .map(Employees::getName)
                .collect(Collectors.groupingBy(
                        name->name,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry-> entry.getValue()>1)
                .map(Map.Entry::getKey)
                .toList();

    }

//   Partition employees into two groups based on whether their salary is greater than ₹50,000.
    public static Map partitionEmployeesIntoTwoGroupsBasedOnWhetherTheirSalaryIsGreaterThan50k(List<Employees> emp) {

        return emp.stream()
                .collect(Collectors.partitioningBy(
                        employees->employees.getSalary()>50000
                ));

    }

//    Sort employees by salary, and if salaries are equal, sort by name.
    public static List<Employees> sortEmployeesBySalaryAndSalariesAreEqualSortByName(List<Employees> emp) {

       return emp.stream()
                .sorted(Comparator.comparing(Employees::getSalary)
                        .thenComparing(Employees::getName))
                .toList();
    }

//    Convert a list of employees into a Map using employee ID as the key.
    public static Map<Integer, Employees> convertLisTOfEmployeesIntoMapUsingEmployeeIDAsKey(List<Employees> emp) {

      return   emp.stream()
                .collect(Collectors.toMap(
                        Employees:: getId,
                        employees -> employees
                ));

    }

//    Count the number of employees in each department.
    public static List countTheNumberOfEmployeesInEachDepartment(List<Employees> emp) {

        return emp.stream()
                .collect(Collectors.groupingBy(
                        Employees::getDepart,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .toList();

    }


//    Find the lowest-paid employee in each department.
    public static Map findTheLowestPaidEmployeeInEachDepartment(List<Employees> emp) {
      return emp.stream()
                .collect(Collectors.groupingBy(
                        Employees::getDepart,
                        Collectors.minBy(
                                Comparator.comparingDouble(Employees::getSalary)
                        )
                ));
    }

//    Calculate the average salary for each department.
    public static Map calculateTheAverageSalaryForEachDepartment(List<Employees> emp) {

        return emp.stream()
                .collect(Collectors.groupingBy(
                        Employees::getDepart,
                        Collectors.averagingDouble(
                                Employees::getSalary
                        )));

    }

//    Find the second-highest-paid employee in each department
    public static Map findTheSecondHighestPaidEmployeeInEachDepartment(List<Employees> emp) {

        return emp.stream()
                .collect(Collectors.groupingBy(
                        Employees::getDepart,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list-> list.stream()
                                        .sorted(Comparator.comparing(Employees::getSalary).reversed())
                                        .skip(1)
                                        .findFirst()
                        )
                ));
    }

    // Find the department with the highest number of employees.
    public static Long findTheDepartmentWithTheHighestNumberOfEmployees(List<Employees> emp) {

       return emp.stream()
                .collect(Collectors.groupingBy(
                        Employees::getDepart,
                        Collectors.counting()
                ))
                 .entrySet()
                 .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
               .orElse(0L);
    }

//    Find employees whose salary is greater than the average salary.
    public static List<Employees> findEmployeesWhoseSalaryIsGreaterThanAverageSalary(List<Employees> emp) {

        Double avg= emp.stream()
                .mapToDouble(Employees::getSalary)
                .average()
                .orElse(0);

        System.out.println("Average = " + avg);

           Long intt= emp.stream()
                   .filter(employees -> employees.getSalary()>avg)
                   .collect(Collectors.counting());

        System.out.println("Total = " + intt);

        return emp.stream()
                .filter(employees -> employees.getSalary()>avg)
                .toList();
    }




}
