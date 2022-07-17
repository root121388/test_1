package com.exam_2.pojo;

import org.springframework.stereotype.Component;

import java.util.List;
//分页查询数据单元
@Component("page")
public class Page {
    private Integer totalCount;
    private List<User> rows;

    public Page() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<User> getRows() {
        return rows;
    }

    public void setRows(List<User> rows) {
        this.rows = rows;
    }
}
