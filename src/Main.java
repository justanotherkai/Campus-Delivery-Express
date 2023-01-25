/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AHMAD KHAIRIL AMIN BIN HISHAMUDDIN (2212947)
 */
import utils.*;

public class Main{
    public static void main(String[] args){
        //Main menu loop
        while(true){
            Food.userOrderQuantity = new int[3];//reset userorder 
            Display.clearConsole();//clears console
            Display.appHeader();//display app header banner
            mainMenu();//display main menu options

            //Process user input
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
        }
    }
    public static void mainMenu(){
        System.out.println("1. Food");
        System.out.println("2. Services");
        System.out.println("3. Exit");
    }
}