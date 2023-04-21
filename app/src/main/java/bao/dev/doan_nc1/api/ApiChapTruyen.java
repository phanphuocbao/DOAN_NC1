package bao.dev.doan_nc1.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import bao.dev.doan_nc1.interfaces.LayChapVe;
import bao.dev.doan_nc1.interfaces.LayTruyenVe;

public class ApiChapTruyen extends AsyncTask<Void,Void,Void> {
    String data;
    LayChapVe layChapVe;
    String idTruyen;

    public ApiChapTruyen(LayChapVe layChapVe, String idTruyen) {
        this.layChapVe = layChapVe;
        this.idTruyen = idTruyen;
        this.layChapVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
//                .url("https://api.jsonserve.com/DRU8QQ")
                .url("http://phanphuocbao-001-site1.atempurl.com/laychapp.php?id="+idTruyen)
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        if (data == null){
            this.layChapVe.biLoi();
        }else {
            this.layChapVe.ketThuc(data);
        }
    }
}
