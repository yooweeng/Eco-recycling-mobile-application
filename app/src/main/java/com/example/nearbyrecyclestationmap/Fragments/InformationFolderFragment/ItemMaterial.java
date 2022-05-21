package com.example.nearbyrecyclestationmap.Fragments.InformationFolderFragment;

import java.io.Serializable;

public class ItemMaterial implements Serializable {

    private String Name;
    private String Desc;
    private String Category;
    private String img;

    public ItemMaterial() {

    }

    public ItemMaterial(String Name, String Desc, String Category, String img) {
        this.Name = Name;
        this.Desc = Desc;
        this.Category = Category;
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
