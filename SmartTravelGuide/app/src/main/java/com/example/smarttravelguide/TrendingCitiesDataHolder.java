package com.example.smarttravelguide;

public class TrendingCitiesDataHolder {
    TrendingCitiesDataHolder(){

    }

    String name,desc,province,imageUrl;

    public TrendingCitiesDataHolder(String name, String desc, String province, String imageUrl) {
        this.name = name;
        this.desc = desc;
        this.province = province;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
