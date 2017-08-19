package com.wanghao.db.dao;

import com.wanghao.db.model.UserBookWatchModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanghao on 8/19/17.
 */
public interface UserBookWatchDao {
    UserBookWatchModel findById(@Param("id") long id);

    List<UserBookWatchModel> queryAll();

    void deleteById(@Param("id") long id);
}
