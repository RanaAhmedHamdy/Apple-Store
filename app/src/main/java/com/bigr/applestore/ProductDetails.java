package com.bigr.applestore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigr.applestore.models.Product;
import com.squareup.picasso.Picasso;

public class ProductDetails extends AppCompatActivity {

    private TextView age;
    private TextView material;
    private TextView price;
    private ListView colors;
    private ListView sizes;
    private ImageView productImage;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        initialize();

        product = (Product) getIntent().getSerializableExtra("product");

        setData();
    }

    private void initialize() {
        age = (TextView) findViewById(R.id.age);
        material = (TextView) findViewById(R.id.material);
        price = (TextView) findViewById(R.id.price);
        colors = (ListView) findViewById(R.id.colors_list);
        sizes = (ListView) findViewById(R.id.sizes_list);
        productImage = (ImageView) findViewById(R.id.product_image);
    }

    private void setData() {
        age.setText(product.getAge());
        material.setText(product.getMaterial());
        price.setText(product.getPrice());
        Picasso.with(getBaseContext()).load(product.getImage()).into(productImage);

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, product.getColors());

        // Assign adapter to ListView
        colors.setAdapter(colorAdapter);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, product.getSizes());

        // Assign adapter to ListView
        sizes.setAdapter(sizeAdapter);
    }
}
