/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AHMAD
 */
import utils.*;

public class Checkout{

    public static void foodCheckout(String[][] FoodItemList){
        Display.clearConsole();
        Display.appHeader();
        Food.displayCart();
        checkFoodStock(Food.userOrderQuantity, FoodItemList);
        calcAndDispPrice(Food.userOrderQuantity, FoodItemList, Food.serviceFee[Food.locationChose]);
        updateStock(FoodItemList);
    }

    // public static void servicesCheckout(String[][] servicesList){
    //     while(true){
    //         Display.clearConsole();
    //         Display.appHeader();
    //         Services.displayCart(Services.userOrderQuantity);
    //         if(Input.charInputPrompt("Would you like to edit your orders? (y/n)") == 'y'){
    //             Services.orderServices();
    //             continue;
    //         }
    //     }
    //     calcAndDispPrice(Services.userOrderQuantity, servicesList, serviceFee);
    //     updateStock(servicesList);
    // }

    //this function checks if the stock of user's orders are available
    //prompt the user to update their cart and return false, if stock unavailable
    public static void checkFoodStock(int[] userOrderQuantity,String[][] itemList){
        while(true){
            if(Input.charInputPrompt("Would you like to edit your order? (y/n)") == 'y'){
                Food.orderFood();
                Display.clearConsole();
                Display.appHeader();
                Food.displayCart();
                continue;
            }
            //checks if stock is available, tell user then continue if there's issue
            //System.out.println("Stock is unavailable, please edit your order");
            //continue;
            break;
        }

    }

    //this function calculates the food price, service fee, and total price 
    //while also showing a preview of the receipt then prints it
    public static void calcAndDispPrice(int[] userOrderQuantity, String[][] itemList, double serviceFee){
        String[] receiptDetails = new String[10];
        System.out.println("Calculating and showing receipt preview");

        printReceipt(receiptDetails);// pass lines of receipt details which was printed already
    }

    //print receipt in a txt file
    public static void printReceipt(String[] receiptDetails){
        System.out.println("Receipt was printed");
    }

    //update stock in FoodItem
    public static void updateStock(String[][] itemList){
        System.out.println("Stock was updated");
        Input.charInputPrompt("");
    }
}