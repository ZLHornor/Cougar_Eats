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
        //mainMenu();
        viewMenu();


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
        System.out.println("|      Let's get started.     |");
        System.out.println("|   Please Select an option.  |");
        actions();

    }

    public void actions(){
        System.out.printf("| %-13s: %12d |\n", "View Menu", 1);
        System.out.printf("| %-13s: %12d |\n", "Add Item", 2);
        System.out.printf("| %-13s: %12d |\n", "Remove Item", 3);
        System.out.printf("| %-13s: %12d |\n", "Your Order", 4);
        System.out.printf("| %-13s: %12d |\n", "Send Order", 5);
        System.out.printf("| %-13s: %12d |\n", "Order Status", 6);
    }
    // display the menu items
    public void viewMenu(){
        menu.displayMenu();
        actions();
    }
    //check on existing orders location
    public void checkStatus(){}
    //show the users current order
    public void showOrder(){}
    //add item to users order
    public void addItem(){}
    //remove item from users order
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
