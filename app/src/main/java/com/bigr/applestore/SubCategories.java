package com.bigr.applestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubCategories extends AppCompatActivity {

    private String mainCategory;
    private String[] subCategories;
    private ListView subCategoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);

        mainCategory = getIntent().getExtras().get("main_category").toString();

        subCategoriesList = (ListView) findViewById(R.id.subcategories_list);

        if(mainCategory.equals("newBorn"))
            subCategories = getResources().getStringArray(R.array.newborn_sub_categories);
        else if(mainCategory.equals("men"))
            subCategories = getResources().getStringArray(R.array.men_sub_categories);
        else if(mainCategory.equals("women"))
            subCategories = getResources().getStringArray(R.array.women_sub_categories);
        else
            subCategories = getResources().getStringArray(R.array.kids_sub_categories);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, subCategories);

        // Assign adapter to ListView
        subCategoriesList.setAdapter(sizeAdapter);

        subCategoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), ProductsList.class);
                intent.putExtra("main_category", mainCategory);
                intent.putExtra("subcategory", subCategoriesList.getItemAtPosition(position).toString().toLowerCase());
                startActivity(intent);
            }
        });
    }
}
