package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuantityAdapter extends RecyclerView.Adapter<QuantityAdapter.ViewHolder> {

    private static final int QUANTITY_RANGE = 10; // Constant defining the range of quantities to display

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the text of the TextView to the quantity value (position + 1)
        holder.quantityTextView.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return QUANTITY_RANGE; // Return the total number of items to display
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView quantityTextView; // TextView to display the quantity

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find the TextView in the item layout
            quantityTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}