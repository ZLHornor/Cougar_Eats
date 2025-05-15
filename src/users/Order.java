package users;

import resaurant.Menu;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static users.Status.*;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private Random ran;
    private List<String> orderedItems;
    private Status status;
    private Customer customer;
    private Driver driver;
    private double totalPrice;

    //NEEDS TO HAVE ITS OWN MENU FOR PRICE CALCULATIONS
    private Menu menu;

    public Order(Customer customer){

        ran = new Random();
        assignID();
        this.customer = customer;
        totalPrice = 0;
        menu = new Menu();
        orderedItems = new ArrayList<>();
        status = NOT_PLACED;
        driver = null;
    }

    //ASSIGN A RANDOM 3 DIGIT ID
    public void assignID(){
        this.id = ran.nextInt(100, 1000);
    }

    //UPDATES ORDER STATUS UNTIL COMPLETED
    public void updateStatus(){
        switch (status){
            case NOT_PLACED -> {
                status = PLACED;
            }
            case PLACED -> {
                status = ON_ROUTE;
            }
            case ON_ROUTE -> {
                status = DELIVERED;
            }
            case DELIVERED -> {
                System.out.println("This order has been delivered.");
            }
        }
    }

    //CHECKS FOR DRIVER AND RETURNS NULL IF NO DRIVER IS PRESENT
    public String driverNameCheck(){
        if(this.driver == null){
            return "NULL";
        }
        return this.driver.getName();
    }

    //DISPLAYS ENTIRE ORDER W/ DRIVER, OWNER, LOCATION
    public void viewOrder(){

        displayOrderedItems();

        System.out.println("+-----------------------------+");
        System.out.printf("| %-13s: %12s |\n", "Owner", customer.getName());
        System.out.printf("| %-13s: %12s |\n", "Location", customer.getLocation());
        System.out.printf("| %-13s: %12s |\n", "Driver", driverNameCheck());
        System.out.println();
    }


    public void displayOrderedItems(){
        System.out.println("+-----------------------------+");
        System.out.println("|          Order " + this.id + "          |");
        System.out.println("+-----------------------------+");

        int i = 1;

        for(String item: orderedItems){
            System.out.printf("| %d | %-10s: %11.2f |\n", i,item, menu.getItemPrice(item));
            i++;
        }
        System.out.printf("| %-14s: %11.2f |\n", "TotalPrice", totalPrice);
        System.out.printf("| %-14s: %11s |\n", "Status", this.status);
        System.out.println("+-----------------------------+");
        System.out.println();
    }

    public int getID(){
        return this.id;
    }


    //ADD AN ITEM TO ORDER
    public void addItem(int num){

        //GET ADD ITEM TO ORDERED ITEMS FROM MENU ITEM LIST
        this.orderedItems.add(menu.getItemList().get(num - 1));

        //MAKE A STRING OF ORDERED ITEM
        String orderedItem = menu.getItemList().get(num - 1);

        //PRINT ORDERED ITEM FOR CUSTOMER TO CONFIRM ORDER
        System.out.printf("| %-13s: %12s |\n", "Ordered Item" , orderedItem);
        System.out.println("+-----------------------------+");
        System.out.println();

        //CALCULATE NEW PRICE
        calculatePrice();
    }

    //REMOVE AN ITEM FROM ORDERED ITEMS LIST
    public void removeItem(int itemNumber) {

        //MAKE STRING OF REMOVED ITEM
        String item = menu.getItemList().get(itemNumber - 1);

        //REMOVER ITEM FROM USER LIST
        this.orderedItems.remove(itemNumber - 1);

        //PRINT REMOVAL CONFIRMATION
        System.out.printf("| %-13s: %12s |\n", "Removed Item" , item);
        System.out.println("+-----------------------------+");
        System.out.println();

        //CALCULATE NEW PRICE
        calculatePrice();
    }

    //CALCULATE PRICE OF CURRENT ORDER
    public void calculatePrice(){
        //RESET PRICE TO ZERO
        totalPrice = 0;

        //ITERATE THROUGH USERS ORDER AND ADD PRICES FROM MENU
        for(String item: orderedItems){
            totalPrice += menu.getItemPrice(item);
        }
    }

    //RETURN USERS ORDERED ITEMS LIST
    public List<String> getOrderedItems() {
        return orderedItems;
    }

    //RETURN ORDER STATUS
    public Status getStatus(){
        return this.status;
    }

    public Driver getDriver(){
        return this.driver;
    }

    public void setDriver(Driver driver){
        this.driver = driver;
    }
}
