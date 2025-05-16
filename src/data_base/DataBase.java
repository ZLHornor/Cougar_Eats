package data_base;

import users.Customer;
import users.Driver;
import users.Order;

import java.io.*;
import java.util.HashMap;

public class DataBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    HashMap<Integer, Customer> customers;
    HashMap<Integer, Driver> drivers;
    HashMap<Integer, Order> orders;


    public DataBase(){
        customers = new HashMap<>();
        orders = new HashMap<>();
        drivers = new HashMap<>();
        hireDrivers();
    }

    /**
    The next three methods add to data structures
    1. add customer to customers
    2. add driver to drivers
    3. add order to orders

    All use their pins as keys to their HashMaps
     */
    public void addCustomer(Customer customer){
        customers.put(customer.getID(), customer);
    }

    public void addDriver(Driver driver) {drivers.put(driver.getID(), driver);}

    public void addOrder(Order order) {
        this.orders.put(order.getID(), order);
    }

    /**
    * All three getters
    * Each getter has a check that returns null and an error message if the
    * Pin Doesn't exit
     */
    public Customer getCustomer(int id){

        if(!customers.containsKey(id)){
            System.out.println("+-----------------------------+");
            System.out.println("|     Customer Not Found      |");
            System.out.println("|      Please Try Again.      |");
            System.out.println("+-----------------------------+");
            return null;
        }
        return customers.get(id);
    }

    public Order getOrder(int id){
        if (!orders.containsKey(id)) {
            System.out.println("+-----------------------------+");
            System.out.println("|       Order Not Found       |");
            System.out.println("|      Please Try Again.      |");
            System.out.println("+-----------------------------+");
            return null;
        }

        return orders.get(id);
    }

    public Driver getDriver (int pin){
        if (drivers.containsKey(pin)){
            return drivers.get(pin);
        }
        else {
            System.out.println("+-----------------------------+");
            System.out.println("|       Order Not Found       |");
            System.out.println("|      Please Try Again.      |");
            System.out.println("+-----------------------------+");
            return null;
        }
    }


    /**
     * Save the Serialized DataBase Class
     * saves to file and confirms with prompt
     * throws IO exception
     */
    public void save(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            out.writeObject(this);
            System.out.println("Database saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Print all the desired Objects information
     */
    public void printCustomers(){
        for(Customer customer : customers.values()){
            customer.printInfo();

        }
    }

    public void printOrders(){
        for (Order order: orders.values()){
            order.viewOrder();
        }
    }

    public void printDrivers(){
        for(Driver driver : drivers.values()){
            driver.printInfo();

        }
    }


    /**
     * FILL SOME DRIVERS INTO THE Hashtable w/ randomized numbers
     */
    public void hireDrivers(){

        Driver jimmy = new Driver("Jimmy", "In House");
        Driver billy = new Driver("Billy", "In House");
        Driver timmy = new Driver("Timmy", "In House");
        Driver smitty = new Driver("Smitty", "In House");
        Driver greg = new Driver("Greg", "In House");



        drivers.put(jimmy.getID(), jimmy);
        drivers.put(billy.getID(), billy);
        drivers.put(timmy.getID(), timmy);
        drivers.put(smitty.getID(), smitty);
        drivers.put(greg.getID(), greg);

    }



    /**
    1. Remove order from DataBase
    2. Send removal Confirmation
     */
    public void removeOrder(Order order){
        orders.remove(order.getID());
        System.out.println("+-----------------------------+");
        System.out.println("|       Order Completed.      |");
        System.out.println("+-----------------------------+");
    }




}
