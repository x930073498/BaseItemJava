package com.x930073498.baseitemjava.dto;

import android.databinding.ViewDataBinding;

import com.x930073498.baseitemjava.BR;
import com.x930073498.baseitemjava.R;
import com.x930073498.baseitemlib.BaseItem;

public class RecyclerItem implements BaseItem {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_item;
    }

    @Override
    public int getVariableId() {
        return BR.data;
    }

    @Override
    public void onBindView(ViewDataBinding viewDataBinding, int i) {

    }
}