package com.x930073498.baseitemjava;

import android.databinding.ObservableArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.databinding.DataBindingUtil;
import android.content.Intent;

import com.x930073498.baseitemjava.databinding.ActivityMainBinding;
import com.x930073498.baseitemjava.dto.RecyclerItem;
import com.x930073498.baseitemlib.BaseAdapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private RecyclerView recycler;
    private BaseAdapter adapter;
    private ActivityMainBinding binding;
    private List<RecyclerItem> data=new ObservableArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parseIntent(getIntent());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recycler = binding.recycler;
        initData();
        adapter = new BaseAdapter();
        adapter.setData(data);
        recycler.setAdapter(adapter);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * 解析Intent，从Intent中获取数据
     *
     * @param intent intent
     */
    private void parseIntent(Intent intent) {

    }

    private void initData(){
        data.clear();
        RecyclerItem item;
        for (int i = 0; i <100 ; i++) {
            item=new RecyclerItem();
            item.setName("name".concat(String.valueOf(i)));
            data.add(item);
        }
    }

}