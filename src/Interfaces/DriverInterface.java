package Interfaces;

import data_base.DataBase;
import users.Driver;
import users.Order;

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
        System.out.printf("| %-13s: %12d |\n", "Log Out", 4); //TODO make this method
        txt.printShortLine();
        System.out.println();

        //AUTO SAVES AFTER EVERY SELECTION
        save();
        data.save();

        //USER SELECTS AN OPTION
        menuNavigator();

    }

    //switch statement for user selections
    public void menuNavigator(){
        int choice = scnr.nextInt();
        switch (choice){
            case 1 ->{
                selectOrder();
                actions();
            }
            case 2 ->{
                driver.getOrder().viewOrder();
                actions();
            }
            case 3 ->{
                updateStatus();
                actions();
            }
            case 4 -> {
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



    // PROMPTS DRIVER TO SELECT AN ORDERS PIN FROM LIST OF ORDERS
    /*
    1. Print Orders
    2. Get order ID number from the user
    3. retrieve order from database and assign to driver
    4. update order with assigned driver
    5. replace database order with updated order
     */
    private void selectOrder() {
        data.printOrders();

        int orderNum = txt.getIntegers("Please Select an order by its ID.");

        Order order = data.getOrder(orderNum);
        driver.setOrder(order);

        driver.getOrder().setDriver(driver);
        driver.setAvailable(false);

        save();
    }

    /*
    1. put current order into database for update or later use
     */
    public void updateOrder(Order order){
        data.addOrder(order);
    }

    /*\
    1. save order to database
    2. save driver to database
     */
    public void save(){
        updateOrder(driver.getOrder());
        data.addDriver(driver);
    }


    /*
    1. Update current working order
    2. check order status and update driver location
     */
    public void updateStatus(){
        driver.getOrder().updateStatus();
        switch(driver.getOrder().getStatus()){
            case NOT_PLACED -> {
            }
            case PLACED -> {
                driver.setLocation("Waiting on order.");
            }
            case ON_ROUTE -> {
                driver.setLocation("On our way.");
            }
            case DELIVERED -> {
                driver.setLocation("On our way home.");
            }
        }
    }


}
