package users;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Driver extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    Random ran;
    private ArrayBlockingQueue<Integer> ratings;

    Order order;

    public Driver(String name, String location){
        super(name, location);

        ran = new Random();
        ratings = new ArrayBlockingQueue<>(10);
        fudgeRatings();

    }

    //NICE LITTLE DISPLAY OF DRIVERS INFO
    @Override
    public void printInfo() {
        System.out.println("+-----------------------------+");
        System.out.println("|        Driver Info          |");
        System.out.println("+-----------------------------+");
        System.out.printf("| %-10s: %15s |\n", "Name", this.name);
        System.out.printf("| %-10s: %15s |\n", "ID #", this.id);
        System.out.printf("| %-10s: %15.2f |\n", "Rating", getRating());
        System.out.printf("| %-10s: %15s |\n", "Location", this.location);
        System.out.println("+-----------------------------+");
        System.out.println();

    }

    // CALCULATE AND RETURN RATING AVERAGE
    public double getRating(){
        double rating = 0;
        for(int num: this.ratings){
            rating += num;
        }
        rating /= 10;
        return rating;
    }

    // FILL RATINGS CUZ THIS APP ISN'T REAL
    public void fudgeRatings(){
        for(int i = 0; i < 10; i++){
            ratings.offer(ran.nextInt(1,6));
        }
    }

    public ArrayBlockingQueue<Integer> getRatings(){
        return ratings;
    }

    public Order getOrder(){
        return this.order;
    }

    public void setOrder(Order order){
        this.order = order;
    }
}
