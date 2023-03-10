/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AHMAD
 */
import utils.*;
import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class


public class Checkout{
    final static int MAX_RECEIPT_LENGTH = 13;

    //checkout method after exiting Food.mainloop
    public static void foodCheckout(String[][] FoodItemList){
        Display.clearConsole();
        Display.appHeader();
        Food.displayCart();

        // check stocks, allow user to proceed if everything is fine
        checkFoodStock(Food.userOrderQuantity, FoodItemList);

        // calculate prices and display to user, then put arrays of the informations inside receiptDetails
        String[] receiptDetails = calcAndDispPrice(Food.userOrderQuantity, FoodItemList, Food.serviceFee[Food.chosenRestaurantIndex]);

        // print receipt to a txt file in .\receipts
        printReceipt(receiptDetails, Food.restaurants[Food.chosenRestaurantIndex]);

        updateStock(Food.userOrderQuantity, Food.chosenRestaurantIndex);
    }

    //checkout method after exiting Services.mainloop
    public static void servicesCheckout(String[][] servicesList){
         while(true){// display user's cart then prompt them if they want to update it
             Display.clearConsole();
             Display.appHeader();
             Services.displayCart();
             if(Input.charInputPrompt("Would you like to edit your orders? (y/n)") == 'y')
                 Services.orderServices();
             else
                 break;// break the loop if user doesn't input 'y'
         }

        // calculate prices, display to user, then put arrays of the infos inside receiptDetails
        String[] receiptDetails = calcAndDispPrice(Services.userOrderQuantity, servicesList, Services.serviceFee[Services.chosenLocationIndex]);

        // print receipts to a txt file in .\receipts
        printReceipt(receiptDetails,Services.locations[Services.chosenLocationIndex]);
     }

    //this function checks if the stock of user's orders are available
    //prompt the user to update their cart and return false, if stock unavailable
    public static void checkFoodStock(int[] userOrderQuantity,String[][] itemList){
        boolean stockAvailable = true;
        do{
            if(Input.charInputPrompt("Would you like to edit your order? (y/n)") == 'y'){
                Food.orderFood();
                Display.clearConsole();
                Display.appHeader();
                Food.displayCart();
                continue;
            }
            //checks if stock is available, tell user then continue if there's issue
            stockAvailable = true;
            for(int i = 0; i < itemList.length; i++){
                if(userOrderQuantity[i] > Integer.parseInt(itemList[i][2])){
                    System.out.println("Sorry, " + itemList[i][0] + " is out of stock. Please update your order");
                    stockAvailable = false;
                }
            }
        }while(!stockAvailable);
    }

    //this function calculates the food price, service fee, and total price 
    //while also showing a preview of the receipt and return the details
    public static String[] calcAndDispPrice(int[] userOrderQuantity, String[][] itemList, double serviceFee){
        String[] receiptDetails = new String[MAX_RECEIPT_LENGTH];
        double totalCost = 0;
        int receiptLine = 0;
        Display.clearConsole();
        Display.appHeader();
        for(int i = 0; i < itemList.length; i++){
            if(userOrderQuantity[i] != 0){
                double price = userOrderQuantity[i]*Double.parseDouble(itemList[i][1]);
                totalCost += price;
                String calculations = "- " + itemList[i][0] + " x " + userOrderQuantity[i] + " : RM" + String.format("%.2f", price);
                System.out.println(calculations);
                receiptDetails[receiptLine] = calculations;
                receiptLine++;
            }
        }
        totalCost += serviceFee;
        String serviceFeeAmt = "\nService fee : RM" + String.format("%.2f", serviceFee);
        System.out.println(serviceFeeAmt);
        receiptDetails[MAX_RECEIPT_LENGTH - 2] = serviceFeeAmt;

        String totalCostAmt = "Total : RM" + String.format("%.2f", totalCost);
        System.out.println(totalCostAmt);
        Input.charInputPrompt("Input anything to go back to main menu");
        receiptDetails[MAX_RECEIPT_LENGTH - 1] = totalCostAmt;
        return receiptDetails;
    }

    // print receipt based on receiptDetails[] to a txt file in .\receipts 
    public static void printReceipt(String[] receiptDetails, String location){
        String straightLine = "\n---------------------------------------------\n";
        //create a string for date and time with a certain format using LocalDateTIme and DateTimeFormatter objects
        LocalDateTime dateObj = LocalDateTime.now();
        String dateAndTimeNow = dateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String dateAndTimeNowReceipt = dateObj.format(DateTimeFormatter.ofPattern("_dd-MM-yy_HH-mm-ss"));
        try{
            File receiptFile = new File("./receipts/Receipt" + dateAndTimeNowReceipt + ".txt");
            PrintWriter receiptWriter = new PrintWriter(receiptFile);
            receiptWriter.write(straightLine + "Campus Delivery Express (IIUM GOMBAK)\nLocation : " + location + straightLine);
            for(int i = 0; i < MAX_RECEIPT_LENGTH; i++){
                receiptWriter.write((receiptDetails[i] == null ? " " : receiptDetails[i]) + "\n");
                receiptWriter.flush();
            }
            receiptWriter.write(straightLine + "Transaction timestamp : " + dateAndTimeNow + straightLine);
            receiptWriter.close();
        }catch(Exception ex){
            Display.exceptionOccuredMessage();
        }
    }

    //update stock in restaurantMeals.csv
    public static void updateStock(int[] userOrderQuantity, int chosenRestaurantIndex){
        try{
            File restaurantMeals = new File(".\\data\\restaurantMeals.csv");
            File tempFile = new File(".\\data\\temp");
            Scanner duplicate = new Scanner(restaurantMeals);
            PrintWriter update = new PrintWriter(tempFile);
            
            int line = 0, column = 1;
            while(duplicate.hasNextLine()){
                if(line == chosenRestaurantIndex){
                    Scanner seperator = new Scanner(duplicate.nextLine());
                    seperator.useDelimiter(",");
                    String temp = "";
                    while(seperator.hasNext()){
                        if(column%3 == 0) temp += Integer.parseInt(seperator.next()) - userOrderQuantity[column / 3 - 1] + ",";
                        else temp += seperator.next() + ",";
                        column++;
                    }
                    update.println(temp);
                }else{
                    update.println(duplicate.nextLine());
                }
                line++;
            }
            duplicate.close();
            update.close();
            if(!restaurantMeals.delete()) System.out.println("Unable to delete restaurantMeals.csv");
            if(!tempFile.renameTo(restaurantMeals)) System.out.println("Unable to rename tempFile to restaurantMeals.csv");

        }catch(FileNotFoundException ex){
            System.out.println("Stock update failed: restaurantMeals.csv was not found");
        }
    }
}
