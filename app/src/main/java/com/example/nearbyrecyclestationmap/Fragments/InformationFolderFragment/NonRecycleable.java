package com.example.nearbyrecyclestationmap.Fragments.InformationFolderFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyrecyclestationmap.R;

import java.util.ArrayList;
import java.util.List;

public class NonRecycleable extends AppCompatActivity {

    private ArrayList<ItemMaterial> itemMaterials;
    private RecyclerView recyclerView;
    private RecyclerView_Config.ItemMaterialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_fragment_non_recycleable);

        recyclerView = findViewById(R.id.recyclable_recyclerview);
        EditText editText = findViewById(R.id.recycle_searchbar);

        itemMaterials = new ArrayList<>();

        new FirebaseConnector().readNonRecyclables(new FirebaseConnector.NRDataStatus() {
            @Override
            public void DataIsLoaded(ArrayList<ItemMaterial> nonrecyclable, List<String> keys) {
                RecyclerView_Config recyclerView_config = new RecyclerView_Config();
                recyclerView_config.setConfig(recyclerView,NonRecycleable.this,nonrecyclable,keys);
                itemMaterials = nonrecyclable;
                adapter = (RecyclerView_Config.ItemMaterialAdapter) recyclerView.getAdapter();
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text){
        ArrayList<ItemMaterial> filteredList = new ArrayList<>();

        for (ItemMaterial item : itemMaterials){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
}