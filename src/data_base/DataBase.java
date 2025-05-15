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

    public void addCustomer(Customer customer){
        customers.put(customer.getID(), customer);
    }

    public void addDriver(Driver driver) {drivers.put(driver.getID(), driver);}

    public void addOrder(Order order) {
        this.orders.put(order.getID(), order);
    }

    public Customer getCustomer(int id){

        if(!customers.containsKey(id)){
            return null;
        }
        return customers.get(id);
    }

    public Order getOrder(int id){
        return orders.get(id);
    }

    public void save(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            out.writeObject(this);
            System.out.println("Database saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCustomers(){
        for(Customer cust : customers.values()){
            cust.printInfo();

        }
    }

    // FILL SOME DRIVERS INTO THE LINKEDLIST
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

    public void printDrivers(){
        for(Driver driver : drivers.values()){
            driver.printInfo();

        }
    }

    public Driver getDriver (int pin){
        if (drivers.containsKey(pin)){
            return drivers.get(pin);
        }
        else {

            System.out.println("Driver Not Found.");
            return null;
        }

    }

    public void printOrders(){
        for (Order order: orders.values()){
            order.viewOrder();
        }
    }




}
