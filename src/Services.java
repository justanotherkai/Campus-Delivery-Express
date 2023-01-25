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

public class Services{
    public static String[] locations;
    public static Double[] serviceFee;
    public static String[][][] servicesItem;
    public static int[] userOrderQuantity;
    
    final static int MAX_ITEMS_PER_LOCATION = 5,MAX_LOCATION = 100;

    public static int chosenLocationIndex = 0;

    public static void mainloop(){
        // initialize locations[] serviceFee[] servicesItem[] and userOrderQuantity[] based on data files
        initArrays();

        Display.clearConsole();
        Display.appHeader();

        // let user choose a service location
        displayLocations(locations);
        chosenLocationIndex = Input.intInputPrompt("Location") -1;
        chosenLocationIndex = Math.abs(chosenLocationIndex%locations.length);// input will always be valid

        // loop that shows user the services option from chosen location and let them update their orders
        orderServices();

        // proceed to checkout if user is done
        Checkout.servicesCheckout(servicesItem[chosenLocationIndex]);
    }

    // initialize locations[] serviceFee[] servicesItem[] and userOrderQuantity[] based on csv files in .\data\ 
    public static void initArrays(){
        // temporary arrays with big lengths that will store data from csv files before transferring to the actual arrays
        String[] tempLocationList = new String[MAX_LOCATION];
        Double[] tempServiceFee = new Double[MAX_LOCATION];
        String[][][] tempFoodItem = new String[MAX_LOCATION][MAX_ITEMS_PER_LOCATION][3];
        int locationCount = 0;
        
        try{
            // get locations[] and serviceFee[] from serviceLocations.csv
            File locationList = new File(".\\data\\serviceLocations.csv");
            Scanner readLocations = new Scanner(locationList);
            while(readLocations.hasNextLine()){
                Scanner seperator = new Scanner(readLocations.nextLine());
                seperator.useDelimiter(",");
                while(seperator.hasNext()){
                    tempLocationList[locationCount] = seperator.next();
                    tempServiceFee[locationCount] = Double.parseDouble(seperator.next());
                    locationCount++;
                }
                seperator.close();
            }
            readLocations.close();
            
            servicesItem = new String[locationCount][][];

            // get servicesItem[] from serviceList.csv
            File servicesList = new File(".\\data\\serviceList.csv");
            Scanner readServices = new Scanner(servicesList);
            
            int locationIndex = 0;
            while(readServices.hasNextLine()){
                int  serviceIndex = 0, elementIndex = 0;
                Scanner seperator = new Scanner(readServices.nextLine());
                seperator.useDelimiter(",");
                while(seperator.hasNext()){
                    tempFoodItem[locationIndex][serviceIndex][elementIndex] = seperator.next();
                    serviceIndex += (++elementIndex/2);
                    elementIndex %= 2;
                }
                servicesItem[locationIndex] = new String[serviceIndex][2];
                for(int i = 0; i < servicesItem[locationIndex].length;i++){
                    System.arraycopy(tempFoodItem[locationIndex][i], 0, servicesItem[locationIndex][i], 0, servicesItem[locationIndex][i].length);
                }
                locationIndex++;
                seperator.close();
            }
            readServices.close();

            locations = new String[locationCount];
            serviceFee =  new Double[locationCount];
            System.arraycopy(tempLocationList,0,locations,0,locationCount);
            System.arraycopy(tempServiceFee,0,serviceFee,0,locationCount);

            userOrderQuantity = new int[locationCount];// set userOrderQuantity length based on number of locations

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
    }
    
    // show services options, user orders, then let them update their orders in a loop
    public static void orderServices(){
        while(true){
            Display.clearConsole();
            Display.appHeader();
            displayServices(servicesItem[chosenLocationIndex]);// display the service options based on chosen location
            displayCart();// display their carts underneath the options

            // let user add or remove order and quit once they are done, by inputting characters
            char input = Input.charInputPrompt("Which item would you like to add or remove? (Enter q if you are done)\n");
            if(input == 'q') break;
            if((input-'0') < 0 || (input-'0' > servicesItem[chosenLocationIndex].length))continue;// ignore invalid inputs
            addItemToUserOrder(input);
        }// the loop continues until user inputs 'q'
    }


    //display service locations list based on locations[] array
    public static void displayLocations(String[] locationNames){
        System.out.println("Locations: ");
        for(int i = 0; i < locationNames.length; i++){
            System.out.println((i+1) +". "+ locationNames[i]);
        }
    }

    //display service options based on servicesItem[chosenLocationIndex]
    public static void displayServices(String[][] serviceList){
        System.out.printf("%3s%-50s Price\n","","Name");
        for(int i = 0; i < serviceList.length; i++){
                System.out.printf("%d. %-50s RM%s.00\n", i+1, serviceList[i][0], serviceList[i][1] );
        }
    }

    // display user's orders
    public static void displayCart(){
        System.out.println();
        System.out.println("Your orders: ");
        for(int i = 0; i < userOrderQuantity.length; i++){
            if(userOrderQuantity[i] != 0) System.out.println((i+1) + ". " + servicesItem[chosenLocationIndex][i][0] + " x " + userOrderQuantity[i]);
        }
        System.out.println();
    }

    // let user add or remove items from their cart/order
    public static void addItemToUserOrder(char input){
        int inputNum = input - '0';// convert char to int
        inputNum -= 1;// to compensate since index starts with zero, but input option starts with one
        userOrderQuantity[inputNum] += Input.intInputPrompt("How many " + servicesItem[chosenLocationIndex][inputNum][0] + " would you like to add? (Input negative number to remove item)\n");
        if(userOrderQuantity[inputNum] < 0) userOrderQuantity[inputNum] = 0;
    }
}

