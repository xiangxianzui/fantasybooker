package com.wanghao.controller;

import com.alibaba.fastjson.JSONObject;
import com.wanghao.controller.bean.Cart;
import com.wanghao.controller.bean.CartItem;
import com.wanghao.db.model.BookInfoModel;
import com.wanghao.service.interfaces.BookService;
import com.wanghao.util.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wanghao on 8/20/17.
 */
@Controller
@RequestMapping(value = "/shop")
public class ShopController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/shoppingCart", method = RequestMethod.POST)
    public String shoppingCart(@RequestBody String jsonStr, HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        long bookId = Long.valueOf(String.valueOf(jsonObject.get("bookId")));
        int count = (Integer)jsonObject.get("count");
        Cart cart = null;
        //1,获取Cookie中的购物车
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if(StringUtils.equals(Constant.SHOPPING_CART, cookie.getName())){
                    //购物车对象与json字符串互转
                    cart = JSONObject.parseObject(cookie.getValue(), Cart.class);
                    break;
                }
            }
        }
        //2,Cookie中没有购物车, 创建购物车对象
        if (null == cart) {
            cart = new Cart();
        }
        BookInfoModel book = bookService.viewBookDetail(bookId);
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setBook(book);
        cart.addCartItem(cartItem);
        Cookie cookie = new Cookie(Constant.SHOPPING_CART, JSONObject.toJSONString(cart));
        //设置path是可以共享cookie
        cookie.setPath("/");
        //设置Cookie过期时间: -1 表示关闭浏览器失效  0: 立即失效  >0: 单位是秒, 多少秒后失效
        cookie.setMaxAge(-1);
        //5,Cookie写回浏览器
        response.addCookie(cookie);
        return "index";
    }
}
