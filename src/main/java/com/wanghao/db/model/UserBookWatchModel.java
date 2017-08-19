package com.wanghao.db.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wanghao on 8/19/17.
 */
public class UserBookWatchModel {
    private long id;

    private long userId;

    private long bookId;

    private BigDecimal price;

    private int amount;

    private BigDecimal discount;

    private Date createTime;

    private int watchStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getWatchStatus() {
        return watchStatus;
    }

    public void setWatchStatus(int watchStatus) {
        this.watchStatus = watchStatus;
    }
}
