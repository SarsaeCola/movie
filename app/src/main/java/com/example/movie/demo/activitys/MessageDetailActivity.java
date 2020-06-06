package com.example.movie.demo.activitys;

import android.os.Bundle;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.widgts.ScrollGridView;
import com.example.movie.demo.R;
import com.example.movie.demo.adapters.GridAdapter;
import com.example.movie.demo.beans.MovieBean;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.gv)
    ScrollGridView gv;

    GridAdapter adapter;
    List<MovieBean> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        ButterKnife.bind(this);
        tvTitle.setText("消息详情");
        initData();
    }


    //本地数据
    private void initData() {
        for (int i = 1; i < 7; i++) {
            MovieBean bean = new MovieBean();
            bean.setId(i);
            bean.setLike(false);
            bean.setScore("9." + i);
            bean.setHot(666 + i);
            bean.setRead(false);
            switch (i) {
                case 1:
                    bean.setName("触不可及");
                    bean.setPeople("尼尔·博格");
                    bean.setPic(R.mipmap.pic_chubukeji);
                    break;
                case 2:
                    bean.setName("复仇者联盟");
                    bean.setPeople("乔斯·韦登");
                    bean.setPic(R.mipmap.pic_fuchouzhe);
                    break;
                case 3:
                    bean.setName("海上钢琴师");
                    bean.setPeople("吉赛贝·托纳多雷");
                    bean.setPic(R.mipmap.pic_haishanggangqinshi);
                    break;
                case 4:
                    bean.setName("误杀");
                    bean.setPeople("柯汶利");
                    bean.setPic(R.mipmap.pic_wusha);
                    break;
                case 5:
                    bean.setName("叶问");
                    bean.setPeople("叶伟信");
                    bean.setPic(R.mipmap.pic_yewen);
                    break;
                case 6:
                    bean.setName("中国机长");
                    bean.setPeople("刘伟强");
                    bean.setPic(R.mipmap.pic_zhongguojizhang);
                    break;
            }
            list.add(bean);
        }

        Collections.shuffle(list);
        adapter = new GridAdapter(this, (ArrayList) list, R.layout.item_gv);
        gv.setAdapter(adapter);
    }

    @OnClick(R.id.imgv_return)
    public void onViewClicked() {
        onBackPressed();
    }
}
