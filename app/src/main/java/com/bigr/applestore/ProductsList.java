package com.bigr.applestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bigr.applestore.models.GridItem;
import com.bigr.applestore.models.Product;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class ProductsList extends AppCompatActivity {

    private GridView mGridView;
    private GridViewAdapter mGridAdapter;
    private ArrayList<GridItem> mGridData;
    private String mainCategory;
    private String subCategory;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        mGridView = (GridView) findViewById(R.id.gridView);

        //Initialize with empty data
        mGridData = new ArrayList<>();
        products = new ArrayList<>();
        mGridAdapter = new GridViewAdapter(this, R.layout.grid_item, mGridData);
        mGridView.setAdapter(mGridAdapter);

        mainCategory = getIntent().getExtras().get("main_category").toString();
        subCategory = getIntent().getExtras().get("subcategory").toString();

        Firebase ref = new Firebase("https://appleluran.firebaseio.com/" + mainCategory + "/" + subCategory + "/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                mGridData.clear();
                products.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                    products.add(product);
                    System.out.println(product.getImage() + " - " + product.getSizes().get(0));
                    GridItem item = new GridItem(product.getPrice(), product.getImage());
                    mGridData.add(item);
                }
                mGridView.setAdapter(mGridAdapter);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), ProductDetails.class);
                intent.putExtra("product", products.get(position));
                startActivity(intent);
            }
        });
    }
}
