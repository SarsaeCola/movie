package com.example.movie.demo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.widgts.ScrollListView;
import com.example.movie.demo.R;
import com.example.movie.demo.adapters.LikeListViewAdapter;
import com.example.movie.demo.adapters.MoviesListViewAdapter;
import com.example.movie.demo.beans.MovieBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyLikeListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_list)
    ScrollListView lvList;
    @BindView(R.id.tv_empty_title)
    TextView tvEmptyTitle;
    @BindView(R.id.rela_empty)
    RelativeLayout relaEmpty;

    LikeListViewAdapter adapte;
    List<MovieBean> list = new ArrayList<>();
    List<MovieBean> list_total = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_like_list);
        ButterKnife.bind(this);
        tvTitle.setText("我的订阅");
        initAdatper();
        initData();
    }

    private void initData() {
        list_total.clear();
        list.clear();
        list_total = DataSupport.findAll(MovieBean.class);
        if (list_total != null) {
            for (int i = 0; i < list_total.size(); i++) {
                if (list_total.get(i).isLike()) { //已订阅
                    list.add(list_total.get(i));
                }
            }
        }
        ShowOrHide();
    }

    private void initAdatper() {
        adapte = new LikeListViewAdapter(this, (ArrayList) list, R.layout.item_movie_lv);
        lvList.setAdapter(adapte);
    }

    private void ShowOrHide() {
        adapte.notifyDataSetChanged();
        if (list.size() > 0) {
            lvList.setVisibility(View.VISIBLE);
            relaEmpty.setVisibility(View.GONE);
        } else {
            lvList.setVisibility(View.GONE);
            relaEmpty.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        //点击订阅，刷新列表数据
        if (msg.getMessageType() == EventMessage.CLICK_LIKE) {
            adapte.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.imgv_return)
    public void onViewClicked() {
        onBackPressed();
    }
}
