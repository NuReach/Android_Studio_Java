package com.example.recycleadapter;

import java.io.Serializable;

public class Location implements Serializable {
    public String title;
    public String subTitle;

    public int img;

    public Location ( String title, String subTitle, int img){
        this.title = title;
        this.subTitle = subTitle;
        this.img = img;
    }
}
