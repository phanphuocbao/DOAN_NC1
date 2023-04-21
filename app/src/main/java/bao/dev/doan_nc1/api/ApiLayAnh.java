package bao.dev.doan_nc1.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import bao.dev.doan_nc1.interfaces.LayAnhVe;
import bao.dev.doan_nc1.interfaces.LayTruyenVe;

public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data;
    LayAnhVe layAnhVe;

    public ApiLayAnh(LayAnhVe layAnhVe) {
        this.layAnhVe = layAnhVe;
        this.layAnhVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.jsonserve.com/vQw0gH")
//                .url("http://phanphuocbao-001-site1.atempurl.com/layTruyen.php")
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
            this.layAnhVe.biLoi();
        }else {
            this.layAnhVe.ketThuc(data);
        }
    }
}
