package com.bigr.applestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainCategories extends AppCompatActivity {

    private ImageView newBorn;
    private ImageView kids;
    private ImageView women;
    private ImageView men;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_categories);

        initialize();

        intent = new Intent(getBaseContext(), ProductsList.class);
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
    }

    private void initialize() {
        newBorn = (ImageView) findViewById(R.id.new_born_image);
        kids = (ImageView) findViewById(R.id.kids_image);
        women = (ImageView) findViewById(R.id.women_image);
        men = (ImageView) findViewById(R.id.men_image);
    }
}
