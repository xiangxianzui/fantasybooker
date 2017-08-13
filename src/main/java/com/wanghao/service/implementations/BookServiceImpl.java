package com.wanghao.service.implementations;

import com.wanghao.db.dao.BookInfoDao;
import com.wanghao.db.model.BookInfoModel;
import com.wanghao.service.interfaces.BookService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanghao on 8/13/17.
 */
@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    @Resource
    private BookInfoDao bookInfoDao;

    @Override
    public List<BookInfoModel> listAll() {
        return bookInfoDao.queryAll();
    }

    @Override
    public List<BookInfoModel> listPagination(int limit, int offset) {
        if(limit<0 || offset<0){
            return null;
        }
        return bookInfoDao.queryPagination(limit, offset);
    }

    @Override
    public int getBookCount() {
        return bookInfoDao.getCount();
    }
}
