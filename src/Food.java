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
import java.util.*;

public class Food{
    //
    public static String[] restaurants;
    public static Double[] serviceFee;
    public static String[][][] foodItem;
    public static int[] userOrderQuantity;
    
    final static int MAX_RESTAURANT = 100, MAX_ITEMS_PER_RESTAURANT = 20;

    public static int chosenRestaurantIndex = 0;

    public static void mainloop(){
        initArrays();
        System.out.println(serviceFee[1]);
        Display.clearConsole();
        Display.appHeader();
        displayRestaurants(restaurants);
        chosenRestaurantIndex = Input.intInputPrompt("Location") -1;
        chosenRestaurantIndex = Math.abs(chosenRestaurantIndex%restaurants.length);// input will always be valid
        orderFood();
        Checkout.foodCheckout(foodItem[chosenRestaurantIndex]);
    }

    public static void initArrays(){
        String[] tempRestaurantList = new String[MAX_RESTAURANT];
        Double[] tempServiceFee = new Double[MAX_RESTAURANT];
        String[][][] tempFoodItem = new String[MAX_RESTAURANT][MAX_ITEMS_PER_RESTAURANT][3];
        int restaurantCount = 0;
        
        try{
            File restaurantList = new File(".\\data\\restaurants.csv");
            Scanner readRestaurants = new Scanner(restaurantList);
            while(readRestaurants.hasNextLine()){
                Scanner seperator = new Scanner(readRestaurants.nextLine());
                seperator.useDelimiter(",");
                while(seperator.hasNext()){
                    tempRestaurantList[restaurantCount] = seperator.next();
                    tempServiceFee[restaurantCount] = Double.parseDouble(seperator.next());
                    restaurantCount++;
                }
                seperator.close();
            }
            readRestaurants.close();

            foodItem = new String[restaurantCount][][];

            File restaurantMealsList = new File(".\\data\\restaurantMeals.csv");
            Scanner readMeals = new Scanner(restaurantMealsList);
            
            int restaurantIndex = 0;
            while(readMeals.hasNextLine()){
                int  mealIndex = 0, elementIndex = 0;
                Scanner seperator = new Scanner(readMeals.nextLine());
                seperator.useDelimiter(",");
                while(seperator.hasNext()){
                    tempFoodItem[restaurantIndex][mealIndex][elementIndex] = seperator.next();
                    mealIndex += (++elementIndex/3);
                    elementIndex %= 3;
                }
                foodItem[restaurantIndex] = new String[mealIndex][3];
                for(int i = 0; i < foodItem[restaurantIndex].length;i++){
                    System.arraycopy(tempFoodItem[restaurantIndex][i], 0, foodItem[restaurantIndex][i], 0, foodItem[restaurantIndex][i].length);
                }
                restaurantIndex++;
                seperator.close();
            }
            readMeals.close();

            restaurants = new String[restaurantCount];
            serviceFee =  new Double[restaurantCount];
            System.arraycopy(tempRestaurantList,0,restaurants,0,restaurantCount);
            System.arraycopy(tempServiceFee,0,serviceFee,0,restaurantCount);
            userOrderQuantity = new int[restaurantCount];

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }

    public static void orderFood(){
        while(true){
            Display.clearConsole();
            Display.appHeader();
            displayMenu(foodItem[chosenRestaurantIndex]);
            displayCart();
            char input = Input.charInputPrompt("Which item would you like to add or remove? (Enter q if you are done)\n");
            if(input == 'q') break;
            if((input-'0') <= 0 || (input-'0' > foodItem[chosenRestaurantIndex].length))continue;// ignore invalid inputs
            addItemToUserOrder(input);
            
        }
    }

    public static void displayRestaurants(String[] restaurantNames){
        System.out.println("Restaurants: ");
        for(int i = 0; i < restaurantNames.length; i++){
            System.out.println((i+1) +". "+ restaurantNames[i]);
        }
    }

    public static void displayMenu(String[][] menuList){
        System.out.printf("%3s%-50s Price\n","","Name");
        for(int i = 0; i < menuList.length; i++){
                if(menuList[i][0] != null)System.out.printf("%d. %-50s RM%s.00\n", i+1, menuList[i][0], menuList[i][1] );
        }
    }

    public static void displayCart(){
        System.out.println();
        System.out.println("Your orders:");

        for(int i = 0; i < foodItem[chosenRestaurantIndex].length; i++){
            if(userOrderQuantity[i] != 0) System.out.println((i+1) + ". " + foodItem[chosenRestaurantIndex][i][0] + " x " + userOrderQuantity[i]);
        }
        System.out.println();
    }

    public static void addItemToUserOrder(char input){
        int inputNum = input - '0';// convert char to int
        inputNum -= 1;// to compensate since index starts with zero, but input option starts with one
        userOrderQuantity[inputNum] += Input.intInputPrompt("How many " + foodItem[chosenRestaurantIndex][inputNum][0] + " would you like to add? (Input negative number to remove item)\n");
        if(userOrderQuantity[inputNum] < 0) userOrderQuantity[inputNum] = 0;
    }
    
}
