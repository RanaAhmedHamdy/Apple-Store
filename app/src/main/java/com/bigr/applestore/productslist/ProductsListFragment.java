package com.bigr.applestore.productslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bigr.applestore.cart.Cart;
import com.bigr.applestore.R;
import com.bigr.applestore.adapters.GridViewAdapter;
import com.bigr.applestore.models.GridItem;
import com.bigr.applestore.models.Product;
import com.bigr.applestore.productdetails.ProductDetails;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Rana on 3/21/2016.
 */
public class ProductsListFragment extends Fragment {

    private GridView mGridView;
    private GridViewAdapter mGridAdapter;
    private ArrayList<GridItem> mGridData;
    private String mainCategory;
    private String subCategory;
    private ArrayList<Product> products;

    public ProductsListFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_products_list, container, false);

        mGridView = (GridView) rootView.findViewById(R.id.gridView);

        //Initialize with empty data
        mGridData = new ArrayList<>();
        products = new ArrayList<>();
        mGridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item, mGridData);
        mGridView.setAdapter(mGridAdapter);

        mainCategory = getActivity().getIntent().getExtras().get("main_category").toString();
        subCategory = getActivity().getIntent().getExtras().get("subcategory").toString();

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
                Intent intent = new Intent(getActivity(), ProductDetails.class);
                intent.putExtra("product", products.get(position));
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cart, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.basket) {
            Intent intent = new Intent(getActivity(), Cart.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
