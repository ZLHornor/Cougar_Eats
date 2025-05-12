package Interfaces;

import java.util.Scanner;

import static Interfaces.USER.*;

public class Manager {

    private USER userCheck;
    Scanner scnr;
    CustomerInterface cstmr;
    DriverInterface drvr;
    TextHelpers txt;

    public Manager(){
        init();
        start();
    }

    //Instantiate Objects
    public void init(){
        scnr = new Scanner(System.in);
        userCheck = null;
        cstmr = new CustomerInterface();
        drvr = new DriverInterface();
        txt = new TextHelpers(scnr);
    }

    //LET USER DECIDE IF THEY ARE A CUSTOMER OR A DRIVER
    public void start(){

        txt.printTitle();

        selectMode();
        switchUsers();
    }

    public void selectMode(){

        int choice = 0;
        System.out.println("+     Select an Interface     +");
        System.out.println("+-----------------------------+");
        System.out.printf("| %-10s: %15s |\n", "Driver", "1");
        System.out.printf("| %-10s: %15s |\n", "Customer", "2");
        System.out.println("+-----------------------------+");

        choice = scnr.nextInt();

        if(choice == 1){
            userCheck = DRIVER;
        }
        if(choice == 2){
            userCheck = CUSTOMER;
        }else{
            txt.invalidEntry();
            selectMode();
        }
    }

    public void switchUsers(){
        switch(userCheck){
            case DRIVER ->{}
            case CUSTOMER ->{cstmr.mainMenu();}
        }
    }

}
