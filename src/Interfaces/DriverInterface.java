package Interfaces;

import data_base.DataBase;

public class DriverInterface {

    DataBase data;
    Manager manager;

    public DriverInterface(DataBase data, Manager manager){
        this.data = data;
        this.manager = manager;
    }

}
