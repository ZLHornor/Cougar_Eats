package Interfaces;

import java.util.Scanner;

import static Interfaces.USER.*;

public class Manager {

    private USER userCheck;
    Scanner scnr;
    CustomerInterface cstmr;
    DriverInterface drvr;

    public Manager(){
        scnr = new Scanner(System.in);
        userCheck = null;
        cstmr = new CustomerInterface();
        drvr = new DriverInterface();
    }

    //PRINT COUGAR EATS WITH BORDERS
    public void printTitle(){
        System.out.println("+-----------------------------+");
        System.out.println("|         Cougar Eats         |");
        System.out.println("+-----------------------------+");
    }

    public void whoAreYou(){
        printTitle();



        switch(userCheck){
            case DRIVER ->{}
            case CUSTOMER ->{}
            case null, default -> {return;}
        }


    }

    public void selectMode(){
        System.out.printf("| %-10s: %15s |\n", "Driver", "1");
        System.out.printf("| %-10s: %15s |\n", "Customer", "2");
    }
}
