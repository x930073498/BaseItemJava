package com.x930073498.baseitemlib;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BaseItemWrapper<T> implements BaseItem, EqualsProvider {
    private static final String TAG = "BaseItemWrapper";
    private BaseAdapter adapter;
    private BaseItemLayoutProvider<T> provider;
    private T data;
    private BaseItemDataBinder<T> binder;

    public T getData() {
        return data;
    }

    private BaseItemWrapper(T data, BaseItemLayoutProvider<T> provider, BaseItemDataBinder<T> binder, BaseAdapter adapter) {
        this.binder = binder;
        this.provider = provider;
        this.data = data;
        this.adapter = adapter;
    }

    public static <T> List<BaseItemWrapper<T>> warpList(List<T> data, @NonNull BaseItemLayoutProvider provider, @NonNull BaseItemDataBinder<T> binder, BaseAdapter adapter) {

        List<BaseItemWrapper<T>> result = new ArrayList<>();
        if (data == null) return result;
        for (T temp : data
                ) {
            if (temp == null) continue;
            result.add(warp(temp, provider, binder, adapter));
        }
        return result;
    }

    public static <T> List<BaseItem> toBaseItem(List<T> data, @NonNull BaseItemLayoutProvider<T> provider, @NonNull BaseItemDataBinder<T> binder, BaseAdapter adapter) {
        return new ArrayList<>(warpList(data, provider, binder, adapter));
    }

    public static <T> List<BaseItemWrapper<T>> warpAndSet(List<T> data, @NonNull BaseItemLayoutProvider<T> provider, @NonNull BaseItemDataBinder<T> binder, BaseAdapter adapter) {
        List<BaseItemWrapper<T>> result = warpList(data, provider, binder, adapter);
        adapter.setData(result);
        return result;
    }

    public static <T> BaseItemWrapper<T> warp(@NonNull T data, @NonNull BaseItemLayoutProvider<T> provider, @NonNull BaseItemDataBinder<T> binder, BaseAdapter adapter) {
        return new BaseItemWrapper<>(data, provider, binder, adapter);
    }

    @Override
    public void attachToParent(View view) {
        Log.d(TAG, "attachToParent: " + view.getClass().getName());
    }

    @Override
    public int getLayoutId() {
        return provider.provideLayout(data);
    }

    @Override
    public int getVariableId() {
        return NO_ID;
    }

    @Override
    public void onBindView(ViewDataBinding viewDataBinding, int i) {
        if (binder != null)
            binder.bind(adapter, data, viewDataBinding, i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseItemWrapper)) return false;
        BaseItemWrapper<?> that = (BaseItemWrapper<?>) o;
        return equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return hash(data);
    }
}
