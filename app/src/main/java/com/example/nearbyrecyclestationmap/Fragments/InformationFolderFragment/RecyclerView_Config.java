package com.example.nearbyrecyclestationmap.Fragments.InformationFolderFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyrecyclestationmap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Config {
    public Context mContext;
    public ItemMaterialAdapter mIMAdapter;

    public void setConfig(RecyclerView view, Context context, ArrayList<ItemMaterial> Materials, List<String> keys){
        mContext = context;
        mIMAdapter = new ItemMaterialAdapter(Materials,keys);
        view.setLayoutManager(new LinearLayoutManager(context));
        view.setAdapter(mIMAdapter);
    }

    class ItemMaterialView extends RecyclerView.ViewHolder{
        private ImageView iImage;
        private TextView iName;

        private String iDesc;
        private String iCategory;
        private String iUrl;
        private String key;

        public ItemMaterialView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.information_item_view,parent,false));

            iImage = itemView.findViewById(R.id.iv_img);
            iName = itemView.findViewById(R.id.iv_name);

        }

        public void bind(ItemMaterial item, String key){
            Picasso.get().load(item.getImg()).into(iImage);
            iName.setText(item.getName());

            this.iDesc = item.getDesc();
            this.iCategory = item.getCategory();
            this.iUrl = item.getImg();
            this.key = key;
        }

    }

    class ItemMaterialAdapter extends RecyclerView.Adapter<ItemMaterialView>{
        private ArrayList<ItemMaterial> MaterialList;
        private List<String> mKeys;

        public ItemMaterialAdapter(ArrayList<ItemMaterial> list, List<String> keys){
            this.MaterialList = list;
            this.mKeys = keys;
        }


        @NonNull
        @Override
        public ItemMaterialView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemMaterialView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemMaterialView holder, @SuppressLint("RecyclerView") int position) {
            holder.bind(MaterialList.get(position), mKeys.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, MaterialPage.class).putExtra("itemMaterial",MaterialList.get(position)));
                }
            });

        }

        @Override
        public int getItemCount() {
            return MaterialList.size();
        }

        public void filterList(ArrayList<ItemMaterial> filteredList){
            MaterialList = filteredList;
            notifyDataSetChanged();
        }

    }
}
