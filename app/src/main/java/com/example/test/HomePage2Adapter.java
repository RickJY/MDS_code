package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
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
    private final List<ItemModel> itemList; // List of items to be displayed

    public HomePage2Adapter(List<ItemModel> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the current item
        ItemModel item = itemList.get(position);

        // Set item image and text
        holder.imageView.setImageResource(item.getImageResId());
        holder.textView.setText(item.getItemName());

        // Set button click listener
        holder.button.setOnClickListener(v -> {
            int originalIndex = item.getOriginalIndex(); // Retrieve the original index of the item
            Context context = holder.itemView.getContext(); // Get the context
            String[] toasts = context.getResources().getStringArray(R.array.option_toasts); // Retrieve string array from resources

            // Show a toast message based on the original index
            if (originalIndex < toasts.length) {
                Toast.makeText(context, toasts[originalIndex], Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Selected item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(List<ItemModel> filteredList) {
        itemList.clear(); // Clear the current list
        itemList.addAll(filteredList); // Add all filtered items
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView; // Image view for displaying item image
        TextView textView; // Text view for displaying item name
        Button button; // Button for user interaction


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage); // Initialize image view
            textView = itemView.findViewById(R.id.itemText); // Initialize text view
            button = itemView.findViewById(R.id.itemButton); // Initialize button
        }
    }
}
