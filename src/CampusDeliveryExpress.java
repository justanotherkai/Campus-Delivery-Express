import java.io.*;
import utils.*;

public class CampusDeliveryExpress{
    public static void main(String[] args){
        //Main menu loop
        while(true){
            Food.userOrderQuantity = new int[3];//reset userorder 
            Display.clearConsole();
            Display.appHeader();
            mainMenu();
            char userInput = Input.charInputPrompt();
            if(userInput == '1'){
                Food.mainloop();
            }
            else if(userInput == '2'){
                Services.mainloop();
            }
            else if(userInput == '3'){//Exit
                System.out.print("Are you sure you want to exit?(y/n):");
                if(Input.charInputPrompt() == 'y') break;
            }
            else
                Display.invalidInputMessage();
        }
    }
    public static void mainMenu(){
        System.out.println("1. Food");
        System.out.println("2. Services");
        System.out.println("3. Exit");
    }
}
