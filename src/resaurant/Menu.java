package resaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu {

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
        itemPrices.put("Main 1", 1.00);
        itemList.add("Main 1");
        itemPrices.put("Main 2", 1.00);
        itemList.add("Main 2");
        itemPrices.put("Main 3", 1.00);
        itemList.add("Main 3");
        itemPrices.put("Drink 1", 1.00);
        itemList.add("Drink 1");
        itemPrices.put("Drink 2", 1.00);
        itemList.add("Drink 2");
        itemPrices.put("Side 1", 1.00);
        itemList.add("Side 1");
        itemPrices.put("Side 2", 1.00);
        itemList.add("Side 2");
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
