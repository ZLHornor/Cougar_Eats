package Interfaces;

import data_base.DataBase;
import resaurant.Menu;
import users.Customer;
import users.Driver;
import users.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static users.Status.*;

public class CustomerInterface {

    private List<Driver> Drivers;
    private Customer customer;
    private final Menu menu;
    private Scanner scnr;
    TextHelpers txt;
    DataBase data;
    private Manager manager;

    public CustomerInterface(DataBase data, Manager manager){

        scnr = new Scanner(System.in);
        menu = new Menu();
        Drivers = new LinkedList<>();
        txt = new TextHelpers(scnr);
        this.manager = manager;
        this.data = data;

    }


    //INITIAL MENU FOR LOG-IN/USER CREATION
    public void mainMenu(){
        //CHECK IF USER IS NEW OR RETURNING CUSTOMER
        returningCustomer();
        //PROCEED TO ACTIONS MENU
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

    //PROMPT FOR USERS ACCESS INFORMATION OR ACCOUNT CREATION
    public void returningCustomer(){
        //RETURNING CUSTOMER?
        txt.printShortLine();
        System.out.println("|       Have an account?      |");
        txt.printShortLine();


        if(!txt.yesOrNo(scnr)){
            //IF NO MAKE ACCOUNT
            getInformation();
        }else{
            //IF YES LOAD CUSTOMER FROM DATABASE
            loadCustomer();
        }
    }

    //USES CUSTOMER PIN TO FIND INFORMATION IN DATABASE AND LOAD IT OVER
    public void loadCustomer(){
        System.out.println("+---------------------------------------+");
        System.out.println("|              Welcome back!            |");
        System.out.println("|        What is your ID Number?        |");
        System.out.println("+---------------------------------------+");

        int id = scnr.nextInt();
        customer = data.getCustomer(id);
        if(customer == null){
            txt.failedToLoad();
            loadCustomer();
        }
    }

    //DISPLAY ACTIONS FOR THE USER
    public void actions(){

        txt.printShortLine();
        System.out.println("|   Please Select an option.  |");
        txt.printShortLine();
        System.out.printf("| %-13s: %12d |\n", "View Menu", 1);
        System.out.printf("| %-13s: %12d |\n", "Add Item", 2);
        System.out.printf("| %-13s: %12d |\n", "Remove Item", 3);
        System.out.printf("| %-13s: %12d |\n", "Your Order", 4);
        System.out.printf("| %-13s: %12d |\n", "Delete Order", 5);
        System.out.printf("| %-13s: %12d |\n", "Send Order", 6);
        System.out.printf("| %-13s: %12d |\n", "Order Status", 7);
        System.out.printf("| %-13s: %12d |\n", "Log Out", 8); //TODO make this method
        txt.printShortLine();
        System.out.println();

        //AUTO SAVES AFTER EVERY SELECTION
        //UPDATES CUSTOMER IN DATABASE
        addCustomerDATA();
        save();

        //USER SELECTS AN OPTION
        menuNavigator();

    }

    //TODO implement cancel functionality
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


    //DISPLAY MENU ITEMS
    public void viewMenu(){
        menu.displayMenu();
        actions();
    }


    //check on existing orders location

    public void checkStatus(){

        Order order = customer.getOrder();
        if(order.getStatus() != PLACED && order.getStatus() != NOT_PLACED){
            order.updateStatus();
            showOrder(order);
        }
        else if (order.getStatus() == DELIVERED){
            System.out.println("+-----------------------------+");
            System.out.println("|       Order Delivered!      |");
            System.out.println("|      Rate your Driver?      |");
            System.out.println("+-----------------------------+");

            if(txt.yesOrNo(scnr)){
                rateDriverPrompt();
                customer.resetOrder();
            }
            else{
                System.out.println("+-----------------------------+");
                System.out.println("|          Thank You!         |");
                System.out.println("|       Have a Nice Day!      |");
                System.out.println("+-----------------------------+");
                customer.resetOrder();
            }
        }else{
            customer.getOrder().displayOrderedItems();
            System.out.println("+-----------------------------+");
            System.out.println("|       Order not sent        |");
            System.out.println("|     Please finish order     |");
            System.out.println("+-----------------------------+");
        }
    }

    //show the users current order
    public void showOrder(Order order){
        order.viewOrder();
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

        data.addOrder(customer.getOrder());
        txt.printGap();
        System.out.println("You're order is on its way");
        txt.printGap();



    }

    //RATE DRIVER AFTER ORDER IS DELIVERED

    public void rateDriver(int rating){

        //Get the customer order from DATABASE
        Order order = data.getOrder(customer.getOrder().getID());
        //Get Driver from Order from Database
        Driver driver = order.getDriver();

        //IF RATINGS HAS SPACE add rating
        if(driver.getRatings().offer(rating)){
            return;
        }
        else{
            //IF RATINGS IS FULL POLL THE FRONT OF QUEUE AND CALL AGAIN
            driver.getRatings().remove();
            rateDriver(rating);
        }

        data.removeOrder(order);


        //return newly rated driver to DATABASE
        save();
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

    public void addCustomerDATA(){
        data.addCustomer(customer);
    }

    public void save(){
        data.save();
    }

    //TODO make a sign out function
}
