package cn.dbdj1201.itravel.domain;

import java.io.Serializable;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:16
 **/
public class Seller implements Serializable {
    private Integer sid;        //商家id
    private String sname;       //商家姓名
    private String consphone;   //商家电话
    private String address;     //商家地址

    public Seller() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
