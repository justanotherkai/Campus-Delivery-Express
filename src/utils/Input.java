package utils;
import java.util.*;

public class Input{
    public static char charInputPrompt(String message){
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

    public static char charInputPrompt(){
        return charInputPrompt("");
    }
    public static int intInputPrompt(String message){
        return (charInputPrompt(message) - '0');
    }
    public static int intInputPrompt(){
        return intInputPrompt("");
    }

    public static boolean verifyInput(char input, char... allowed){
        for(char allowedInput : allowed){
            if(input == allowedInput) return true;
        }
        return false;
    }
}