package Interfaces;

import data_base.DataBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import static Interfaces.USER.*;

public class Manager {

    private USER userCheck;
    Scanner scnr;
    CustomerInterface cstmr;
    DriverInterface drvr;
    TextHelpers txt;
    DataBase data;

    public Manager(){
        init();
        data.printCustomers();
        start();
    }

    //Instantiate Objects
    public void init(){
        scnr = new Scanner(System.in);
        data = loadData();
        userCheck = null;
        cstmr = new CustomerInterface(data, this);
        drvr = new DriverInterface(data, this);
        txt = new TextHelpers(scnr);


    }

    //LOAD DATA BASE CONTAINING ALL USER INFORMATION
    public DataBase loadData(){
        //CHECK FOR DATABASE FILE
        File file = new File("data.ser");

        //IF FOUND LOAD IT INTO DATABASE OBJECT
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                return (DataBase) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Failed to load existing database. Creating new one...");
            }
        } else {
            System.out.println("No database file found. Creating new one...");
        }

        //IF NOT FOUND CREATE NEW DATABASE
        return new DataBase(); // create a fresh one if no file or loading failed
    }

    //LET USER DECIDE IF THEY ARE A CUSTOMER OR A DRIVER
    public void start(){

        txt.printTitle();

        selectMode();
        switchUsers();
    }

    public void selectMode(){

        int choice;
        System.out.println("|     Select an Interface     |");
        System.out.println("+-----------------------------+");
        System.out.printf("| %-10s: %15s |\n", "Driver", "1");
        System.out.printf("| %-10s: %15s |\n", "Customer", "2");
        System.out.println("+-----------------------------+");

        choice = scnr.nextInt();

        if(choice == 1){
            userCheck = DRIVER;
        }
        if(choice == 2){
            userCheck = CUSTOMER;
        }else{
            txt.invalidEntry();
            selectMode();
        }
    }

    public void switchUsers(){
        switch(userCheck){
            case DRIVER ->{}
            case CUSTOMER -> cstmr.mainMenu();
        }
    }

}
