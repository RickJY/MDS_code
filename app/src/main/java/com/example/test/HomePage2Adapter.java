package com.example.test;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomePage2Adapter extends RecyclerView.Adapter<HomePage2Adapter.ViewHolder> {
    private final List<ItemModel> itemList; // List of items to display in the RecyclerView
    private final ShoppingCart shoppingCart; // Shopping cart object to manage added items

    // Constructor to initialize the adapter with item list and shopping cart
    public HomePage2Adapter(List<ItemModel> itemList, ShoppingCart shoppingCart) {
        this.itemList = itemList;
        this.shoppingCart = shoppingCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = itemList.get(position); // Get the item at the current position

        // Set the image and text for the item
        holder.imageView.setImageResource(item.getImageResId());
        holder.textView.setText(item.getItemName());

        // Set click listener for the "get me" button
        holder.button.setOnClickListener(v -> {
            Resources res = holder.itemView.getContext().getResources();
            String[] messages = res.getStringArray(R.array.option_toasts); // Get messages from resources
            StringBuilder message = new StringBuilder();
            for (String s : messages) {
                message.append(s).append("\n"); // Append each message with a newline
            }
            Toast.makeText(holder.itemView.getContext(), message.toString(), Toast.LENGTH_SHORT).show(); // Show messages in a Toast
        });

        // Set click listener for the "add to cart" button
        holder.addToCartButton.setOnClickListener(v -> {
            shoppingCart.addItem(item); // Add the item to the shopping cart
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size(); // Return the number of items in the list
    }

    // Method to update the item list with filtered results
    public void setFilteredList(List<ItemModel> filteredList) {
        itemList.clear(); // Clear the current item list
        itemList.addAll(filteredList); // Add the filtered items to the list
        notifyDataSetChanged(); // Notify the RecyclerView that the data has changed
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Button button;
        Button addToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            textView = itemView.findViewById(R.id.itemText);
            button = itemView.findViewById(R.id.itemButton);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}