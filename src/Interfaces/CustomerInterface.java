package Interfaces;

import data_base.DataBase;
import resaurant.Menu;
import users.Customer;
import users.Driver;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static users.Status.DELIVERED;

public class CustomerInterface {

    private List<Driver> Drivers;
    private Customer customer;
    private final Menu menu;
    private Scanner scnr;
    TextHelpers txt;
    DataBase data;

    public CustomerInterface(DataBase data){

        scnr = new Scanner(System.in);
        menu = new Menu();
        Drivers = new LinkedList<>();
        txt = new TextHelpers(scnr);
        this.data = data;

        hireDrivers();
    }

//TODO fill in user interface methods
    // main menu of options for user to select
    public void mainMenu(){
        returningCustomer();
        actions();
    }


    //GET USER INFORMATION AND CREATE CUSTOMER FROM INPUT
    public void getInformation(){
        String name;
        String address;

        txt.printShortLine();
        System.out.println("|   Please enter your name:   |");
        name = scnr.nextLine();
        System.out.println("|  Please enter your address: |");
        address = scnr.nextLine();
        txt.printLongLine();
        System.out.println("|                Welcome                |");
        txt.printLongLine();
        System.out.println("|         Is your info correct?         |");
        System.out.printf("| %-10s: %25s |\n", "Name", name);
        System.out.printf("| %-10s: %25s |\n", "Address", address);
        txt.printLongLine();


        if(txt.yesOrNo(scnr)){
            customer = new Customer(name, address);
            //Add Customer to DATA BASE
            data.addCustomer(customer);


            customer.printInfo();
            txt.rememberID(customer.getID());
            actions();
        }
        else{
            System.out.println("|       Let's try again.      |");
            getInformation();
        }
    }

    public void returningCustomer(){
        txt.printShortLine();
        System.out.println("|       Have an account?      |");
        txt.printShortLine();

        if(!txt.yesOrNo(scnr)){
            getInformation();
        }else{
            loadCustomer();
        }
    }

    public void loadCustomer(){
        System.out.println("+---------------------------------------+");
        System.out.println("|              Welcome back!            |");
        System.out.println("|        What is your ID Number?        |");

        int id = scnr.nextInt();
        customer = data.loadCustomer(id);
        if(customer == null){
            txt.failedToLoad();
            loadCustomer();
        }
    }



    //DISPLAY ACTIONS FOR THE USER
    public void actions(){
        System.out.println("+-----------------------------+");
        System.out.println("|   Please Select an option.  |");
        System.out.println("+-----------------------------+");

        System.out.printf("| %-13s: %12d |\n", "View Menu", 1);
        System.out.printf("| %-13s: %12d |\n", "Add Item", 2);
        System.out.printf("| %-13s: %12d |\n", "Remove Item", 3);
        System.out.printf("| %-13s: %12d |\n", "Your Order", 4);
        System.out.printf("| %-13s: %12d |\n", "Send Order", 5);
        System.out.printf("| %-13s: %12d |\n", "Order Status", 6);
        System.out.println();
        data.save();
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
            case 5 ->{
                sendOrder();
                actions();
            }
            case 6 ->{
                checkStatus();
                actions();
            }
            default -> {
                txt.invalidEntry();
                actions();
            }
        }
    }


    //DISPLAY MENU ITEMS
    public void viewMenu(){
        menu.displayMenu();
        actions();
    }

    //check on existing orders location
    //TODO shows driver location and order status
    //TODO increments both to simulate order progression
    public void checkStatus(){
        customer.getOrder().updateStatus();
        showOrder();
        if (customer.getOrder().getStatus() == DELIVERED){

            System.out.println("Your Order has been delivered. Would you like to rate your Driver?");
            if(txt.yesOrNo(scnr)){
                rateDriverPrompt();
            }
            else{
                System.out.println("Have a nice day.");
            }


        }
    }

    //show the users current order
    public void showOrder(){
        customer.getOrder().viewOrder();
    }
    //add item to users order
    //TODO add item to order and increase price
    public void addItem(){
        menu.displayMenu();
        System.out.println("+-----------------------------+");
        System.out.println("+Select an item number to add.+");
        int selection = scnr.nextInt();
        if (selection < 1 || selection > 7){
            txt.invalidEntry();
            addItem();
        }
        customer.getOrder().addItem(selection);
    }

    //remove item from users order & reduce total price of order
    //TODO remove item from order and reduces totalprice
    public void removeItem(){
        customer.getOrder().displayOrderedItems();
        System.out.println("+-----------------------------+");
        System.out.println("+--Select an item to remove.--+");
        int selection = scnr.nextInt();
        if (selection < 1 || selection > customer.getOrder().getOrderedItems().size()){
            txt.invalidEntry();
            removeItem();
        }
        customer.getOrder().removeItem(selection);
        actions();
    }

    //APPROVE ORDER AND ASSIGN AN AVAILABLE DRIVER
    public void sendOrder(){
        for(Driver driver : Drivers){
            if (driver.isAvailable()){
                driver.setAvailable(false);
                //ASSIGN THE AVAILABLE DRIVER TO THE CUSTOMER
                customer.setDriver(driver);
                System.out.println(driver.getName() + " has been assigned.");
                customer.getDriver().printInfo();
                driver.setCustomer(this.customer);

                customer.getOrder().updateStatus();
                break;
            }
        }
    }

    //RATE DRIVER AFTER ORDER IS DELIVERED
    //TODO Check Functionality
    public void rateDriver(int rating){
        //IF RATINGS HAS SPACE DO NOTHING
        if(customer.getDriver().getRatings().offer(rating)){
            return;
        }
        else{
            //IF RATINGS IS FULL POLL THE FRONT OF QUEUE AND CALL AGAIN
            customer.getDriver().getRatings().remove();
            rateDriver(rating);
        }
    }

    public void rateDriverPrompt(){
        int rating = getRatingHelper();

        if (rating < 1 || rating > 5){
            txt.invalidEntry();
            rateDriverPrompt();
        }else{
            rateDriver(rating);
        }
    }

    public int getRatingHelper(){

        System.out.println("Please provide a rating between 1 and 5.");
        return scnr.nextInt();

    }


    // FILL SOME DRIVERS INTO THE LINKEDLIST
    public void hireDrivers(){
        Drivers.add(new Driver("Driver 1", "In House"));
        Drivers.add(new Driver("Driver 2", "In House"));
        Drivers.add(new Driver("Driver 3", "In House"));
        Drivers.add(new Driver("Driver 4", "In House"));
        Drivers.add(new Driver("Driver 5", "In House"));
    }

    // DISPLAY ALL DRIVER INFO
    public void printDrivers(){
        for(Driver driver: Drivers){
            driver.printInfo();
        }
    }

    //TODO make a sign out function
}
