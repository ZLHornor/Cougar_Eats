package users;

public class Customer extends Person{

    private Driver driver = null;
    private Order order;
    private boolean orderPlaced = false;

    public Customer(String name, String location) {
        super(name, location);
        order = new Order(this);
    }

    @Override
    public void printInfo() {
        System.out.printf("| %-10s: %25s |\n", "Name", this.name);
        System.out.printf("| %-10s: %25s |\n", "ID #", this.id);
        System.out.printf("| %-10s: %25s |\n", "Address", this.location);
        System.out.println("+---------------------------------------+");
        System.out.println();
    }

    //RETURN DRIVER FOR rateDriver() IN MANAGER
    public Driver getDriver (){
        return this.driver;
    }

    //ASSIGN AN AVAILABLE DRIVER
    public void setDriver(Driver driver){
        this.driver = driver;
    }

    public Order getOrder() {
        return this.order;
    }
}
