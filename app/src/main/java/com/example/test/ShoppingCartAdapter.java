package com.example.test;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private final List<ItemModel> cartItems; // List of items in the shopping cart
    private final ShoppingCart shoppingCart; // Shopping cart object to manage items and quantities

    // Constructor to initialize the adapter with cart items and shopping cart
    public ShoppingCartAdapter(List<ItemModel> cartItems, ShoppingCart shoppingCart) {
        this.cartItems = cartItems;
        this.shoppingCart = shoppingCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = cartItems.get(position); // Get the item at the current position
        int quantity = shoppingCart.getItemQuantity(item); // Get the quantity of the item from the shopping cart

        // Set the image, name, and quantity for the item
        holder.itemImage.setImageResource(item.getImageResId());
        holder.itemName.setText(item.getItemName());
        holder.quantityEditText.setText(String.valueOf(quantity));

        // Set click listener for the decrease button
        holder.decreaseButton.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(holder.quantityEditText.getText().toString());
            if (currentQuantity > 1) {
                shoppingCart.setItemQuantity(item, currentQuantity - 1); // Decrease the quantity in the shopping cart
                holder.quantityEditText.setText(String.valueOf(currentQuantity - 1)); // Update the quantity displayed
            } else {
                cartItems.remove(position); // Remove the item from the cart if quantity is 1
                notifyItemRemoved(position); // Notify the RecyclerView that the item has been removed
            }
        });

        // Set click listener for the increase button
        holder.increaseButton.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(holder.quantityEditText.getText().toString());
            shoppingCart.setItemQuantity(item, currentQuantity + 1); // Increase the quantity in the shopping cart
            holder.quantityEditText.setText(String.valueOf(currentQuantity + 1)); // Update the quantity displayed
        });

        // Set text change listener for the quantity EditText
        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    int newQuantity = Integer.parseInt(s.toString());
                    shoppingCart.setItemQuantity(item, newQuantity); // Update the quantity in the shopping cart
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size(); // Return the number of items in the cart
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView decreaseButton;
        EditText quantityEditText;
        TextView increaseButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            decreaseButton = itemView.findViewById(R.id.decreaseButton);
            quantityEditText = itemView.findViewById(R.id.quantityEditText);
            increaseButton = itemView.findViewById(R.id.increaseButton);
        }
    }
}