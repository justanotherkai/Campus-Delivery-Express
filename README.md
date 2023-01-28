# Campus-Delivery-Express

## How to run:
0. Download the zip file (then extract) or clone the repo
1. Open the windows command prompt
2. Navigate to the downloaded zip file (> cd "insert-file-location")
3. Make sure you've opened the directory with src, data, receipts, etc files (> ls) to check
4. Type in this command > java -jar .\dist\CampusDeliveryExpress.jar
5. The program should be running, maximize the cmd window for best experience

## Classes and methods

public class Display:
>	public static void clearConsole() 
>> a function to clear the console
>	public static void invalidInputMessage()
>	public static void exceptionOccuredMessage()
>	public static void appHeader() 
>> a function to print a header from a text file

public class Input :
>	public static char charInputPrompt(String message)
>	public static char charInputPrompt()
>	public static int intInputPrompt(String message)
>	public static int intInputPrompt()
>	public static boolean verifyInput(char input, char... allowed)

public class Main :
>	public static void main(String[] args) 
>> Main menu loop
>	public static void mainMenu()

public class Food :
>	public static void mainloop() 
>>  initialize restaurants[] serviceFee[] foodItem[] and userOrderQuantity[] based on data files.
>	public static void initArrays() 
>>  initialize restaurants[] serviceFee[] foodItem[] and userOrderQuantity[] based on csv files in .\data\
>	public static void orderFood() 
>>  show menu, orders, then let them update their carts, repeat
>	public static void displayRestaurants(String[] restaurantNames)
>> display restaurant list based on restaurants[] array
>	public static void displayMenu(String[][] menuList)
>>  display menu from chosen restaurant based on foodItem[]  
>	public static void displayCart() 
>>  display user orders
>	public static void addItemToUserOrder(char input)
>>  let user add or remove items from their cart/order

public class Checkout :
>	public static void foodCheckout(String[][] FoodItemList) 
>> checkout method after exiting Food.mainloop
>	public static void servicesCheckout(String[][] servicesList)
>> checkout method after exiting Services.mainloop
>	public static void checkFoodStock(int[] userOrderQuantity,String[][] itemList)
>> this function checks if the stock of user's orders are available 
>> prompt the user to update their cart and return false, if stock unavailable
>	public static String[] calcAndDispPrice(int[] userOrderQuantity, String[][] itemList, double serviceFee) 
>> this function calculates the food price, service fee, and total price 
>	public static void printReceipt(String[] receiptDetails, String location)
>>  print receipt based on receiptDetails[] to a txt file in .\receipts  
>	public static void updateStock(int[] userOrderQuantity, String[][] itemList)
>> update stock in FoodItem

public class Services :
>	public static void mainloop(){
>>  initialize locations[] serviceFee[] servicesItem[] and userOrderQuantity[] based on data files 
>	public static void initArrays()  
>>  initialize locations[] serviceFee[] servicesItem[] and userOrderQuantity[] based on csv files in .\data\  
>	public static void orderServices() 
>>  show services options, user orders, then let them update their orders in a loop 
>	public static void displayLocations(String[] locationNames) 
>> display service locations list based on locations[] array  
>	public static void displayServices(String[][] serviceList) 
>> display service options based on servicesItem[chosenLocationIndex]
>	public static void displayCart() 
>>  display user's orders 
>	public static void addItemToUserOrder(char input) 
>>  let user add or remove items from their cart/order 


## Program flow chart **outdated*

![diagram 1](/data/flowcharts/Diagram_1.png)

![diagram 2](/data/flowcharts/Diagram_2.png)

![diagram 3](/data/flowcharts/Diagram_3.png)

![diagram 4](/data/flowcharts/DIagram_4.png)

![diagram 5](/data/flowcharts/Diagram_5.png)

