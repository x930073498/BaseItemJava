package com.x930073498.baseitemlib.wrapper;

import android.support.v4.util.ObjectsCompat;

import java.util.Arrays;

public interface EqualsProvider {
    default int hash(Object... objects) {
        return Arrays.hashCode(objects);
    }

    default boolean equals(Object a, Object b) {
        return ObjectsCompat.equals(a, b);
    }
}
