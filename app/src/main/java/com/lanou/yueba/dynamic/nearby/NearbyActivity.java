package com.lanou.yueba.dynamic.nearby;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;

/**
 * Created by dllo on 16/11/10.
 */

public class NearbyActivity extends BaseActivity {
//    private MapView mMapView;

    @Override
    protected int setLayout() {
        return R.layout.activity_nearby;
    }

    @Override
    protected void initView() {
//        mMapView = bindView(R.id.map);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mMapView.onPause();
    }
}
