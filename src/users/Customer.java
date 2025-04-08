package users;

public class Customer extends Person{

    private Driver driver = null;
    private Order order;
    private boolean orderPlaced = false;

    public Customer(String name, String location) {
        super(name, location);
    }

    @Override
    public void printInfo() {

    }

    //RETURN DRIVER FOR rateDriver() IN MANAGER
    public Driver getDriver (){
        return this.driver;
    }

    //ASSIGN AN AVAILABLE DRIVER
    public void setDriver(Driver driver){
        this.driver = driver;
    }
}
