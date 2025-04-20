package users;

import resaurant.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static users.Status.*;

public class Order {

    private int orderID;
    private Random ran;
    private List<String> orderedItems;
    private Status status;
    private Customer customer;
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
        status = PLACED;

    }

    //ASSIGN A RANDOM 3 DIGIT ID
    public void assignID(){
        orderID = ran.nextInt(100, 1000);

    }

    //UPDATES ORDER STATUS UNTIL COMPLETED
    public void updateStatus(){
        switch (status){
            case PLACED -> {
                status = ON_ROUTE;
            }
            case ON_ROUTE -> {
                status = DELIVERED;
            }
            case DELIVERED -> {
            }
        }
    }

    public void viewOrder(){

        displayOrderedItems();

        System.out.println("+-----------------------------+");
        System.out.printf("| %-13s: %12s |\n", "Owner", customer.getName());
        System.out.printf("| %-13s: %12s |\n", "Location", customer.getLocation());
        System.out.printf("| %-13s: %12.2f |\n", "TotalPrice", totalPrice);
        System.out.printf("| %-13s: %12s |\n", "Status", this.status);
        System.out.println();
    }

    //TODO CALCULATE NEW PRICE OF ORDER FOR PRINT RETURN
    public void displayOrderedItems(){
        System.out.println("+-----------------------------+");
        System.out.println("|          Order " + orderID + "          |");
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

    //TODO CALCULATE NEW PRICE OF ORDER FOR PRINT RETURN
    //ADD AN ITEM TO ORDER
    public void addItem(int num){

        this.orderedItems.add(menu.getItemList().get(num - 1));
        String orderedItem = menu.getItemList().get(num - 1);
        System.out.printf("| %-13s: %12s |\n", "Ordered Item" , orderedItem);
        System.out.println("+-----------------------------+");
        System.out.println();
        calculatePrice();



    }

    //TODO CALCULATE NEW PRICE OF ORDER FOR PRINT RETURN
    //REMOVE AN ITEM FROM ORDERED ITEMS LIST
    public void removeItem(int itemNumber) {
        String item = menu.getItemList().get(itemNumber - 1);
        this.orderedItems.remove(itemNumber - 1);

        System.out.printf("| %-13s: %12s |\n", "Removed Item" , item);
        System.out.println("+-----------------------------+");
        System.out.println();
        calculatePrice();




    }

    public void calculatePrice(){
        totalPrice = 0;
        for(String item: orderedItems){
            totalPrice += menu.getItemPrice(item);

        }
    }

    public List<String> getOrderedItems() {
        return orderedItems;
    }
}
