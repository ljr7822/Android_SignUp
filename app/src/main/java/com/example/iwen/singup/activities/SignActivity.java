package com.example.iwen.singup.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.singup.R;
import com.example.iwen.singup.helper.LocationService;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import net.qiujuer.genius.ui.widget.FloatActionButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 打卡界面Activity
 */
public class SignActivity extends BaseActivity {
    private static LocationService locationService;
    private boolean isClickAction = false;
    @BindView(R.id.lay_main)
    LinearLayout mLaymali;
    // 标题
    @BindView(R.id.appbar)
    View mLayAppbar;
    // 返回按钮
    @BindView(R.id.im_sign_back)
    ImageView mSignBack;
    // 文本显示框
    @BindView(R.id.tx_address)
    TextView mTextViewAddress;
    // 详情地址显示
    @BindView(R.id.sign_address_content)
    TextView mTextViewAddressTo;
    // 按钮
    @BindView(R.id.btn_action_sign_in)
    FloatActionButton mAction;
    // 背景
    @BindView(R.id.lay)
    ConstraintLayout mLayout;
    // 获取定位的标题
    @BindView(R.id.tv_location)
    TextView mTextViewLocation;


    /**
     * 打卡Activity显示入口
     * @param context 上下文
     */
    public static void show(Context context){
        context.startActivity(new Intent(context,SignActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 给内容部分设置背景图片
        Glide.with(this)
                .load(R.mipmap.top_bg)
                .centerCrop()
                .into(new CustomViewTarget<View, Drawable>(mLayAppbar) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        this.view.setBackground(resource.getCurrent());
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
        // 给界面设置背景
        Glide.with(this)
                .load(R.mipmap.bg_src_play)
                .centerCrop()
                .into(new CustomViewTarget<ConstraintLayout,Drawable>(mLayout) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        this.view.setBackground(resource.getCurrent());
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });

        locationService = new LocationService(this);
//        多个activity
//        locationService = ((App) getApplication()).locationService;
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
    }

    // 获取定位
    @OnClick(R.id.btn_action_sign_in)
    void onSignInClick() {
        if (isClickAction){
            locationService.stop();
            showXPopupRightLocation(this,"重新定位","是否重新获取当前位置?");
        }else {
            showXPopupRightLocation(this,"确认","是否获取当前位置?");
        }
    }

    // 销毁定位
    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationService.unregisterListener(mListener); // 注销掉监听
        locationService.stop(); // 停止定位服务
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuilder sb = new StringBuilder(256);
                //sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                //sb.append(location.getTime());
                //sb.append("\nlocType : ");// 定位类型
                //sb.append(location.getLocType());
                //sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                //sb.append(location.getLocTypeDescription());
                //sb.append("\nlatitude : ");// 纬度
                //sb.append(location.getLatitude());
                //sb.append("\nlontitude : ");// 经度
                //sb.append(location.getLongitude());
                //sb.append("\nradius : ");// 半径
                //sb.append(location.getRadius());
                //sb.append("\nCountryCode : ");// 国家码
                //sb.append(location.getCountryCode());
                //sb.append("\nCountry : ");// 国家名称
                //sb.append(location.getCountry());
                //sb.append("\ncitycode : ");// 城市编码
                //sb.append(location.getCityCode());
                //sb.append("\ncity : ");// 城市
                //sb.append(location.getCity());
                //sb.append("\nDistrict : ");// 区
                //sb.append(location.getDistrict());
                //sb.append("\nStreet : ");// 街道
                //sb.append(location.getStreet());
                //sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                //sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                //sb.append(location.getUserIndoorState());
                //sb.append("\nDirection(not all devices have value): ");
                //sb.append(location.getDirection());// 方向
                //sb.append("\nlocationdescribe: ");
                //sb.append(location.getLocationDescribe());// 位置语义化信息
                //sb.append("\nPoi: ");// POI信息
//                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
//                    for (int i = 0; i < location.getPoiList().size(); i++) {
//                        Poi poi = (Poi) location.getPoiList().get(i);
//                        sb.append(poi.getName() + ";");
//                    }
//                }
//                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                    sb.append("\nspeed : ");
//                    sb.append(location.getSpeed());// 速度 单位：km/h
//                    sb.append("\nsatellite : ");
//                    sb.append(location.getSatelliteNumber());// 卫星数目
//                    sb.append("\nheight : ");
//                    sb.append(location.getAltitude());// 海拔高度 单位：米
//                    sb.append("\ngps status : ");
//                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
//                    sb.append("\ndescribe : ");
//                    sb.append("gps定位成功");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                    // 运营商信息
//                    if (location.hasAltitude()) {// *****如果有海拔高度*****
//                        sb.append("\nheight : ");
//                        sb.append(location.getAltitude());// 单位：米
//                    }
//                    sb.append("\noperationers : ");// 运营商信息
//                    sb.append(location.getOperators());
//                    sb.append("\ndescribe : ");
//                    sb.append("网络定位成功");
//                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                    sb.append("\ndescribe : ");
//                    sb.append("离线定位成功，离线定位结果也是有效的");
//                } else if (location.getLocType() == BDLocation.TypeServerError) {
//                    sb.append("\ndescribe : ");
//                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
//                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//                }
                logMsg(sb.toString());
            }
        }
    };

    /**
     * 显示请求字符串
     */
    public void logMsg(final String str) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mTextViewAddress.post(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewAddress.setText(str);

                        }
                    });
                    mTextViewAddressTo.post(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewAddressTo.setText(str);
                        }
                    });
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 再次确认用户是否获取定位
     * 显示确认和取消对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupRightLocation(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "取消",
                        "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                locationService.start();
                                isClickAction =true;
                                mTextViewLocation.setText("定位获取成功");
                            }
                        },
                        new OnCancelListener() {
                            @Override
                            public void onCancel() {
                            }
                        }, false)
                .show();
    }

    /**
     * 确认用户是否退出定位
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupRightBack(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "取消",
                        "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                finish();
                                locationService.stop();
                            }
                        },
                        new OnCancelListener() {
                            @Override
                            public void onCancel() {
                            }
                        }, false)
                .show();
    }

    /**
     * 返回按钮
     */
    @OnClick(R.id.im_sign_back)
    void onSignBackClick(){
        showXPopupRightBack(this,"确认","是否退出签到页面？退出后将自动关闭定位！");
    }
}