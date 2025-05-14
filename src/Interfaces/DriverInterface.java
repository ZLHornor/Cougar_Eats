package Interfaces;

import data_base.DataBase;
import users.Driver;

import java.util.Scanner;

public class DriverInterface {

    DataBase data;
    Manager manager;
    Driver driver;
    TextHelpers txt;
    Scanner scnr;

    public DriverInterface(DataBase data, Manager manager){
        this.data = data;
        this.manager = manager;
        scnr = new Scanner(System.in);
        txt = new TextHelpers(scnr);
    }

    public void mainMenu(){
        data.printDrivers();
        logIn();
        actions();

    }

    private void logIn() {
        txt.printShortLine();
        System.out.println("|      What is your pin?      |");
        txt.printShortLine();

        int pin = scnr.nextInt();

        getDriver(data.getDriver(pin));

    }

    public void getDriver(Driver driver){
        if(driver == null){
            System.out.println("Please try again.");
            logIn();
        }
        else{
            this.driver = driver;
            welcome();
        }
    }

    public void welcome(){
        String name = driver.getName();

        System.out.println("+-----------------------------+");
        System.out.printf("| %-10s: %15s |\n", "Welcome Back", name);
        txt.printShortLine();
    }

    //DISPLAY ACTIONS FOR THE USER
    public void actions(){

        txt.printShortLine();
        System.out.println("|   Please Select an option.  |");
        txt.printShortLine();
        System.out.printf("| %-13s: %12d |\n", "Select Order", 1);
        System.out.printf("| %-13s: %12d |\n", "My Order", 2);
        System.out.printf("| %-13s: %12d |\n", "Update Status", 3);
        System.out.printf("| %-13s: %12d |\n", "Log Out", 8); //TODO make this method
        txt.printShortLine();
        System.out.println();

        //AUTO SAVES AFTER EVERY SELECTION
        data.save();

        //USER SELECTS AN OPTION
        menuNavigator();

    }

    //switch statement for user selections
    public void menuNavigator(){
        int choice = scnr.nextInt();
        switch (choice){
            case 1 ->{
                viewMenu();
                //ASK IF USER WANTS TO ADD AN ITEM
                System.out.println("+ Add an item to your order?  +");
                //System.out.println("+-----------------------------+");
                if(txt.yesOrNo(scnr)){
                    addItem();
                    actions();
                }else{
                    actions();
                }
            }
            case 2 ->{
                addItem();
                actions();
            }
            case 3 ->{
                removeItem();
                actions();
            }
            case 4 -> {
                customer.getOrder().displayOrderedItems();
                actions();
            }
            case 5 -> {
                customer.resetOrder();
                System.out.println("Your order has been reset.");
                actions();
            }
            case 6 ->{
                sendOrder();
                actions();
            }
            case 7 ->{
                checkStatus();
                actions();
            }
            case 8 ->{
                System.out.println("THank you! Have a nice day!");
                data.save();
                manager.start();
            }
            default -> {
                txt.invalidEntry();
                actions();
            }
        }
    }


}
