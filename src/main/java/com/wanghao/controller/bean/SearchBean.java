package com.wanghao.controller.bean;

/**
 * Created by wanghao on 8/14/17.
 */
public class SearchBean {
    private String searchword;

    private int searchpage;

    public String getSearchword() {
        return searchword;
    }

    public void setSearchword(String searchword) {
        this.searchword = searchword;
    }

    public int getSearchpage() {
        return searchpage;
    }

    public void setSearchpage(int searchpage) {
        this.searchpage = searchpage;
    }
}
