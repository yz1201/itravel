package cn.dbdj1201.itravel.domain;

import java.io.Serializable;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:16
 **/
public class RouteImage implements Serializable {
    private Integer rgid;       //商品图片的id
    private Integer rid;        //旅游商品id
    private String bigPic;      //详情商品大图
    private String smallPic;    //详情商品小图

    public RouteImage() {
    }

    public Integer getRgid() {
        return rgid;
    }

    public void setRgid(Integer rgid) {
        this.rgid = rgid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    @Override
    public String toString() {
        return "RouteImage{" +
                "rgid=" + rgid +
                ", rid=" + rid +
                ", bigPic='" + bigPic + '\'' +
                ", smallPic='" + smallPic + '\'' +
                '}';
    }
}
