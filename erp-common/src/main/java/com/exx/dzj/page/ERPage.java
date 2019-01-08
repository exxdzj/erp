package com.exx.dzj.page;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Author
 * @Date 2019/1/7 0007 9:10
 * @Description
 */
public class ERPage<T> implements Serializable {
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -3307732924775222549L;

    //当前页
    private int pageNo;
    //每页的数量
    private int pageSize;
    //总记录数
    private Long totalSize;
    //结果集
    private List<T> list;

    public ERPage(){

    }

    public ERPage(List<T> list){
        //super(list);
        this(list, 8);
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public ERPage (List<T> list, int navigatePages) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNo = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.list = page;
            this.totalSize = page.getTotal();
        }
    }

    public int getPageNo() {
        return pageNo;
    }
    public void setPageNum(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public Long getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
}
