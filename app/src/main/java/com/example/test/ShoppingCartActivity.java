package com.example.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView; // RecyclerView to display items in the shopping cart
    private ShoppingCartAdapter cartAdapter; // Adapter for the RecyclerView
    private ShoppingCart shoppingCart; // Shopping cart object to manage items
    private BottomNavigationView bottomNavigationView; // Bottom navigation view for navigation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // Retrieve the ShoppingCart object from the Intent.
        shoppingCart = getIntent().getParcelableExtra("cart");
        // If no ShoppingCart object is found in the Intent, create a new one.
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }

        // Initialize the RecyclerView.
        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and set it to the RecyclerView.
        cartAdapter = new ShoppingCartAdapter(shoppingCart.getCartItems(), shoppingCart);
        recyclerView.setAdapter(cartAdapter);

        // Initialize the BottomNavigationView.
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_cart); // Set the default selected item.

        // Set up the listener for BottomNavigationView item clicks.
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                // Navigate to the HomePage2Activity.
                Intent intent = new Intent(ShoppingCartActivity.this, HomePage2Activity.class);
                intent.putExtra("cart", shoppingCart); // Pass the ShoppingCart object to the Intent.
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_cart) {
                return true; // Stay on the shopping cart page.
            } else {
                return false; // Do nothing for other items.
            }
        });
    }
}