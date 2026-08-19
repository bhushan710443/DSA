package Problems.Streams;

public class Employees {

    private String name;
    private String address;
    private int age;
    private int salary;
    private String designation;

    public Employees(String name, String address, int age, int salary, String designation) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getDesignation() {
        return designation;
    }
}
