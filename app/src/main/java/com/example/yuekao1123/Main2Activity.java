package com.example.yuekao1123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekao1123.adapter.RecyAdapter;
import com.example.yuekao1123.bean.CartBean;
import com.example.yuekao1123.bean.ShopBean;
import com.example.yuekao1123.callBack.ViewCallBack;
import com.example.yuekao1123.presenter.MyPresenter;

/**
 * 杨雪峰
 * 第一步 添加依赖
 */
public class Main2Activity extends AppCompatActivity implements ViewCallBack{

    private RecyclerView recyclerView;
    private TextView total_price;
    private TextView total_num;
    private CheckBox quanxuan;
    private MyPresenter myPresenter;
    private RecyAdapter recyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        total_price = (TextView) findViewById(R.id.total_price);
        total_num = (TextView) findViewById(R.id.total_num);
        quanxuan = (CheckBox) findViewById(R.id.quanxuan);

        quanxuan.setTag(1);

        LinearLayoutManager manager = new LinearLayoutManager(Main2Activity.this,LinearLayoutManager.VERTICAL,false);
//new适配器
        recyAdapter = new RecyAdapter(this);
        myPresenter = new MyPresenter(this);
//调用presenter里面的数据

        myPresenter.getData();

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyAdapter);

        recyAdapter.setUpdateListener(new RecyAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置UI的改变
                total_num.setText("共"+num+"件商品");//总数量
                total_price.setText("总价 :¥"+total+"元");//总价
                if(allCheck){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.mipmap.ic_launcher);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.mipmap.ic_launcher);
                }
                quanxuan.setChecked(allCheck);

            }
        });
        //这里只做UI更改，点击全选按钮，调到adapter的操作

        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter了里面的方法，把当前quanxuan状态传递过去
                int tag =(int) quanxuan.getTag();
                if (tag == 1){

                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.mipmap.ic_launcher);

                }else {

                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.mipmap.ic_launcher);
                }
                recyAdapter.quanXuan(quanxuan.isChecked());
            }
        });

    }

    @Override
    public void success(ShopBean cartBean) {
//拿回返回来的数据 传递适配器数据
        recyAdapter.add(cartBean);


    }

    @Override
    public void failure(Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main2Activity.this,"error",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //调用p层的解除绑定
        myPresenter.detach();
    }

}
