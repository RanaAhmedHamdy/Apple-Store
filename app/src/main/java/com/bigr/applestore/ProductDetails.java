package com.bigr.applestore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bigr.applestore.models.Product;

public class ProductDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Product p = (Product) getIntent().getSerializableExtra("product");
    }
}