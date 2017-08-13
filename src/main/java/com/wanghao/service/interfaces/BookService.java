package com.wanghao.service.interfaces;

import com.wanghao.db.model.BookInfoModel;

import java.util.List;

/**
 * Created by wanghao on 8/13/17.
 */
public interface BookService {
    List<BookInfoModel> listAll();
}
