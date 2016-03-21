package com.bigr.applestore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigr.applestore.models.Product;
import com.squareup.picasso.Picasso;

/**
 * Created by Rana on 3/21/2016.
 */
public class ProductDetailsFragment extends Fragment {

    private TextView age;
    private TextView material;
    private TextView price;
    private ListView colors;
    private ListView sizes;
    private ImageView productImage;
    private Product product;

    public ProductDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_products_details, container, false);

        age = (TextView) rootView.findViewById(R.id.age);
        material = (TextView) rootView.findViewById(R.id.material);
        price = (TextView) rootView.findViewById(R.id.price);
        colors = (ListView) rootView.findViewById(R.id.colors_list);
        sizes = (ListView) rootView.findViewById(R.id.sizes_list);
        productImage = (ImageView) rootView.findViewById(R.id.product_image);

        product = (Product) getActivity().getIntent().getSerializableExtra("product");

        setData();

        return rootView;
    }

    private void setData() {
        age.setText(product.getAge());
        material.setText(product.getMaterial());
        price.setText(product.getPrice());
        Picasso.with(getActivity()).load(product.getImage()).into(productImage);

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, product.getColors());

        // Assign adapter to ListView
        colors.setAdapter(colorAdapter);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, product.getSizes());

        // Assign adapter to ListView
        sizes.setAdapter(sizeAdapter);
    }
}
