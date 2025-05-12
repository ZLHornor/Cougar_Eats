package data_base;

import users.Customer;
import users.Driver;
import users.Order;

import java.util.HashMap;

public class DataBase {

    HashMap<Integer, Customer> customers;
    HashMap<Integer, Driver> drivers;
    HashMap<Integer, Order> orders;

    public DataBase(){
        customers = new HashMap<>();

    }

    public void addCustomer(Customer customer){
        customers.put(customer.getID(), customer);

    }



}
