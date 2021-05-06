package application;

//peer programmed by Daniel Yoon and David Kim
public class Employees extends DataAccess {
    public static void main(String[] args) {

    }
    public static void deleteEmployee(String id){
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\employees.csv";
        int fields = 5;
        deleteRecord(filepath, id, fields);
    }
    public static void newEmployee(String id, String fname, String lname, String pay, String position){
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\employees.csv";
        String[] input = {id, fname, lname, pay, position};
        saveRecord(input, filepath);
    }
    public static String check(String id, int columnSearch){ //column 0 = ID, 1=fname, 2 =lname, 3=pay,4=pos
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\employees.csv";
        int columnNum = 5;
        return query(filepath, id, columnSearch, columnNum);
    }
    public static void updateEmployee(String id, String fname, String lname, String pay, String position){//put -1 for fields that remain the same except for id
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\employees.csv";
        String[] newValue = new String[5];
        newValue[0] = id;
        newValue[1] = fname;
        newValue[2] = lname;
        newValue[3] = pay;
        newValue[4] = position;
        editRecord(filepath, newValue);
    }

}