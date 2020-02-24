package cn.dbdj1201.itravel.domain;

import java.io.Serializable;

/**
 * @author tyz1201
 * @datetime 2020-02-24 11:55
 * 分类实体类
 **/
public class Category implements Serializable {
    private Integer cid;  //分类id
    private String cname; //分类名称

    public Category() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
