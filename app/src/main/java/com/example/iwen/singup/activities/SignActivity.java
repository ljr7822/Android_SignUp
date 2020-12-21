package com.example.iwen.singup.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.PresenterActivity;
import com.example.iwen.common.utils.DateTimeUtil;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.factory.model.db.location.LocationTaskList;
import com.example.iwen.factory.model.db.sign.SignRspModel;
import com.example.iwen.factory.presenter.sign.SignContract;
import com.example.iwen.factory.presenter.sign.SignPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.helper.LocationService;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;
import com.pedaily.yc.ycdialoglib.utils.DialogUtils;

import net.qiujuer.genius.ui.widget.FloatActionButton;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * 打卡界面Activity
 */
public class SignActivity extends PresenterActivity<SignContract.Presenter> implements SignContract.View {
    private static LocationService locationService;
    // 获取定位信息返回的数据模型
    private static LocationTaskList mLocationTaskList;
    // 判断是不是从消息列表进来的
    private static boolean isContact = false;
    // 是否已经定位
    private boolean isClickAction = false;
    // 当前系统时间
    private String systemTime;
    // 格式化后的系统时间:yyyy-mm-dd
    private String formatTimeDay;
    // 格式化后的系统时间:12:12:11
    private String formatTimeH;
    // 格式化经纬度
    private String locationStr;
    // Layout布局
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
    // 提交按钮
    @BindView(R.id.btn_next)
    Button mSubmitSign;
    // 发起人
    @BindView(R.id.sign_send_content)
    TextView mSignSendContent;
    // 签到人
    @BindView(R.id.sign_user_content)
    TextView mSignUserContent;
    // 签到日
    @BindView(R.id.sign_time_content)
    TextView mSignTimeContent;
    // 签到时
    @BindView(R.id.sign_data_content)
    TextView mSignDataContent;
    // 拍照按钮
    @BindView(R.id.im_camera)
    ImageView mImCamera;
    // 拍照的用例
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    // 温度信息
    private String info = null;
    // workId
    private String workId;

    // 定位数据字符串
    private StringBuilder time_sb;
    private StringBuilder NlocType;
    private StringBuilder locTypeDescription;
    private StringBuilder latitude;
    private StringBuilder lontitude;
    private StringBuilder radius;
    private StringBuilder CountryCode;
    private StringBuilder Country;
    private StringBuilder CityCode;
    private StringBuilder City;
    private StringBuilder District;
    private StringBuilder Street;
    private StringBuilder addr;

    private String[] list;
    private String Messagetitle =  "晚间体温";
    private Fragment mFragment;

    /**
     * 打卡Activity显示入口
     * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     *
     * @param context          上下文
     * @param locationTaskList 单个定位信息model
     */
    public static void show(Context context, LocationTaskList locationTaskList) {
        if (locationTaskList != null) {
            mLocationTaskList = locationTaskList;
            isContact = true;
        }
        Intent intent = new Intent(context, SignActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 绑定layout
     */
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 注意，建议加上这个判断
        DialogUtils.requestMsgPermission(this);
        workId = (String) SPUtils.get(this,"workId","10010001");
        // 初始化背景
        initBgImg();
        // 初始化时间
        timeUtil();
        // 修改签到信息框显示
        if (isContact) {
            // 有LocationTaskList传入
            mSignUserContent.setText(mLocationTaskList.getWork());
            mSignTimeContent.setText(mLocationTaskList.getDate());
            mSignDataContent.setText(formatTimeH);
        } else {
            // 无LocationTaskList传入
            mSignUserContent.setText("李俊然");
            mSignTimeContent.setText(formatTimeDay);
            mSignDataContent.setText(formatTimeH);
        }
        locationService = new LocationService(this);
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());

        list = new String[]{"正常", "37.1", "37.9", "38.1", "39.1"};
    }

    /**
     * 获取系统时间
     */
    private void timeUtil() {
        // 获取系统时间
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        systemTime = dateTimeUtil.getTime();
        formatTimeDay = systemTime.split(" ")[0].trim();
        formatTimeH = systemTime.split(" ")[1].trim();
    }

    /**
     * 获取定位点击事件
     */
    @OnClick(R.id.btn_action_sign_in)
    void onSignInClick() {
        if (isClickAction) {
            locationService.stop();
            showXPopupRightLocation(this, "重新定位", "是否重新获取当前位置?");
        } else {
            showXPopupRightLocation(this, "确认", "是否获取当前位置?");
        }
    }

    /**
     * 初始化背景
     */
    private void initBgImg() {
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
                .into(new CustomViewTarget<ConstraintLayout, Drawable>(mLayout) {
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
    }

    /**
     * 销毁定位
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationService.unregisterListener(mListener); // 注销掉监听
        locationService.stop(); // 停止定位服务
    }

    /**
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                // 获取定位时间---------------
                time_sb = new StringBuilder(256);
                time_sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                time_sb.append(location.getTime());
                // 获取定位类型---------------
                NlocType = new StringBuilder(256);
                //nlocType_sb.append("\nlocType : ");// 定位类型
                NlocType.append(location.getLocType());
                // 对应的定位类型说明-------------------
                locTypeDescription = new StringBuilder(256);
                //sb.append("\nlocType description : ");
                locTypeDescription.append(location.getLocTypeDescription());
                // 纬度----------------
                latitude = new StringBuilder(256);
                //sb.append("\nlatitude : ");// 纬度
                latitude.append(location.getLatitude());
                // 经度---------------
                lontitude = new StringBuilder(256);
                //sb.append("\nlontitude : ");// 经度
                lontitude.append(location.getLongitude());
                // 半径---------------
                radius = new StringBuilder(256);
                //sb.append("\nradius : ");// 半径
                radius.append(location.getRadius());
                // 国家码-------------
                CountryCode = new StringBuilder(256);
                //sb.append("\nCountryCode : ");// 国家码
                CountryCode.append(location.getCountryCode());
                // 国家名称---------------
                Country = new StringBuilder(256);
                //sb.append("\nCountry : ");// 国家名称
                Country.append(location.getCountry());
                // 城市编码--------------
                CityCode = new StringBuilder(256);
                //sb.append("\ncitycode : ");// 城市编码
                CityCode.append(location.getCityCode());
                // 城市-----------------
                City = new StringBuilder(256);
                //sb.append("\ncity : ");// 城市
                City.append(location.getCity());
                // 区-------------------
                District = new StringBuilder(256);
                //sb.append("\nDistrict : ");// 区
                District.append(location.getDistrict());
                // 街道----------------
                Street = new StringBuilder(256);
                //sb.append("\nStreet : ");// 街道
                Street.append(location.getStreet());
                // 地址信息-------------
                addr = new StringBuilder(256);
                //sb.append("\naddr : ");// 地址信息
                addr.append(location.getAddrStr());
                locationStr = lontitude + "," + latitude + ";";
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
                logMsg(addr.toString());
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
                                isClickAction = true;
                                mSignDataContent.setText(formatTimeH);
                                mTextViewLocation.setText("定位获取成功");
                                ToastUtils.Builder builder = new ToastUtils.Builder(context);
                                builder.setLayout(R.layout.view_layout_toast_get_location_done)
                                        .setGravity(Gravity.CENTER)
                                        .build()
                                        .show();
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
     * 用户未获取定位
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupGetLocation(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "我再看看",
                        "去获取",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
//                                locationService.start();
//                                isClickAction = true;
//                                mSignDataContent.setText(formatTimeH);
//                                mTextViewLocation.setText("定位获取成功");
//                                ToastUtils.Builder builder = new ToastUtils.Builder(context);
//                                builder.setLayout(R.layout.view_layout_toast_get_location_done)
//                                        .setGravity(Gravity.CENTER)
//                                        .build()
//                                        .show();
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
    void onSignBackClick() {
        showXPopupRightBack(this, "确认", "是否退出签到页面？退出后将自动关闭定位！");
    }

    /**
     * 取消按钮
     */
    @OnClick(R.id.btn_cancel_sign)
    void onCancelSignBackClick() {
        showXPopupRightBack(this, "确认", "是否退出签到页面？退出后将自动关闭定位！");
    }

    /**
     * 拍照按钮
     */
    @OnClick(R.id.im_camera)
    void onCameraClick() {
        // 调用系统相机进行拍照
        Toast.makeText(this, "点击了拍照", Toast.LENGTH_SHORT).show();
        dispatchTakePictureIntent();
    }

    /**
     * 提交按钮，发起请求
     */
    @OnClick(R.id.btn_next)
    void onSubmitClick() {
        // 获取当前是否为拍照打卡
        String ifIcon = mLocationTaskList.getCollectType();
        if (isClickAction) {
            if (info==null){
                showXPopupSelectList(this,Messagetitle,list);
            }else {
                // 已经获取定位
                if (ifIcon.equals("拍照签到")) {
                    // TODO 是拍照打卡，弹出收集信息窗口，弹出拍照选项
                    showXPopupTakePicture(this,
                            "确认", "该任务为拍照打卡任务，是否启用相机进行拍照？",
                            mLocationTaskList.getWork(), workId,mLocationTaskList.getDepartmentName());
                } else {
                    // 不是拍照打卡
                    mPresenter.sign(mLocationTaskList.getSignInId(),
                            mLocationTaskList.getCollectId(),
                            mLocationTaskList.getWork(),
                            info,
                            formatTimeDay+" "+formatTimeH,
                            "null",
                            locationStr);
                }
            }
        } else {
            // 提示用户获取定位
            showXPopupGetLocation(this, "抱歉>_<", "未获取定位，是否获取定位信息！");
        }
    }

    /**
     * 初始化presenter
     */
    @Override
    protected SignContract.Presenter initPresenter() {
        return new SignPresenter(this);
    }

    /**
     * 签到成功回调
     *
     * @param signRspModel 回调的数据
     */
    @Override
    public void signSuccess(SignRspModel signRspModel) {
        // 签到成功的回调，弹窗提示
        showXPopupSuccess(this, "签到成功", Messagetitle +":"+signRspModel.getInfo());
    }

    /**
     * 签到成功弹窗
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupSuccess(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "我知道了",
                        "查看详情",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                // 跳转签到详情fragment
                                Intent intent = new Intent(SignActivity.this, DescActivity.class);
                                intent.putExtra("department",mLocationTaskList.getDepartmentName());
                                intent.putExtra("date",formatTimeDay+" "+formatTimeH);
                                intent.putExtra("adder",addr.toString());
                                intent.putExtra("title",Messagetitle);
                                intent.putExtra("info",info);
                                startActivity(intent);
                            }
                        },
                        new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                ToastUtils.Builder builder = new ToastUtils.Builder(context);
                                builder.setLayout(R.layout.view_layout_toast_done)
                                        .setGravity(Gravity.CENTER)
                                        .build()
                                        .show();
                            }
                        }, false)
                .show();
    }

    /**
     * 拍照打卡
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupTakePicture(Context context, String title, String content,String name,String workId,String department) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "不了",
                        "前往拍照",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                // 跳转拍照的fragment页面进行拍照
                                Intent intent = new Intent(SignActivity.this,TakePictureActivity.class);
                                intent.putExtra("name",name);
                                intent.putExtra("workId",workId);
                                intent.putExtra("department",department);
                                startActivity(intent);
                            }
                        },
                        new OnCancelListener() {
                            @Override
                            public void onCancel() {
//                                ToastUtils.Builder builder = new ToastUtils.Builder(context);
//                                builder.setLayout(R.layout.view_layout_toast_done)
//                                        .setGravity(Gravity.CENTER)
//                                        .build()
//                                        .show();
                            }
                        }, false)
                .show();
    }

    /**
     * 打开相机拍照
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * 获取拍照的获取缩略图
     *
     * @param requestCode int
     * @param resultCode int
     * @param data Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // imageView.setImageBitmap(imageBitmap);
        }
    }

    /**
     * 弹出选择列表
     *
     * @param context 上下文
     * @param title   收集的标题
     * @param list    信息选择的列表
     */
    public void showXPopupSelectList(Context context, String title, String[] list) {
        // 这种弹窗从 1.0.0版本开始实现了优雅的手势交互和智能嵌套滚动
        new XPopup.Builder(context)
                .asBottomList(title, list,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                Toasty.success(getApplicationContext(),text,Toasty.LENGTH_SHORT).show();
                                info = text;
                            }
                        })
                .show();
    }
}