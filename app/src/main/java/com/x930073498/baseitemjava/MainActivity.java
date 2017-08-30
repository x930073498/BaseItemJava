package com.x930073498.baseitemjava;

import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.x930073498.baseitemlib.BaseAdapter;
import com.x930073498.baseitemlib.BaseItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
List<TestItem> data=new ObservableArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseAdapter adapter=new BaseAdapter();
        adapter.setData(data);
    }

    public static class TestItem implements BaseItem{

        @Override
        public int getLayoutId() {
            return 0;
        }

        @Override
        public int getVariableId() {
            return 0;
        }

        @Override
        public void onBindView(ViewDataBinding binding, int position) {

        }
    }
}
