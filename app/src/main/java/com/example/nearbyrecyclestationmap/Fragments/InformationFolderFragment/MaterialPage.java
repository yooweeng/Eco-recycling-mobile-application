package com.example.nearbyrecyclestationmap.Fragments.InformationFolderFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nearbyrecyclestationmap.R;
import com.squareup.picasso.Picasso;

public class MaterialPage extends AppCompatActivity {

    ImageView img;
    TextView name;
    TextView desc;
    TextView category;

    ItemMaterial item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_fragment_material_page);

        img = findViewById(R.id.mp_imageView);
        name = findViewById(R.id.mp_nameView);
        desc = findViewById(R.id.mp_descView);
        category = findViewById(R.id.mp_categoryView);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            item = (ItemMaterial) intent.getSerializableExtra("itemMaterial");
            Picasso.get().load(item.getImg()).into(img);

            name.setText(item.getName());
            desc.setText(item.getDesc());
            category.setText(item.getCategory());

        }
    }
}