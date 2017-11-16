package com.wanghao.controller.bean;

import com.wanghao.db.model.BookInfoModel;

/**
 * Created by wanghao on 8/20/17.
 * 购物车里的商品
 */
public class CartItem {

    //商品
    private BookInfoModel book;

    //该商品在购物车里的数量
    private int count;

    public BookInfoModel getBook() {
        return book;
    }

    public void setBook(BookInfoModel book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        if (!book.equals(cartItem.book)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return book.hashCode();
    }
}
