package com.wanghao.controller.bean;

import com.wanghao.db.model.BookInfoModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghao on 8/20/17.
 * 购物车容器
 */
public class Cart {
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    //向购物车添加商品
    public void addCartItem(CartItem item){
        if(item == null){
            return;
        }
        boolean isExist = false;
        for (CartItem ci : cartItems){
            if(ci.equals(item)){
                ci.setCount(ci.getCount() + item.getCount());
                isExist = true;
                break;
            }
        }
        if(!isExist){
            cartItems.add(item);
        }
    }

    //购物车总价
    public BigDecimal getTotalPrice(){
        BigDecimal result = BigDecimal.ZERO;
        for (CartItem ci : cartItems){
            BookInfoModel book = ci.getBook();
            BigDecimal count = BigDecimal.valueOf(ci.getCount());
            result = result.add(book.getPrice().multiply(book.getDiscount()).multiply(count));
        }
        return result;
    }
}
