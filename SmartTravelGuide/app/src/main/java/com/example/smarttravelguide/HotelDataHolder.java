package com.example.smarttravelguide;

public class HotelDataHolder {
    String name,desc,imageUrl;

    HotelDataHolder(){

    }
    public HotelDataHolder(String name, String desc, String imageUrl) {
        this.name = name;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
