/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author AHMAD
 */
import java.io.*;
import java.util.*;

public class Display{

    //a function to clear the console
    public static void clearConsole(){
        //a message will appear if console isn't cleared
        System.out.println("Attention: This program only works with windows cmd and terminals that support ANSI escape sequence");
        //ansi escape code for clearing the console
        System.out.print("\033[H\033[2J");
        //a technique to clear the windows command prompt console
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch(IOException ex){
            exceptionOccuredMessage();
        }catch(InterruptedException ex){
            exceptionOccuredMessage();
        }
    }

    public static void invalidInputMessage(){
        System.out.println("Invalid input, please try again.");
    }
    public static void exceptionOccuredMessage(){
        System.out.println("An exception has occured!");
    }

    //a function to print a header from a text file
    public static void appHeader(){
        File headerFile = new File("./data/appHeader.txt");
        try{
            Scanner sc = new Scanner(headerFile);
            while (sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
        System.out.println();
    }
}
