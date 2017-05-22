package com.zte.shawn.lotterycheck.activity;

import android.content.Context;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by 10192984 on 2017/1/9.
 */
public class ThreadWeiXinLoginActivity {
    private Context context;
    private static final String APP_ID="wx1887105ff42f30b4";
    private IWXAPI api;

    private void getToWx(){
        api = WXAPIFactory.createWXAPI(context,APP_ID,true);
        api.registerApp(APP_ID);
    }

    public void getAuth(){

            // send oauth request
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);


    }

    Thread gaoDuanDaQiShangDangCi = new Thread( () -> {
        System.out.println("This is from an anonymous method (lambda exp).");
    } );
}
