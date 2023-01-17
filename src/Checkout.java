import utils.*;

public class Checkout{
    public static double serviceFee = 0;
    public static double foodPrice = 0;;

    public static void mainloop(){
        do{
            Display.clearConsole();
            Display.appHeader();
            Food.displayCart(Food.userOrderQuantity);
            if(Input.charInputPrompt("Would you like to edit your order? (y/n)") == 'y')
                Food.orderFood();
        }while(!checkFoodStock(Food.userOrderQuantity, Food.FoodItem[Food.locationChose]));//only break the loop if checkFoodStock return true (no stock issues)
        calculatePrice(Food.userOrderQuantity, Food.locationChose);
        printReceipt();
        updateStock(Food.FoodItem[Food.locationChose]);
    }

    //this function checks if the stock of user's orders are available
    //prompt the user to update their cart and return false, if stock unavailable
    public static boolean checkFoodStock(int[] userOrderQuantity,String[][] RestaurantFoodItem){

        return true;
    }

    //this function calculates the food price, service fee, and total price 
    //while also showing a preview of the receipt
    public static void calculatePrice(int[] userOrderQuantity, int locationChose){

    }

    //print receipt in a txt file
    public static void printReceipt(){
        
    }

    //update stock in FoodItem
    public static void updateStock(String[][] RestaurantFoodItem){

    }
}