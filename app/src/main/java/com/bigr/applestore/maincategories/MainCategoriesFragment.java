package com.bigr.applestore.maincategories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigr.applestore.cart.Cart;
import com.bigr.applestore.R;
import com.bigr.applestore.subcategories.SubCategories;

/**
 * Created by Rana on 3/21/2016.
 */
public class MainCategoriesFragment extends Fragment {

    private ImageView newBorn;
    private ImageView kids;
    private ImageView women;
    private ImageView men;
    private Intent intent;

    public MainCategoriesFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main_categories, container, false);

        newBorn = (ImageView) rootView.findViewById(R.id.new_born_image);
        kids = (ImageView) rootView.findViewById(R.id.kids_image);
        women = (ImageView) rootView.findViewById(R.id.women_image);
        men = (ImageView) rootView.findViewById(R.id.men_image);

        intent = new Intent(getActivity(), SubCategories.class);
        newBorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("main_category", "newBorn");
                startActivity(intent);
            }
        });

        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("main_category", "kids");
                startActivity(intent);
            }
        });

        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("main_category", "women");
                startActivity(intent);
            }
        });

        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("main_category", "men");
                startActivity(intent);
            }
        });

        /*Firebase ref = new Firebase("https://appleluran.firebaseio.com/");
        ArrayList<String> sizes = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        sizes.add("l");
        sizes.add("m");
        colors.add("purple");
        colors.add("white");
        Product p = new Product("1111","1","1","http://res.cloudinary.com/appleluran/image/upload/t2_sx5nu0.jpg ","cotton","F",2,colors,sizes);
        ref.child("kids/t-shirts").push().setValue(p);
        ref.child("women/t-shirts").push().setValue(p);*/
        /*Firebase ref = new Firebase("https://appleluran.firebaseio.com/subcategories");
        Subcategory s = new Subcategory("t-shirts", "v1461580358/shirt_xdnmla.jpg");
        Subcategory s2 = new Subcategory("jeans", "v1461580358/jeans_hj4nly.jpg");
        Subcategory s3 = new Subcategory("shorts", "v1461580357/shorts_ckeqd0.jpg");
        Subcategory s4 = new Subcategory("dress", "v1461580358/dress_j1gvch.jpg");
        ref.child("newBorn").push().setValue(s);
        ref.child("newBorn").push().setValue(s3);

        ref.child("kids").push().setValue(s);
        ref.child("kids").push().setValue(s3);

        ref.child("men").push().setValue(s);
        ref.child("men").push().setValue(s2);

        ref.child("women").push().setValue(s);
        ref.child("women").push().setValue(s4);*/
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
