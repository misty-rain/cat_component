package com.friendtimes.ft_module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.friendtimes.ft_logger.LogProxy;
import com.friendtimes.http.HttpUtility;
import com.friendtimes.http.callback.StringCallback;
import com.friendtimes.http.config.HttpMethod;
import com.mistyrain.okhttp.Call;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogProxy.setDebugMode(true);
        String json = "{\"topic_c_ver\":\"2016\",\"chat_view_ver\":\"1\",\"recharge_card_des_ver\":\"201504181400\",\"vservice_sdk_domain\":\"csdk.haowanyou.com\",\"vservice_custom_domain\":\"m-service.haowanyou.com\",\"android_mac_filter\":\"00:00:00:00:00:00\",\"mall_claim_ver\":\"201506171519\",\"crash_domain\":\"gam.m.haowanyou.com\",\"game_domain\":\"gam.9917.com\",\"vservice_im_domain\":\"cimw.haowanyou.com\",\"ad_product_ids\":\"26\",\"sms_channel\":\"0\",\"recharge_card_des_url\":\"http://pf-res.haowanyou.com/m/props/rechargecardsConfig.json?v=\",\"service_im_domain\":\"imw.haowanyou.com\",\"icon_widget_ver\":\"1\",\"platform_download_url\":\"http://m.haowanyou.com\",\"vservice_ucenter_domain\":\"cucenter.haowanyou.com\",\"onekey_notice\":\"0\",\"topic_i_ver\":\"1\",\"sms_notice\":\"0\",\"pf_res_url\":\"http://pf-res.haowanyou.com/\",\"service_ucenter_domain\":\"119.29.106.51:8881\",\"vs_cdn_domain\":\"cres.haowanyou.com\",\"service_upload_domain\":\"119.29.106.140:8883\",\"faq_ver\":\"2015082011002\",\"topic_k_ver\":\"1\",\"onekey_show_flag\":\"1\",\"vservice_upload_domain\":\"cupload.haowanyou.com\",\"service_custom_domain\":\"m-service.haowanyou.com\",\"faq_url\":\"http://appcdn.haowanyou.com/sdk/res/json/faq.json?v=\",\"real_confirm\":\"0\",\"ad_start_ver\":\"1\",\"ad_product_url\":\"http://static.haowanyou.com/m/sdk/ad/android/\",\"platform_check_name\":\"com.GF.platform\",\"wish_share_url\":\"http://www.haowanyou.com/mapp/xy.html\",\"s_cdn_domain\":\"res.haowanyou.com\",\"service_sdk_domain\":\"sdk.haowanyou.com\",\"sms_pay_switch\":\"1\"}";
        LogProxy.e("Main","this is Main Tag");
        LogProxy.d("test","this is test tag");
        LogProxy.i("nige","this is nige tag");
        LogProxy.json(json);
        Map<String,String> params =  new HashMap<>();
        try {
            HttpUtility.getInstance().execute(HttpMethod.GET, "", params, new StringCallback() {


                @Override
                public void onError(Call call, Exception e) {

                }

                @Override
                public void onResponse(String response) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
