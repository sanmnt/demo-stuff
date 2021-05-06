package application;

//peer programmed by Daniel Yoon and David Kim
public class Overhead extends DataAccess
{
    public static void deleteManufacturer(String id){
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\manufacturer.csv";
        int fields = 5;
        deleteRecord(filepath, id, fields);
    }
    public static void newManufacturer(String manufacturer, String rent, String utilities, String insurance, String machinery) {
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\manufacturer.csv";
        String[] input = {manufacturer, rent, utilities, insurance, machinery};
        saveRecord(input, filepath);
    }

    //column 0 = manufacturer, 1 = rent, 2 = utilities, 3 = insurance, 4 = machinery
    public static String check(String manufacturer, int columnSearch) {
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\manufacturer.csv";
        int columnNum = 5;
        return query(filepath, manufacturer, columnSearch, columnNum);
    }

    //put -1 for fields that remain the same except for id
    public static void updateManufacturer(String manufacturer, String rent, String utilities, String insurance, String machinery){
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\manufacturer.csv";
        String[] newValue = new String[5];
        newValue[0] = manufacturer;
        newValue[1] = rent;
        newValue[2] = utilities;
        newValue[3] = insurance;
        newValue[4] = machinery;
        editRecord(filepath, newValue);
    }

}