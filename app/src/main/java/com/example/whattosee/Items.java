package com.example.whattosee;

public class Items {
    private String name; // название
    private String discription;  // столица
    private int ItemResource; // ресурс флага

    public Items(String name, String discription, int Res){

        this.name=name;
        this.discription=discription;
        this.ItemResource=Res;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDiscription() {
        return this.discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getItemResource() {
        return this.ItemResource;
    }

    public void setItemResource(int ItemResource) {
        this.ItemResource = ItemResource;
    }
}

