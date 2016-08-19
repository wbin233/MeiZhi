package com.example.dell_pc.meizhi.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell_pc.meizhi.Activity.ShowImgActivity;
import com.example.dell_pc.meizhi.Adapter.BaseAdapter;
import com.example.dell_pc.meizhi.Model.BaseModel;
import com.example.dell_pc.meizhi.Model.ZhuangbiModel;
import com.example.dell_pc.meizhi.R;
import com.example.dell_pc.meizhi.network.Network;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wbin on 2016/8/16.
 */
public class ZhuangbiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    BaseAdapter adapter = new BaseAdapter();
    Subscriber subscriber = new Subscriber<List<BaseModel>>() {
        @Override
        public void onCompleted() {
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<BaseModel> o) {
            adapter.setList(o);
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meizhi_fragment, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, String url, String title) {
                Intent intent = new Intent(getActivity(), ShowImgActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setRefreshing(false);
        load();
        return view;
    }

    public void load() {
        Network.getZhuangbiApi()
                .search("装逼")
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<List<ZhuangbiModel>, List<BaseModel>>() {
                    @Override
                    public List<BaseModel> call(List<ZhuangbiModel> zhuangbiModels) {
                        List<BaseModel> list = new LinkedList<>();
                        for (ZhuangbiModel zhuangbiModel : zhuangbiModels) {
                            list.add(new BaseModel(zhuangbiModel.getDescription(), zhuangbiModel.getImage_url()));
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void onRefresh() {
        load();
    }
}
