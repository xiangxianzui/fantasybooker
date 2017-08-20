package com.wanghao.controller;

import com.alibaba.fastjson.JSONArray;
import com.wanghao.util.Constant;
import com.wanghao.controller.bean.SearchBean;
import com.wanghao.db.model.BookInfoModel;
import com.wanghao.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        int limit = Constant.PAGINATION_LIMIT_SHOW;
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(){
        SearchBean searchBean = new SearchBean();
        searchBean.setSearchpage(1);
        return new ModelAndView("book/search").addObject("searchBean", searchBean);
    }

    /*以json的格式返回对象，需要导入jackson包依赖：jackson-core-asl和jackson-mapper-asl*/
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String search(@ModelAttribute("searchBean") SearchBean searchBean){
        int limit = Constant.PAGINATION_LIMIT;
        String searchword = searchBean.getSearchword();
        int page = searchBean.getSearchpage();
        int offset = ((page-1)*limit);
        List<BookInfoModel> books = bookService.search(searchword, limit, offset);
        String jsonStr = JSONArray.toJSONString(books);
        return jsonStr;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewBookDetail(@PathVariable long id){
        BookInfoModel book = bookService.viewBookDetail(id);
        if(book != null){
            ModelAndView mav = new ModelAndView("book/detail");
            mav.addObject(Constant.CUR_BOOK, book);
            return mav;
        }
        else {
            return new ModelAndView("error/other");
        }
    }

}
