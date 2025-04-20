import resaurant.Menu;
import users.Customer;
import users.Driver;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    private List<Driver> Drivers;
    private Customer customer;
    private final Menu menu;
    private Scanner scnr;

    public Manager(){
        scnr = new Scanner(System.in);
        menu = new Menu();
        Drivers = new LinkedList<>();
        hireDrivers();
        mainMenu();
        //viewMenu();


    }

//TODO fill in user interface methods
    // main menu of options for user to select
    public void mainMenu(){
        printTitle();
        getInformation();

    }

    public void printTitle(){
        System.out.println("+-----------------------------+");
        System.out.println("|         Cougar Eats         |");
        System.out.println("+-----------------------------+");
    }

    //GET USER INFORMATION AND CREATE CUSTOMER FROM INPUT
    public void getInformation(){
        String name;
        String address;

        System.out.println("|   Please enter your name:   |");
        name = scnr.nextLine();
        System.out.println("|  Please enter your address: |");
        address = scnr.nextLine();
        customer = new Customer(name, address);
        System.out.println("+---------------------------------------+");
        System.out.println("|                Welcome                |");
        System.out.println("+---------------------------------------+");
        System.out.println("|         Is your info correct?         |");

        if(yesOrNo()){
            customer.printInfo();
            actions();

        }
        else{
            getInformation();
        }
    }

    //CONFIRM YES OR NO AND RETURN BOOLEAN
    public boolean yesOrNo(){
        System.out.printf("| %-10s: %15s |\n", "Yes", "Y");
        System.out.printf("| %-10s: %15s |\n", "No", "N");
        String answer = scnr.nextLine().toLowerCase();

        switch (answer) {

            case "y" -> { return true;}
            case "n" -> {
                System.out.println("|       Let's try again.      |");
                return false;
            }
            default -> {
                invalidEntry();
                yesOrNo();
            }
        }
        return true;

    }

    //switch statement for user selections
    public void menuNavigator(){
        int choice = scnr.nextInt();
        switch (choice){
            case 1 ->{
                viewMenu();
                System.out.println("+-Add an item to your order?--+");
                System.out.println("+-----------------------------+");
                yesOrNo();

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
            case 5 ->{}
            case 6 ->{}
            default -> {
                invalidEntry();
                actions();
            }
        }
    }

    //TEXT PROMPT FOR INVALID ENTRY
    private void invalidEntry() {
        System.out.println("+-----------------------------+");
        System.out.println("|         Invalid Entry       |");
        System.out.println("|       Let's try again.      |");
        System.out.println("+-----------------------------+");
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
        menuNavigator();
    }

    //DISPLAY MENU ITEMS
    public void viewMenu(){
        menu.displayMenu();
        actions();
    }

    //check on existing orders location
    //TODO shows driver location and order status
    //TODO increments both to simulate order progression
    public void checkStatus(){}

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
            invalidEntry();
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
        if (selection < 1 || selection > customer.getOrder().getOrderedItems().size()+1){
            invalidEntry();
            removeItem();
        }
        customer.getOrder().removeItem(selection);
        actions();
    }
    //approve order and assign it to a driver
    public void sendOrder(){
        for(Driver driver : Drivers){
            if (driver.isAvailable()){
                driver.setAvailable(false);
                //ASSIGN THE AVAILABLE DRIVER TO THE CUSTOMER
                customer.setDriver(driver);
                customer.getDriver().printInfo();
                driver.setCustomer(this.customer);
                System.out.println(driver.getName() + " has been assigned.");



            }
        }
    }
    //rate driver after order is completed
    //TODO prompts user after order is completed
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
}
