package com.wanghao.task;

import com.wanghao.common.Constant;
import com.wanghao.db.dao.BookInfoDao;
import com.wanghao.db.model.BookInfoModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

/**
 * Created by wanghao on 8/14/17.
 * 查询book_info表，每次从offset开始取limit条数据
 */
@Service
public class SearchBookTask implements Callable<List<BookInfoModel>>{
    private static final Logger logger = Logger.getLogger(SearchBookTask.class);

    @Resource
    private BookInfoDao bookInfoDao;

    private String searchword;

    private int limit;

    private int offset;

    public String getSearchword() {
        return searchword;
    }

    public void setSearchword(String searchword) {
        this.searchword = searchword;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    /*    public SearchBookTask(String searchword, int limit, int offset) {
        this.searchword = searchword;
        this.limit = limit;
        this.offset = offset;
    }*/

    @Override
    public List<BookInfoModel> call() throws Exception {
        logger.info(this.searchword +" " + this.limit +" "+ this.offset);
        List<BookInfoModel> books = bookInfoDao.queryBySearchword(searchword, limit, offset);
        logger.info(books.size());
        if(CollectionUtils.isEmpty(books)){
            return null;
        }

        return books;
    }
}
