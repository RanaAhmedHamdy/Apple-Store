package com.bigr.applestore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SubCategories extends AppCompatActivity {

    private String mainCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);

        mainCategory = getIntent().getExtras().get("main_category").toString();
    }
}
