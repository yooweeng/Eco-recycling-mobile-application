package com.example.nearbyrecyclestationmap.Fragments.InformationFolderFragment;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseConnector {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private static ArrayList<ItemMaterial> Recyclables = new ArrayList<>();
    private static ArrayList<ItemMaterial> NonRecyclables = new ArrayList<>();

    public FirebaseConnector(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Information");
    }

    public interface RDataStatus{
        void DataIsLoaded(ArrayList<ItemMaterial> recyclable, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public interface NRDataStatus{
        void DataIsLoaded(ArrayList<ItemMaterial> nonrecyclable, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readRecyclables(final RDataStatus dataStatus){
        reference.child("Recyclables").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Recyclables.clear();
                List<String> Keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()){
                    Keys.add(keyNode.getKey());
                    ItemMaterial item = keyNode.getValue(ItemMaterial.class);
                    Recyclables.add(item);
                    System.out.println(item.getName() + "\n");
                }
                dataStatus.DataIsLoaded(Recyclables,Keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readNonRecyclables(final NRDataStatus dataStatus){
        reference.child("NonRecyclables").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NonRecyclables.clear();
                List<String> Keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()){
                    Keys.add(keyNode.getKey());
                    ItemMaterial item = keyNode.getValue(ItemMaterial.class);
                    NonRecyclables.add(item);
                }
                dataStatus.DataIsLoaded(NonRecyclables,Keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
