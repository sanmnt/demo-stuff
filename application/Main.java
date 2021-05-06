package application;

//peer programmed by Daniel Yoon and David Kim
public class Main 
{
    public static void main(String args[])
    {
        //String[] testArrayEmp = {"1234", "John", "Wu", "$12", "Manager"};
        Employees myEmployees = new Employees();
        myEmployees.newEmployee("1234", "John", "Wu", "$12", "Manager");
        myEmployees.updateEmployee("1234", "-1", "-1", "$11", "Janitor");        
        myEmployees.newEmployee("1234", "Wang", "Wu", "$12", "Manager");

        Order order = new Order();
        order.newOrder("1234", "3", "jone's", "yes");

        Overhead overhead = new Overhead();
        overhead.newManufacturer("1234", "John", "Wu", "$12", "Manager");
        overhead.updateManufacturer("1234", "-1", "-1", "$11", "Janitor");        
        overhead.newManufacturer("1234", "Wang", "Wu", "$12", "Manager");
    }
}