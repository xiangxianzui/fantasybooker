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

    //分页查询
    List<BookInfoModel> queryPagination(@Param("limit") int limit, @Param("offset") int offset);

    //得到book_info表一共有多少条数据
    int getCount();

    //根据searchword模糊查询
    List<BookInfoModel> queryBySearchword(@Param("searchword") String searchword, @Param("limit") int limit, @Param("offset") int offset);
}
