package com.example.movie.demo.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.app.shop.mylibrary.base.BaseFragment;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.utils.UserManager;
import com.app.shop.mylibrary.widgts.ScrollListView;
import com.example.movie.demo.R;
import com.example.movie.demo.activitys.MessageDetailActivity;
import com.example.movie.demo.activitys.MovieDetailActivity;
import com.example.movie.demo.adapters.MessageListAdapter;
import com.example.movie.demo.adapters.MoviesListViewAdapter;
import com.example.movie.demo.beans.MessageBean;
import com.example.movie.demo.beans.MovieBean;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends BaseFragment {

    @BindView(R.id.lv_message)
    ScrollListView lv_message;

    MessageListAdapter adapter;
    List<MessageBean> list = new ArrayList<>();


    public CenterFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        ButterKnife.bind(this, view);
        registerEventBus();
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        lv_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (UserManager.isLogin(getActivity())) {

                    skipActivity(getActivity(), MessageDetailActivity.class);
                    list.get(position).setNew(false);
                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showToast(getActivity(), "请先登录");
                }
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            MessageBean bean = new MessageBean();
            bean.setContent("你有新的推送消息" + i);
            bean.setNew(true);
            list.add(bean);
        }
        adapter = new MessageListAdapter(getContext(), (ArrayList) list, R.layout.item_message_lv);
        lv_message.setAdapter(adapter);
    }
}
