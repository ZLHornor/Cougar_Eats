package Interfaces;

import java.util.Scanner;

public class TextHelpers {

    Scanner scnr;

    public TextHelpers(Scanner scnr){
        this.scnr = scnr;
    }

    //PRINT COUGAR EATS WITH BORDERS
    public void printTitle(){
        printShortLine();
        System.out.println("|         Cougar Eats         |");
        printShortLine();
    }

    public void printShortLine(){
        System.out.println("+-----------------------------+");
    }

    public void printLongLine(){
        System.out.println("+---------------------------------------+");
    }

    //CONFIRM YES OR NO AND RETURN BOOLEAN
    public boolean yesOrNo(Scanner scnr){
        printShortLine();
        System.out.printf("| %-10s: %15s |\n", "Yes", "Y");
        System.out.printf("| %-10s: %15s |\n", "No", "N");
        printShortLine();
        String answer = scnr.nextLine().toLowerCase();

        switch (answer) {
            case "y" -> {
                printGap();
                return true;}
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
        printGap();
    }

    public void rememberID(int id){
        System.out.println("+-----------------------------+");
        System.out.println("|    Please Remember you're   |");
        System.out.println("|         Your ID Num         |");
        System.out.println("|             " + id + "            |");
        System.out.println("+-----------------------------+");
        printGap();
    }

    public void failedToLoad(){
        System.out.println("+-----------------------------+");
        System.out.println("|        Failed to Load       |");
        System.out.println("|       Let's try again.      |");
        System.out.println("+-----------------------------+");
        printGap();
    }

    public void printGap(){
        System.out.println();
    }

    public int getIntegers(String prompt){
        System.out.println(prompt);
        return scnr.nextInt();
    }

}
