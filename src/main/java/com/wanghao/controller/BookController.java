package com.wanghao.controller;

import com.wanghao.common.Constant;
import com.wanghao.db.model.BookInfoModel;
import com.wanghao.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wanghao on 8/13/17.
 */

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value="page",required=true) int page){
        int limit = Constant.PAGINATION_LIMIT;
        int totalSize = bookService.getBookCount();
        int pageNum = (totalSize%limit==0) ? totalSize/limit : totalSize/limit+1;
        if(page<=0){
            page = 1;
        }
        if(page>pageNum){
            page = pageNum;
        }
        int offset = ((page-1)*limit)%totalSize;
        List<BookInfoModel> books = bookService.listPagination(limit, offset);
        ModelAndView mav = new ModelAndView("book/index");
        mav.addObject("books", books);
        mav.addObject("page", page);
        mav.addObject("pageNum", pageNum);
        return mav;
    }
}
