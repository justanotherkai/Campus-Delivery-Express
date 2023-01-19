/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AHMAD
 */
import utils.*;

import utils.*;

public class Services{
    public static String[] locations = 
        {"Shiny Car Wash","Barber Roger","Bob the cleaner","Bonda's Child Care","Excellence Chiropractic"};
    
    final static int MAX_ITEMS_PER_LOCATION = 5;
        
    public static double[] serviceFee ={
        3.50, 2, 3.50, 5, 6.50
    };

    public static int[] userOrderQuantity = new int[MAX_ITEMS_PER_LOCATION];

    public static String[][][] servicesItem = {// Elements: name, price
        {{"Car Wash","15"},{"Motorcycle Wash","8"},{"Large Vehicle Wash (lorry, tractor, etc)","35"}},
        {{"Adult","15"},{"Child","8"},{"Ladies","25"}},
        {{"Room Cleaning (<400sqft","10"},{"Full House Cleaning (<1200sqft)","25"},{"Full House Cleaning <2400sqft)","32"},{"Full House Cleaning (<5000sqft)","45"}},
        {{"2 Hours","15"},{"6 Hours","30"},{"12 Hours","50"}, {"24 Hours","90"}},
        {{"Consultation","15"},{"Full Body Massage","25"},{"Partial Massage(Back/Legs/Hand & Feet/...)","10"}},    
    };

    public static int chosenLocationIndex = 0;

    public static void mainloop(){
        Display.clearConsole();
        Display.appHeader();
        displayLocations(locations);
        chosenLocationIndex = Input.intInputPrompt("Location") -1;
        chosenLocationIndex = Math.abs(chosenLocationIndex%locations.length);// input will always be valid
        orderServices();
        Checkout.servicesCheckout(servicesItem[chosenLocationIndex]);
    }

    public static void orderServices(){
        while(true){
            Display.clearConsole();
            Display.appHeader();
            displayMenu(servicesItem[chosenLocationIndex]);
            displayCart();
            char input = Input.charInputPrompt("Which item would you like to add or remove? (Enter q if you are done)\n");
            if(input == 'q') break;
            if((input-'0') < 0 || (input-'0' > servicesItem[chosenLocationIndex].length))continue;// ignore invalid inputs
            addItemToUserOrder(input);
        }
    }

    public static void displayLocations(String[] locationNames){
        System.out.println("Locations: ");
        for(int i = 0; i < locationNames.length; i++){
            System.out.println((i+1) +". "+ locationNames[i]);
        }
    }

    public static void displayMenu(String[][] menuList){
        System.out.printf("%3s%-50s Price\n","","Name");
        for(int i = 0; i < menuList.length; i++){
                System.out.printf("%d. %-50s RM%s.00\n", i+1, menuList[i][0], menuList[i][1] );
        }
    }

    public static void displayCart(){
        System.out.println();
        System.out.println("Your orders: ");
        for(int i = 0; i < userOrderQuantity.length; i++){
            if(userOrderQuantity[i] != 0) System.out.println((i+1) + ". " + servicesItem[chosenLocationIndex][i][0] + " x " + userOrderQuantity[i]);
        }
        System.out.println();
    }

    public static void addItemToUserOrder(char input){
        int inputNum = input - '0';// convert char to int
        inputNum -= 1;// to compensate since index starts with zero, but input option starts with one
        userOrderQuantity[inputNum] += Input.intInputPrompt("How many " + servicesItem[chosenLocationIndex][inputNum][0] + " would you like to add? (Input negative number to remove item)\n");
        if(userOrderQuantity[inputNum] < 0) userOrderQuantity[inputNum] = 0;
    }
}

