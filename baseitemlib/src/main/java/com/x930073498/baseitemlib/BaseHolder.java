package com.x930073498.baseitemlib;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class BaseHolder extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    private ViewDataBinding binding;
    private BaseItem baseItem;

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    public BaseItem getBaseItem() {
        return baseItem;
    }

    public void setBaseItem(BaseItem baseItem) {
        this.baseItem = baseItem;
    }
}
