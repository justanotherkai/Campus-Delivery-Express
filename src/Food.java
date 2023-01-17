import utils.*;

public class Food{
public static String[] FoodLocation = 
        {"Delicious Pizza","Indo gepuk","Cafe Corner","Sushi Delight","Nacho Taco","Tomyam Shop","Thailicious","Grill Master","Food Mart","Jazmina Corner"};

    public static int[] userOrderQuantity = new int[3];

    public static String[][][] FoodItem = {// Elements: name, price, stock
        {{"Hawaiian Pizza","10","99"},{"Basic Pizza","3","99"},{"Cheese Pizza","8","99"}},
        {{"Nasi Ayam Gepuk","6","99"},{"Nasi Ayam Geprek","6","99"},{"Nasi Indonesia","5","99"}},
        {{"Caramel Machiato Coffee","8","99"},{"Latte Coffee","5","99"},{"Iced Coffee","3","99"}},
        {{"Tuna","5","99"},{"Salmon","5","99"},{"Sashimi","5","99"}},
        {{"Beef Taco","7","99"},{"Spicy Taco","9","99"},{"Normal Taco","5","99"}},
        {{"Beef Tomyam","20","99"},{"Tomyam 2pax","18","99"},{"Tomyam Gung","15","99"}},
        {{"PadThai","4","99"},{"Thai Fried Rice","7","99"},{"Roti Thai","5","99"}},
        {{"Beef Chop","18","99"},{"Chicken Chop","15","99"},{"Lamb Chop","20","99"}},
        {{"Maggi Goreng","5","99"},{"Maggi Gorend Double","9","99"},{"Nasi Goreng Kampung","8","99"}},
        {{"Roti Telur","3","99"},{"Roti Kosong","1","99"},{"Noan Garlic Butter","9","99"}},     
    };

    public static int locationChose = 0;

    public static void mainloop(){
        Display.clearConsole();
        Display.appHeader();
        displayRestaurants(FoodLocation);
        locationChose = Input.intInputPrompt("Location");
        orderFood();
        Checkout.mainloop();
    }

    public static void orderFood(){
        while(true){
            Display.clearConsole();
            Display.appHeader();
            displayMenu(FoodItem[locationChose]);
            displayCart(userOrderQuantity);
            char input = Input.charInputPrompt("Which item would you like to add or remove? (Enter q if you are done)\n");
            if(input == 'q') break;
            if(!Input.verifyInput(input,'1','2','3'))continue;// ignore invalid inputs
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
        System.out.printf("%3s%-32s Price\n","","Name");
        for(int i = 0; i < menuList.length; i++){
                System.out.printf("%d. %-32s RM%s.00\n", i+1, menuList[i][0], menuList[i][1] );
        }
    }

    public static void displayCart(int[] userOrderQuantity){
        System.out.println();
        System.out.println("Your orders: ");
        for(int i = 0; i < userOrderQuantity.length; i++){
            if(userOrderQuantity[i] != 0) System.out.println((i+1) + ". " + FoodItem[locationChose][i][0] + " x " + userOrderQuantity[i]);
        }
        System.out.println();
    }

    public static void addItemToUserOrder(char input){
        int inputNum = input - '0';// convert char to int
        inputNum -= 1;// to compensate since index starts with zero, but input option starts with one
        userOrderQuantity[inputNum] += Input.intInputPrompt("How many " + FoodItem[locationChose][inputNum][0] + " would you like to add? (Input negative number to remove item)\n");
        if(userOrderQuantity[inputNum] < 0) userOrderQuantity[inputNum] = 0;
    }
}