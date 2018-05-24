package com.x930073498.baseitemlib;

import android.support.annotation.LayoutRes;

public interface BaseItemLayoutProvider<T> {
    @LayoutRes
    int provideLayout(T data);
}
