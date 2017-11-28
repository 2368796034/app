package com.example.yuekao1123.model;

import com.example.yuekao1123.bean.CartBean;
import com.example.yuekao1123.bean.ShopBean;
import com.example.yuekao1123.callBack.ModelCallBack;
import com.example.yuekao1123.okhttp.AbstractUiCallBack;
import com.example.yuekao1123.okhttp.OkhttpUtils;

/**
 * Created by 杨雪峰 on 2017/11/23.
 */

public class MyModel {

    public void getData(final ModelCallBack modelCallBack){
        //访问接口
        String path = "http://120.27.23.105/product/getCarts?uid=100";

        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<ShopBean>() {
            @Override
            public void success(ShopBean cartBean) {
                modelCallBack.success(cartBean);
            }

            @Override
            public void failure(Exception e) {

                 modelCallBack.failure(e);
            }
        });


    }


}
