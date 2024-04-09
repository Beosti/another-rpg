package main;

import main.object.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that handles everything inventory related
 */
public class Inventory {

    private HashMap<Item, Integer> items = new HashMap<>();
    private int capacity = 20;



    // Method to add an item to the first available slot in the inventory
    public void addItem(Item item) {
        for (int i = 0; i < capacity; i++) {
            if (!items.containsValue(i)) {
                items.put(item, i);
                return;
            }
        }
        System.out.println("Inventory is full. Cannot add item.");
    }

    // Method to remove an item from the inventory
    public void removeItem(Item item) {
        if (items.containsKey(item)) {
            items.remove(item);
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    // Method to get the total count of items in the inventory
    public int getItemCount() {
        int count = 0;
        for (Integer index : items.values()) {
            count++;
        }
        return count;
    }


    public HashMap<Item, Integer> getItems()
    {
        return this.items;
    }

    public void setItems(HashMap<Item, Integer> items)
    {
        this.items = items;
    }

    public Integer getCapacity()
    {
        return this.capacity;
    }
    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }


}
