package Interfaces;

import java.util.Scanner;

public class TextHelpers {

    Scanner scnr;

    public TextHelpers(Scanner scnr){
        this.scnr = scnr;
    }

    //PRINT COUGAR EATS WITH BORDERS
    public void printTitle(){
        System.out.println("+-----------------------------+");
        System.out.println("|         Cougar Eats         |");
        System.out.println("+-----------------------------+");
    }

    //CONFIRM YES OR NO AND RETURN BOOLEAN
    public boolean yesOrNo(Scanner scnr){
        System.out.printf("| %-10s: %15s |\n", "Yes", "Y");
        System.out.printf("| %-10s: %15s |\n", "No", "N");
        String answer = scnr.nextLine().toLowerCase();

        switch (answer) {

            case "y" -> { return true;}
            case "n" -> { return false;}
            default -> {
                invalidEntry();
                yesOrNo(scnr);
            }
        }
        return true;
    }

    //TEXT PROMPT FOR INVALID ENTRY
    public void invalidEntry() {
        System.out.println("+-----------------------------+");
        System.out.println("|         Invalid Entry       |");
        System.out.println("|       Let's try again.      |");
        System.out.println("+-----------------------------+");
    }
}
