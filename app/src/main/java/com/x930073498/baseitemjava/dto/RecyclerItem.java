package com.x930073498.baseitemjava.dto;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.x930073498.baseitemjava.BR;
import com.x930073498.baseitemjava.R;
import com.x930073498.baseitemlib.BaseItem;

import java.io.Serializable;

public class RecyclerItem implements BaseItem ,Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void attachToParent(View view) {

    }

    @Override
    public void detachFromParent(View view) {

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