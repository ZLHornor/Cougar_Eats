import resaurant.Menu;
import users.Customer;
import users.Driver;

import java.util.LinkedList;
import java.util.List;

public class Manager {

    private List<Driver> Drivers;
    private Customer customer;
    private final Menu menu;

    public Manager(){
        menu = new Menu();
        Drivers = new LinkedList<>();
        hireDrivers();

    }

//TODO fill in user interface methods
    // main menu of options for user to select
    public void mainMenu(){}
    // display the menu items
    public void viewMenu(){}
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
