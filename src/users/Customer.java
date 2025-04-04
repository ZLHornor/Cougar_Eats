package users;

public class Customer extends Person{

    private Driver driver;
    private Order order;

    public Customer(String name, String location) {
        super(name, location);
    }

    @Override
    public void printInfo() {

    }
}
