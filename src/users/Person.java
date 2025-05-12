package users;

import java.io.Serializable;
import java.util.Random;

public abstract class Person implements Serializable {

    protected Random ran;
    protected String name;
    protected String location;
    protected int id;

    public Person(String name, String location){
        ran = new Random();
        this.name = name;
        this.location = location;
        id = ran.nextInt(1000, 10000);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void printInfo();




}
