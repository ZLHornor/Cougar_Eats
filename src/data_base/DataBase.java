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
    }

    public void addCustomer(Customer customer){
        customers.put(customer.getID(), customer);
    }

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



}
