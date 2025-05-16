package resaurant;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //HASHMAP TO STORE ITEMS AND PRICES
    private HashMap<String, Double> itemPrices;

    //ORDERED LIST FOR INDEXING
    private List<String> itemList;

    public Menu(){

        //FILL BOTH itemPrices AND itemList
        setMenu();
    }

    //ADD MENU ITEMS TO BOTH DATA STRUCTURES
    private void setMenu(){
        itemPrices = new HashMap<>();
        itemList = new ArrayList<>();
        itemPrices.put("Burger", 9.00);
        itemList.add("Burger");
        itemPrices.put("Pizza", 15.00);
        itemList.add("Pizza");
        itemPrices.put("Salad", 8.00);
        itemList.add("Salad");
        itemPrices.put("Coffee", 3.00);
        itemList.add("Coffee");
        itemPrices.put("Soda", 3.00);
        itemList.add("Soda");
        itemPrices.put("Fries", 5.00);
        itemList.add("Fries");
        itemPrices.put("Slaw", 4.00);
        itemList.add("Slaw");
    }

    public void displayMenu() {

        System.out.println("+-----------------------------+");
        System.out.println("|         Menu Items          |");
        System.out.println("+-----------------------------+");

        int i = 1;
        for(String item: itemList){
            System.out.printf("| %d | %-10s: %11.2f |\n", i,item, itemPrices.get(item));
            i++;
        }

        System.out.println("+-----------------------------+");
        System.out.println();

    }


    //RETURN itemPrices HASHMAP FOR PRICE CHECKING
    public double getItemPrice(String item){
        return itemPrices.get(item);
    }


    public HashMap<String, Double> getItemPrices() {
        return itemPrices;
    }

    //RETURN ITEM LIST WITH INDEXS FOR SELECTION
    public List<String> getItemList() {
        return itemList;
    }
}
