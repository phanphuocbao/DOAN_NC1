package bao.dev.doan_nc1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import bao.dev.doan_nc1.api.ApiLayAnh;
import bao.dev.doan_nc1.interfaces.LayAnhVe;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe {
    ImageView imgAnh;
    ArrayList<String> arrUrlAnh;
    int soTrang, soTrangDangDoc;
    TextView txvSoTrang;
    Button btnnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhxa();
        setUp();
        setClick();
        new ApiLayAnh(this).execute();
    }
    public void init(){
//        arrUrlAnh = new ArrayList<>();
//        arrUrlAnh.add("https://i221.ntcdntempv3.com/data/images/11306/222547/002-fix.jpg?data=net");
//        soTrangDangDoc = 1;
//        soTrang = arrUrlAnh.size();
    }
    public void anhxa(){
        txvSoTrang = findViewById(R.id.txvSoTrang);
        imgAnh = findViewById(R.id.imgAnh);
        btnnext = findViewById(R.id.btnnext);
    }
    public void setUp(){
//        docTheoTrang(0);
    }
    public void setClick(){
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (soTrangDangDoc >=soTrang){
                    Toast.makeText(DocTruyenActivity.this, "Đã đến ảnh cuối cùng", Toast.LENGTH_SHORT).show();
//                    soTrangDangDoc = soTrang;
                }else {
                    right(v);
                }
            }
        });
    }

    public void right(View view){
        docTheoTrang(1);
    }
    public void left(View view){
        docTheoTrang(-1);
    }

    private void docTheoTrang(int i){
        soTrangDangDoc = soTrangDangDoc +i;
        if (soTrangDangDoc == 0){
            soTrangDangDoc = 1;

        }
        if (soTrangDangDoc >soTrang){
            soTrangDangDoc = soTrang;
        }
        txvSoTrang.setText(soTrangDangDoc +" / "+soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc-1)).into(imgAnh);
    }
    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        arrUrlAnh = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(data);
            for(int i = 0 ; i<arr.length(); i++){
                arrUrlAnh.add(arr.getString(i));
            }
            soTrangDangDoc = 1;
            soTrang = arrUrlAnh.size();
            docTheoTrang(0);
        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {

    }
}