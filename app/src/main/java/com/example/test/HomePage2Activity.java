package com.example.test;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomePage2Activity extends AppCompatActivity {
    private List<ItemModel> itemList;  // The original list of items
    private List<ItemModel> filteredList; // The filtered list based on search input
    private HomePage2Adapter itemAdapter; // Adapter for the RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_2);

        // Initialize and configure the SearchView
        // SearchView for filtering the list
        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus(); // Remove focus to prevent the keyboard from appearing on startup
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // We do not handle the search submit separately
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText); // Filter the list as user types
                return false;
            }
        });

        // Initialize and configure the RecyclerView
        // RecyclerView to display the list of items
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // Optimize performance since item size will not change
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Use a vertical linear layout

        // Initialize the item list with predefined items
        //define three elements for recyclerview array
        //First is our image resource path, second is describe, third is index for searching suitable string
        itemList = new ArrayList<>();
        itemList.add(new ItemModel(R.drawable.develop0, "T-shirt", 0));
        itemList.add(new ItemModel(R.drawable.develop1, "Shoe", 1));
        itemList.add(new ItemModel(R.drawable.develop2, "Shoe", 2));
        itemList.add(new ItemModel(R.drawable.develop3, "shoe", 3));
        itemList.add(new ItemModel(R.drawable.develop4, "shoe", 4));
        itemList.add(new ItemModel(R.drawable.develop5, "shoe", 5));
        itemList.add(new ItemModel(R.drawable.develop6, "badminton racket", 6));
        itemList.add(new ItemModel(R.drawable.develop7, "badminton", 7));
        itemList.add(new ItemModel(R.drawable.develop8, "Football", 8));
        itemList.add(new ItemModel(R.drawable.develop9, "Basketball", 9));

        // Initialize filteredList with all items initially
        filteredList = new ArrayList<>(itemList);

        // Set up the adapter with the filtered list
        itemAdapter = new HomePage2Adapter(filteredList);
        recyclerView.setAdapter(itemAdapter);
    }

    /**
     * Filters the list based on the input text from the SearchView.
     *
     * @param text The text entered by the user in the SearchView.
     */
    private void filterList(String text) {
        if (text.isEmpty()) {
            // If search query is empty, reset to the original list
            filteredList = new ArrayList<>(itemList);
        } else {
            // Otherwise, filter the list based on the search query
            List<ItemModel> tempList = new ArrayList<>();
            for (ItemModel item : itemList) {
                // Case-insensitive search for items containing the input text
                if (item.getItemName().toLowerCase().contains(text.toLowerCase())) {
                    tempList.add(item);
                }
            }
            filteredList = tempList;
        }

        // Show a toast message if no items match the search
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }

        // Update the adapter with the new filtered list
        itemAdapter.setFilteredList(filteredList);
    }
}