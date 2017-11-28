package com.example.yuekao1123.callBack;

import com.example.yuekao1123.bean.CartBean;
import com.example.yuekao1123.bean.ShopBean;

/**
 * Created by 杨雪峰 on 2017/11/23.
 */

public interface ViewCallBack {

    public void success(ShopBean cartBean);
    public void failure(Exception e);

}
