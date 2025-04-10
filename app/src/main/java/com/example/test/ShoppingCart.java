package com.example.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Parcelable {
    private Map<ItemModel, Integer> itemQuantities; // Map to store items and their quantities

    // Constructor to initialize the ShoppingCart object
    public ShoppingCart() {
        itemQuantities = new HashMap<>();
    }

    // Method to add an item to the shopping cart
    public void addItem(ItemModel item) {
        itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1); // Increment quantity or add with quantity 1
    }

    // Method to get a list of items in the shopping cart
    public List<ItemModel> getCartItems() {
        return new ArrayList<>(itemQuantities.keySet()); // Return a list of item keys
    }

    // Method to get the quantity of a specific item in the cart
    public int getItemQuantity(ItemModel item) {
        return itemQuantities.getOrDefault(item, 0); // Return quantity or 0 if item not found
    }

    // Method to set the quantity of a specific item in the cart
    public void setItemQuantity(ItemModel item, int quantity) {
        if (quantity > 0) {
            itemQuantities.put(item, quantity); // Update quantity if greater than 0
        } else {
            itemQuantities.remove(item); // Remove item if quantity is 0 or less
        }
    }

    // Method to clear all items from the shopping cart
    public void clearCart() {
        itemQuantities.clear(); // Clear the map of items and quantities
    }

    // Constructor to create a ShoppingCart object from a Parcel
    protected ShoppingCart(Parcel in) {
        int size = in.readInt(); // Read the size of the map
        itemQuantities = new HashMap<>();
        for (int i = 0; i < size; i++) {
            ItemModel item = in.readParcelable(ItemModel.class.getClassLoader()); // Read the item
            int quantity = in.readInt(); // Read the quantity
            itemQuantities.put(item, quantity); // Add item and quantity to the map
        }
    }

    // Creator constant to create ShoppingCart objects from Parcel
    public static final Creator<ShoppingCart> CREATOR = new Creator<ShoppingCart>() {
        @Override
        public ShoppingCart createFromParcel(Parcel in) {
            return new ShoppingCart(in);
        }

        @Override
        public ShoppingCart[] newArray(int size) {
            return new ShoppingCart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0; // Indicates no special kinds of objects will be in the Parcel
    }

    // Method to write the ShoppingCart object to a Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemQuantities.size()); // Write the size of the map
        for (Map.Entry<ItemModel, Integer> entry : itemQuantities.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags); // Write the item
            dest.writeInt(entry.getValue()); // Write the quantity
        }
    }
}