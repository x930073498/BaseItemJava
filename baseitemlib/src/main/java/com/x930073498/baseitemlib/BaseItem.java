package com.x930073498.baseitemlib;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public interface BaseItem {
    int NO_ID = -1;

    @LayoutRes
    int getLayoutId();

    int getVariableId();

    void onBindView(ViewDataBinding binding, int position);
}
