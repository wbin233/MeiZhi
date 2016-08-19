package com.example.dell_pc.meizhi.Model;

/**
 * Created by wbin on 2016/8/16.
 */
public class MeizhiModel {
    String url;
    String who;

    public MeizhiModel(String url, String who) {
        this.url = url;
        this.who = who;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
