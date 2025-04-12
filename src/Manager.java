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
        customer.printInfo();
        yesOrNo();
    }

    // confirm user selection
    /*TODO this needs to be able to be implemented for every selection
        not just for getInformation()
     */
    public void yesOrNo(){
        System.out.printf("| %-10s: %15s |\n", "Yes", "Y");
        System.out.printf("| %-10s: %15s |\n", "ID #", "N");
        String answer = scnr.nextLine().toLowerCase();
        switch (answer){
            case "y" -> {
                actionsMenu();
            }
            case "n" -> {
                System.out.println("|       Let's try again.      |");
                getInformation();
            }
            default -> {
                System.out.println("|         Invalid Entry       |");
                System.out.println("|       Let's try again.      |");
                yesOrNo();

            }
        }
    }

    public void actionsMenu(){
        System.out.println("+-----------------------------+");
        System.out.println("|      Let's get started.     |");
        System.out.println("|   Please Select an option.  |");
        System.out.println("+-----------------------------+");
        actions();
        int choice = scnr.nextInt();


    }

    //switch statement for user selections
    public void menuNavigator(int choice){

        switch (choice){
            case 1 ->{viewMenu();}
            case 2 ->{addItem();}
            //TODO make sure this works and returns error is user entry is incorrect
            case 3 ->{
                System.out.println("+-----------------------------+");
                System.out.println("    Select item to remove.    ");
                customer.getOrder().displayOrderedItems();
                int remove = scnr.nextInt();
                removeItem(remove);}
            case 4 ->{}
            case 5 ->{}
            case 6 ->{}
            default -> {
                System.out.println("+-----------------------------+");
                System.out.println("|         Invalid Entry       |");
                System.out.println("|       Let's try again.      |");
                System.out.println("+-----------------------------+");
                actions();
            }
        }
    }

    //Display actions for the user
    public void actions(){
        System.out.printf("| %-13s: %12d |\n", "View Menu", 1);
        System.out.printf("| %-13s: %12d |\n", "Add Item", 2);
        System.out.printf("| %-13s: %12d |\n", "Remove Item", 3);
        System.out.printf("| %-13s: %12d |\n", "Your Order", 4);
        System.out.printf("| %-13s: %12d |\n", "Send Order", 5);
        System.out.printf("| %-13s: %12d |\n", "Order Status", 6);
        System.out.println();
    }

    // display the menu items
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
    public void addItem(){}

    //remove item from users order & reduce total price of order
    //TODO remove item from order and reduces totalprice
    public void removeItem(int itemNumber){}
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
