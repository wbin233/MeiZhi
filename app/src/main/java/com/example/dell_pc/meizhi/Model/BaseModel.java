package com.example.dell_pc.meizhi.Model;

/**
 * Created by wbin on 2016/8/19.
 */
public class BaseModel {
    String title;
    String imgUrl;

    public BaseModel(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
