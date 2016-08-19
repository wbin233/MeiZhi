package com.example.dell_pc.meizhi.Model;

import java.util.List;

/**
 * Created by wbin on 2016/8/17.
 */
public class MeizhiResult {
    int count;
    String error;
    List<MeizhiModel> results;

    public MeizhiResult(int count, String error, List<MeizhiModel> results) {
        this.count = count;
        this.error = error;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<MeizhiModel> getResults() {
        return results;
    }

    public void setResults(List<MeizhiModel> results) {
        this.results = results;
    }
}
