package com.bigr.applestore.subcategories;

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
import android.widget.ListView;

import com.bigr.applestore.cart.Cart;
import com.bigr.applestore.productslist.ProductsList;
import com.bigr.applestore.R;
import com.bigr.applestore.adapters.CustomListViewAdapter;
import com.bigr.applestore.models.Subcategory;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Rana on 3/21/2016.
 */
public class SubCategoriesFragment extends Fragment {

    private String mainCategory;
    private ArrayList<Subcategory> subcategories;
    private ListView subCategoriesList;

    public SubCategoriesFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_sub_categories, container, false);


        subcategories = new ArrayList<>();
        mainCategory = getActivity().getIntent().getExtras().get("main_category").toString();

        subCategoriesList = (ListView) rootView.findViewById(R.id.subcategories_list);

        Firebase ref = new Firebase("https://appleluran.firebaseio.com/subcategories/" + mainCategory + "/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Subcategory subcategory = postSnapshot.getValue(Subcategory.class);
                    subcategories.add(subcategory);
                }
                CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(),
                        R.layout.subcategories_list_item, subcategories);
                subCategoriesList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        subCategoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProductsList.class);
                intent.putExtra("main_category", mainCategory);
                intent.putExtra("subcategory", subCategoriesList.getItemAtPosition(position).toString().toLowerCase());
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
