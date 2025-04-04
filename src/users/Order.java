package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static users.Status.*;

public class Order {

    private int orderID;
    private Random ran;
    private List<String> orderedItems;
    private Status status;

    public Order(){

        ran = new Random();
        assignID();

        orderedItems = new ArrayList<>();
        status = PLACED;

    }

    //ASSIGN A RANDOM 3 DIGIT ID
    public void assignID(){
        orderID = ran.nextInt(100, 1000);

    }

    //UPDATES ORDER STATUS UNTIL COMPLETED
    public void updateStatus(){
        switch (status){
            case PLACED -> {
                status = ON_ROUTE;
            }
            case ON_ROUTE -> {
                status = DELIVERED;
            }
            case DELIVERED -> {
            }
        }
    }
}
