package Problems.List;
import java.util.List;

public class ListProblems {

    public static void main(String[] args) {
        List<Employee> emp = List.of(
                new Employee(1,"Bhushan"),
                new Employee(2,"ABhushan"),
                new Employee(3,"BBhushan"),
                new Employee(4,"CBhushan"),
                new Employee(5,"DBhushan"),
                new Employee(6,"EBhushan"),
                new Employee(7,"FBhushan"),
                new Employee(8,"GBhushan"),
                new Employee(9,"HBhushan"),
                new Employee(10,"IBhushan")
        );
        List<StocksPercent> stocks = List.of(
                new StocksPercent(10, 30),
                new StocksPercent(40, 20),
                new StocksPercent(50, 10)
        );

        System.out.println(findStocks(emp, stocks).toString());
    }

    private static List<Employee> findStocks(List<Employee> emps, List<StocksPercent> stocks ) {

        int numberOfEmployee=0;
        int initial=0;
        for(StocksPercent stock: stocks){
            numberOfEmployee =(int)(numberOfEmployee + (emps.size()*stock.getStockPercent())/100);
            for(int i=initial; i < numberOfEmployee; i++){
                Employee emp= emps.get(i);
                emp.setStocks(stock.getStocks());
            }
            initial = numberOfEmployee;
        }

        return emps ;
    }



















}
