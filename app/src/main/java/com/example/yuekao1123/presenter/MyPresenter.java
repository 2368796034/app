package com.example.yuekao1123.presenter;

import com.example.yuekao1123.bean.CartBean;
import com.example.yuekao1123.bean.ShopBean;
import com.example.yuekao1123.callBack.ModelCallBack;
import com.example.yuekao1123.callBack.ViewCallBack;
import com.example.yuekao1123.model.MyModel;

/**
 * Created by 杨雪峰 on 2017/11/23.
 */

public class MyPresenter {

    MyModel myModel = new MyModel();
    ViewCallBack viewCallBack;
    public MyPresenter(ViewCallBack viewCallBack) {
        this.viewCallBack = viewCallBack;
    }

    //调用model 层的请求数据
    public void getData(){
        myModel.getData(new ModelCallBack() {
            @Override
            public void success(ShopBean cartBean) {
                if(viewCallBack!=null) {
                    viewCallBack.success(cartBean);
                }
            }

            @Override
            public void failure(Exception e) {
                if(viewCallBack!=null) {
                    viewCallBack.failure(e);
                }
            }
        });
    }

    /**
     * 防止内存泄露
     * */
    public void detach(){
        viewCallBack=null;
    }



}
