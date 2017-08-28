package com.x930073498.baseitemlib;

import android.databinding.ViewDataBinding;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public abstract class AbstractBaseItem implements BaseItem {
    @Override
    public int getVariableId() {
        return NO_ID;
    }

    @Override
    public void onBindView(ViewDataBinding binding, int position) {

    }
}
