package com.x930073498.baseitemlib.wrapper;

import android.support.annotation.LayoutRes;

public interface BaseItemLayoutProvider<T> {
    @LayoutRes
    int provideLayout(T data);
}
