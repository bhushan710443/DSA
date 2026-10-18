package Problems.Streams;

public class Employees {


    private int id;
    private String name;
    private String address;
    private int age;
    private int salary;
    private String department;

    public int getId() {
        return id;
    }

    public Employees(int id, String name, String address, int age, int salary, String department) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.salary = salary;
        this.department = department;
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
    public String getDepart() {
        return department;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
