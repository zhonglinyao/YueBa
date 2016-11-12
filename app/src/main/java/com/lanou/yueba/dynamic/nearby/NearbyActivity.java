package com.lanou.yueba.dynamic.nearby;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.lanou.yueba.R;
import com.lanou.yueba.app.YueBaApp;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.tools.ActivityTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dllo on 16/11/10.
 */

public class NearbyActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private ImageView mIvBack;
    private RadioGroup mRadioGroup;
    LocationClient mLocClient;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位
    private Button mBtnMode;
    private RadioButton mRbNormal;
    private RadioButton mRbSatellite;
    private TextView mTvSaveLocation;
    private FrameLayout mFlSave;
    private ImageView mIvSave;

    @Override
    protected int setLayout() {
        return R.layout.activity_nearby;
    }

    @Override
    protected void initView() {
        mMapView = bindView(R.id.mv_nearby);
        mIvBack = bindView(R.id.iv_back_nearby);
        mRadioGroup = bindView(R.id.rg_nearby);
        mBtnMode = bindView(R.id.btn_mode_nearby);
        mRbNormal = bindView(R.id.btn_normal_nearby);
        mRbSatellite = bindView(R.id.btn_satellite_nearby);
        mTvSaveLocation = bindView(R.id.tv_save_location_nearby);
        mFlSave = bindView(R.id.fl_save_nearby);
        mIvSave = bindView(R.id.iv_save_map_nearby);
    }

    @Override
    protected void initData() {
        Log.d("Sysout", "test");
        mFlSave.setVisibility(View.GONE);
        mIvSave.setVisibility(View.GONE);
        initListener();
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBtnMode.setText("普通");
        mBaiduMap = mMapView.getMap();
        mRadioGroup.setOnCheckedChangeListener(this);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setTrafficEnabled(true);
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, null));
    }

    private void initListener() {

        mIvBack.setOnClickListener(this);
        mBtnMode.setOnClickListener(this);
        mTvSaveLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_nearby:
                ActivityTools.deleteActivity(NearbyActivity.this.getClass().getSimpleName());
                break;
            case R.id.btn_mode_nearby:
                switch (mCurrentMode) {
                    case NORMAL:
                        mBtnMode.setText("跟随");
                        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                                mCurrentMode, true, null));
                        break;
                    case COMPASS:
                        mBtnMode.setText("普通");
                        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                                mCurrentMode, true, null));
                        break;
                    case FOLLOWING:
                        mBtnMode.setText("罗盘");
                        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                                mCurrentMode, true, null));
                        break;
                    default:
                        break;
                }
                break;
            case R.id.tv_save_location_nearby:
                save();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.btn_normal_nearby:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                mRbNormal.setTextColor(Color.BLACK);
                mRbSatellite.setTextColor(Color.BLACK);
                mBtnMode.setTextColor(Color.BLACK);
                break;
            case R.id.btn_satellite_nearby:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                mRbNormal.setTextColor(Color.WHITE);
                mRbSatellite.setTextColor(Color.WHITE);
                mBtnMode.setTextColor(Color.WHITE);
                break;

        }
    }

    private void save() {
        mIvBack.setClickable(false);
        mTvSaveLocation.setClickable(false);
        mFlSave.setVisibility(View.VISIBLE);
        mIvSave.setVisibility(View.VISIBLE);
        mFlSave.setBackgroundColor(getResources().getColor(R.color.colorBackgroundLoading));
        AnimationDrawable drawable = (AnimationDrawable) mIvSave.getBackground();
        drawable.start();
        /**
         * null 全屏
         */
        mBaiduMap.snapshotScope(null, new BaiduMap.SnapshotReadyCallback() {
            public void onSnapshotReady(Bitmap snapshot) {
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
                String pathName = Environment.getExternalStorageDirectory().getPath()
                        + "/yueba/location/";
                String fileName = sdf.format(new Date()) + ".png";
                File path = new File(pathName);
                File file = new File(pathName + fileName);
                FileOutputStream out;

                try {
                    if (!path.exists()) {
                        path.mkdirs();
                    }
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    out = new FileOutputStream(file);

                    if (snapshot.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                        Log.d("Sysout", "ccc");
                        out.flush();
                        out.close();
                        Log.d("Sysout", "bbb");
                        showNotification(snapshot);
                        mFlSave.setVisibility(View.GONE);
                        mIvSave.setVisibility(View.GONE);
                        mIvBack.setClickable(true);
                        mTvSaveLocation.setClickable(true);
                    }else {
                        Log.d("Sysout", "ddd");
                    }
                } catch (FileNotFoundException e) {
                    mIvBack.setClickable(true);
                    mTvSaveLocation.setClickable(true);
                    Toast.makeText(YueBaApp.getContext(), "保存失败", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    mIvBack.setClickable(true);
                    mTvSaveLocation.setClickable(true);
                    Toast.makeText(YueBaApp.getContext(), "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showNotification(Bitmap bitmap) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Log.d("Sysout", "aaaa");
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
              //  .setLargeIcon(bitmap)
                .setTicker("定位图已保存")
                .setContentTitle("定位图已保存")
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(101, notification);
    }

    @Override
    protected void onDestroy() {
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityTools.deleteActivity(NearbyActivity.this.getClass().getSimpleName());
    }

    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


}
