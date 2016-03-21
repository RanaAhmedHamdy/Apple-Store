package com.bigr.applestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Rana on 3/21/2016.
 */
public class SubCategoriesFragment extends Fragment {

    private String mainCategory;
    private String[] subCategories;
    private ListView subCategoriesList;

    public SubCategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_sub_categories, container, false);


        mainCategory = getActivity().getIntent().getExtras().get("main_category").toString();

        subCategoriesList = (ListView) rootView.findViewById(R.id.subcategories_list);

        if(mainCategory.equals("newBorn"))
            subCategories = getResources().getStringArray(R.array.newborn_sub_categories);
        else if(mainCategory.equals("men"))
            subCategories = getResources().getStringArray(R.array.men_sub_categories);
        else if(mainCategory.equals("women"))
            subCategories = getResources().getStringArray(R.array.women_sub_categories);
        else
            subCategories = getResources().getStringArray(R.array.kids_sub_categories);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, subCategories);

        // Assign adapter to ListView
        subCategoriesList.setAdapter(sizeAdapter);

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
}
