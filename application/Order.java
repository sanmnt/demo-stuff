package application;//package application;
//peer programmed by Daniel Yoon and David Kim
import java.util.*;

//import org.graalvm.compiler.nodes.calc.IntegerConvertNode;

import java.io.*;
public class Order extends Shipping
{
    public static void main(String[] args) {
        updateOrder("201","20", "s1", "yes" );
    }
    public static void deleteOrder(String id){
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv";
        int fields = 9;
        deleteRecord(filepath, id, fields);
    }

    public static void newOrder(String id, String units, String shippingCompanyID, String payment) {
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv";
        String shippingCosts = (check(shippingCompanyID, 4)).trim();
        System.out.println(shippingCosts);
        double material = Integer.parseInt(units) * 4;
        double overhead = general.overheadCost() *.04;
        double labor = general.laborCost() * .04;
        System.out.println("test");
        double total = Double.parseDouble(shippingCosts) + material + overhead + labor;
        System.out.println("test2");
        String[] input = {id, units, shippingCompanyID, shippingCosts, String.valueOf(material), String.valueOf(overhead), String.valueOf(labor), String.valueOf(total), payment};
        System.out.println(input.length);
        addInventory(id, units);
        saveRecord(input, filepath);
    }

    //column 0 = ID, 1 = units, 2 = shippingCompanyID, 3 = shippingCosts
    public static String checkOrder(String id, int columnSearch) {
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv";
        int columnNum = 9;
        String returnString = query(filepath, id, columnSearch, columnNum);
        return returnString;
    }

    //put -1 for fields that remain the same except for id
    public static void updateOrder(String id, String units, String shippingCompanyID, String payment) {
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv";
        String[] newValue = new String[9];
        String shippingCosts = check(shippingCompanyID, 4);
        double material = Double.parseDouble(units) * 4;
        double overhead = general.overheadCost() *.04;
        double labor = general.laborCost() * .04;
        double total = Double.parseDouble(shippingCosts) + material + overhead + labor;
        String mcost = String.valueOf(material);
        String ocost = String.valueOf(overhead);
        String lcost = String.valueOf(labor);
        String tcost = String.valueOf(total);
//        if(shippingCompanyID.equals("-1")){
//            shippingCosts ="-1";
//        }
//        if(units.equals("-1")){
//            mcost = "-1";
//            ocost = "-1";
//            lcost = "-1";
//            tcost = "-1";
//        }
        newValue[0] = id;
        newValue[1] = units;
        newValue[2] = shippingCompanyID;
        newValue[3] = shippingCosts;
        newValue[4] = mcost;
        newValue[5] = ocost;
        newValue[6] = lcost;
        newValue[7] = tcost;
        newValue[8] = payment;
        //if(!units.equals("-1"))
        //inventoryUpdate(id, units);
        editRecord(filepath, newValue);
    }

    public static void addInventory(String id, String units) {
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\inventory.csv";
        String rawMaterial = Integer.toString((2* Integer.parseInt(units)));
        String[] input = {id, units, rawMaterial};
        saveRecord(input, filepath);
        inventoryChecker(rawMaterial);
    }

    public static void inventoryChecker(String rawM) {
        int intrawM = Integer.parseInt(rawM);
        String newFile = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\temp.txt";
        File filepath = new File("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\materialCost.csv");
        File tempFile = new File(newFile);
        try{
            Scanner scn = new Scanner(filepath);
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int matAmount = Integer.parseInt(scn.nextLine());
            int matCost = Integer.parseInt(scn.nextLine());
            
            if(matAmount < intrawM) {
                matCost += (intrawM *2);
            }
            else{
                matAmount = matAmount - intrawM;
            }

            if(matAmount < 100) {
                matAmount += 100;
                matCost += 200;
            }
            pw.println(matAmount);
            pw.println(matCost);
            scn.close();
            pw.flush();
            pw.close();
            filepath.delete();
            
            File dump = new File("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\materialCost.csv");
            tempFile.renameTo(dump); 
        }
        catch(Exception e) {
            System.out.println("caught an error1");
        }
    }

    public static void inventoryUpdate(String id, String orderAmount) {
        int oldOrderAmount = Integer.parseInt(query("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv", id, 1, 9));
        int deltaOrder = oldOrderAmount - Integer.parseInt(orderAmount);
        String newFile = "temp.txt";  
        File filepath = new File("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\materialCost.csv");
        File tempFile = new File(newFile);
        try{
            Scanner scn = new Scanner(filepath);
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int matAmount = Integer.parseInt(scn.nextLine());
            int matCost = Integer.parseInt(scn.nextLine());
            
            if(deltaOrder > 0){
                matAmount = matAmount + (deltaOrder * 2);
            }
            if(deltaOrder < 0) {
                matCost += -(4 * deltaOrder);
            }

            if(matAmount < 100) {
                matAmount += 100;
                matCost += 200;
            }

            pw.println(matAmount);
            pw.println(matCost);
            scn.close();
            pw.flush();
            pw.close();
            filepath.delete();
            
            File dump = new File("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\materialCost.csv");
            tempFile.renameTo(dump); 
        }
        catch(Exception e) {
            System.out.println("caught an error2");
        }
        String[] inputArray = {id, orderAmount, Integer.toString(oldOrderAmount*2)};
        editRecord("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\inventory.csv", inputArray);
    }
    
    public static String generateOrderNumber() {
        String newFile = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\temp.txt";
        File filepath = new File("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\Numbers.csv");
        File tempFile = new File(newFile);
        try{
            Scanner scn = new Scanner(filepath);
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int id = Integer.parseInt(scn.nextLine().trim());
            id +=1;
            pw.println(id);
            scn.close();
            pw.flush();
            pw.close();
            filepath.delete();
            File dump = new File("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\Numbers.csv");
            tempFile.renameTo(dump);
            System.out.println("ID IS" + id);
            cleanse("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\Numbers.csv", 1);
            return String.valueOf(id);

        }
        catch(Exception e) {
            System.out.println("caught an error3");
            return("error");
        }

    }
//    	String orderNum;
//    	int leftlimit = 48;
//    	int rightlimit = 57;
//    	int str_length = 7;
//
//    	Random random = new Random();
//
//    	orderNum = random.ints(leftlimit, rightlimit + 1)
//    			.limit(str_length)
//    			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//    		    .toString();
//
//    	return orderNum;
//    }
}