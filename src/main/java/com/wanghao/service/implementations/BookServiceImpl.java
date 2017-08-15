package com.wanghao.service.implementations;

import com.wanghao.db.dao.BookInfoDao;
import com.wanghao.db.model.BookInfoModel;
import com.wanghao.service.interfaces.BookService;
import com.wanghao.task.SearchBookTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanghao on 8/13/17.
 */
@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImpl.class);

    @Resource
    private BookInfoDao bookInfoDao;

    @Autowired
    private SearchBookTask searchBookTask;

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

    @Override
    public List<BookInfoModel> searchAsync(String searchword, int limit, int offset) {
        searchBookTask.setSearchword(searchword);
        searchBookTask.setLimit(limit);
        searchBookTask.setOffset(offset);
        FutureTask<List<BookInfoModel>> futureTask = new FutureTask<List<BookInfoModel>>(searchBookTask);
        List<BookInfoModel> result = null;
        new Thread(futureTask).start();
        try {
            // 取得结果，同时设置超时执行时间为1秒。同样可以用future.get()，不设置执行超时时间取得结果
            result = futureTask.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            futureTask.cancel(true);
        } catch (ExecutionException e) {
            logger.error(e.getMessage());
            futureTask.cancel(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            futureTask.cancel(true);
            // 超时后，进行相应处理
        } finally {
            logger.info("finally");
            if(result != null){
                logger.info("futuretask返回结果的size："+result.size());
            }
            else {
                logger.info("futuretast返回为空");
            }
        }
        return null;
    }

    @Override
    public List<BookInfoModel> search(String searchword, int limit, int offset) {
        return bookInfoDao.queryBySearchword(searchword, limit, offset);
    }


}
