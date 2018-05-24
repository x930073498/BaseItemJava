package com.x930073498.baseitemlib.wrapper;

import android.databinding.ViewDataBinding;

import com.x930073498.baseitemlib.BaseAdapter;

public interface BaseItemDataBinder<T> {
    void bind(BaseAdapter adapter, T data, ViewDataBinding binding, int position);

}
