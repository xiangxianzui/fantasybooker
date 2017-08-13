package com.wanghao.db.dao;

import com.wanghao.db.model.BookInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanghao on 8/13/17.
 */
public interface BookInfoDao {
    int save(BookInfoModel bookInfoModel);

    BookInfoModel findById(@Param("id") long id);

    List<BookInfoModel> queryAll();
}
