package cn.dbdj1201.itravel.domain;

import java.io.Serializable;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:25
 **/
public class Favorite implements Serializable {

    private Integer rid;    //收藏的路线id
    private String date;    //收藏日期
    private Integer uid;    //所属用户id

    public Favorite() {
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "rid=" + rid +
                ", date='" + date + '\'' +
                ", uid=" + uid +
                '}';
    }
}
