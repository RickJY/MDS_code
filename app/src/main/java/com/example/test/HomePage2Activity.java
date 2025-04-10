package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomePage2Activity extends AppCompatActivity {
    private List<ItemModel> itemList; // List to store all items
    private List<ItemModel> filteredList; // List to store filtered items
    private HomePage2Adapter itemAdapter; // Adapter for the RecyclerView
    private ShoppingCart shoppingCart; // Shopping cart object to manage items

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_2);

        // Attempt to retrieve the ShoppingCart object from the Intent.
        shoppingCart = getIntent().getParcelableExtra("cart");
        // If no ShoppingCart object is found in the Intent, create a new one.
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }

        // Initialize the SearchView.
        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Do nothing when the query is submitted.
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText); // Filter the item list based on the new text.
                return false;
            }
        });

        // Initialize the RecyclerView.
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list of all items.
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

        // Initially, the filtered list is the same as the full item list.
        filteredList = new ArrayList<>(itemList);

        // Initialize the adapter and set it to the RecyclerView.
        itemAdapter = new HomePage2Adapter(filteredList, shoppingCart);
        recyclerView.setAdapter(itemAdapter);

        // Initialize the BottomNavigationView.
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_home); // Set the default selected item.

        // Set up the listener for BottomNavigationView item clicks.
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true; // Stay on the home page.
            } else if (itemId == R.id.nav_cart) {
                // Navigate to the ShoppingCartActivity.
                Intent intent = new Intent(HomePage2Activity.this, ShoppingCartActivity.class);
                intent.putExtra("cart", shoppingCart); // Pass the ShoppingCart object to the Intent.
                startActivity(intent);
                return true;
            } else {
                return false; // Do nothing for other items.
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Attempt to retrieve the ShoppingCart object from the Intent when the activity is relaunched.
        ShoppingCart cart = intent.getParcelableExtra("cart");
        if (cart != null) {
            shoppingCart = cart; // Update the member variable shoppingCart.
        }
    }

    // Filter the item list based on the search text.
    private void filterList(String text) {
        if (text.isEmpty()) {
            filteredList = new ArrayList<>(itemList); // If the text is empty, show all items.
        } else {
            List<ItemModel> tempList = new ArrayList<>();
            for (ItemModel item : itemList) {
                if (item.getItemName().toLowerCase().contains(text.toLowerCase())) {
                    tempList.add(item); // Add items that match the search text.
                }
            }
            filteredList = tempList;
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show(); // Show a toast if no items match.
        }

        itemAdapter.setFilteredList(filteredList); // Update the adapter with the filtered list.
    }
}