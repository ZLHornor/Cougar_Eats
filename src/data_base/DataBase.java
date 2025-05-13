package data_base;

import Interfaces.TextHelpers;
import users.Customer;
import users.Driver;
import users.Order;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class DataBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    HashMap<Integer, Customer> customers;
    HashMap<Integer, Driver> drivers;
    HashMap<Integer, Order> orders;




    public DataBase(){
        customers = new HashMap<>();
    }

    public void addCustomer(Customer customer){
        customers.put(customer.getID(), customer);
    }

    public Customer loadCustomer(int id){

        if(!customers.containsKey(id)){
            return null;
        }
        return customers.get(id);
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
