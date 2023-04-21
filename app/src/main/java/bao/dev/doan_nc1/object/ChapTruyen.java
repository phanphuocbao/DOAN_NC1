package bao.dev.doan_nc1.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ChapTruyen implements Serializable {
    private String tenChap, ngayDang;

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public ChapTruyen(){

    }
    public ChapTruyen(String tenChap, String ngayDang) {
        this.tenChap = tenChap;
        this.ngayDang = ngayDang;
    }
    public ChapTruyen (JSONObject o )throws JSONException {
        tenChap = o.getString("tenchap");
        ngayDang = o.getString("ngaynhap");

    }
}
