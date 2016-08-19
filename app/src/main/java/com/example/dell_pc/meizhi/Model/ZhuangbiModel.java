package com.example.dell_pc.meizhi.Model;

/**
 * Created by wbin on 2016/8/16.
 */
public class ZhuangbiModel {
    String description;
    String image_url;

    public ZhuangbiModel(String description, String image_url) {
        this.description = description;
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
