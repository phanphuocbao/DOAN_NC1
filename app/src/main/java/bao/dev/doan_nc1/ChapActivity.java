package bao.dev.doan_nc1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

import bao.dev.doan_nc1.adapter.ChapTruyenAdapter;
import bao.dev.doan_nc1.api.ApiChapTruyen;
import bao.dev.doan_nc1.interfaces.LayChapVe;
import bao.dev.doan_nc1.object.ChapTruyen;
import bao.dev.doan_nc1.object.TruyenTranh;

public class ChapActivity extends AppCompatActivity implements LayChapVe {
    ImageView imgAnhTruyens;
    TextView txvTenTruyens;
    TruyenTranh truyenTranh;
    ListView lsvDanhSachChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiChapTruyen(this, truyenTranh.getId()).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");
        arrChap = new ArrayList<>();
//        for(int i = 0; i<20; i++){
//            arrChap.add(new ChapTruyen("Chapter " + i,"02-08-2002"));
//        }
        chapTruyenAdapter = new ChapTruyenAdapter(this, 0,arrChap);
    }
    private void anhXa(){
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
    }
    private void setUp(){
        txvTenTruyens.setText(truyenTranh.getTenTruyen());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhTruyens);

//        lsvDanhSachChap.setAdapter(chapTruyenAdapter);
    }
    private void setClick(){
        lsvDanhSachChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChapTruyen chapTruyen = arrChap.get(position);
                Bundle b = new Bundle();
                b.putSerializable("chap",chapTruyen);
                Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
                intent.putExtra("dataC", b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Dang Lay Ve", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for(int i = 0; i< array.length(); i++){
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);
            lsvDanhSachChap.setAdapter(chapTruyenAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void biLoi() {

    }
}