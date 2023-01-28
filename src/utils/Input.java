/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author AHMAD
 */
import java.util.*;

public class Input{
    public static char charInputPrompt(String message){// get char type inputs while displaying a message
        Scanner userInput = new Scanner(System.in);
        char charInput = '.';
        while(true){
            try{
                System.out.print(message + " > ");
                charInput = userInput.next().charAt(0);
                break;
            }catch(InputMismatchException ex){
                Display.invalidInputMessage();
                userInput.next();
            }
        }
        return charInput;
    }

    public static char charInputPrompt(){// without message
        return charInputPrompt("");
    }
    public static int intInputPrompt(String message){// int type
        Scanner userInput = new Scanner(System.in);
        int intInput = 0;
        while(true){
            try{
                System.out.print(message + " > ");
                intInput = userInput.nextInt();
                break;
            }catch(InputMismatchException ex){
                Display.invalidInputMessage();
                userInput.next();
            }
        }
        return intInput;
    }
    public static int intInputPrompt(){// int type without message
        return intInputPrompt("");
    }

    public static boolean verifyInput(char input, char... allowed){// verify inputs
        for(char allowedInput : allowed){
            if(input == allowedInput) return true;
        }
        return false;
    }

}
