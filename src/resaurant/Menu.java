package resaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    Map<String, Double> menuItems;
    List<String> itemList;

    public Menu(){


        setMenu();
    }

    private void setMenu(){
        menuItems = new HashMap<>();
        itemList = new ArrayList<>();
        menuItems.put("Main 1", 1.00);
        itemList.add("Main 1");
        menuItems.put("Main 2", 1.00);
        itemList.add("Main 2");
        menuItems.put("Main 3", 1.00);
        itemList.add("Main 3");
        menuItems.put("Drink 1", 1.00);
        itemList.add("Drink 1");
        menuItems.put("Drink 2", 1.00);
        itemList.add("Drink 2");
        menuItems.put("Side 1", 1.00);
        itemList.add("Side 1");
        menuItems.put("Side 2", 1.00);
        itemList.add("Side 2");
    }

    public void displayMenu() {

        System.out.println("+-----------------------------+");
        System.out.println("|         Menu Items          |");
        System.out.println("+-----------------------------+");

        int i = 1;
        for(String item: itemList){
            System.out.printf("|#%d  %-10s: %12.2f |\n", i,item, menuItems.get(item));
            i++;
        }


        System.out.println("+-----------------------------+");
        System.out.println();

    }



}
