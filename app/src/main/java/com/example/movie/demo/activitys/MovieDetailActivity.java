package com.example.movie.demo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.DialogUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.widgts.CustomDialog;
import com.app.shop.mylibrary.widgts.ZhuiXingDialog;
import com.example.movie.demo.R;
import com.example.movie.demo.beans.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.imgv_photo)
    SimpleDraweeView imgvPhoto;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_like)
    TextView tvLike;
    MovieBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        bean = (MovieBean) bundle.getSerializable("bean");
        setView(bean);
        tvTitle.setText(bean.getName());
    }

    private void setView(MovieBean bean) {
        imgvPhoto.setActualImageResource(bean.getPic());
        tvName.setText("电影名称：" + bean.getName());
        tvPeople.setText("导演：" + bean.getPeople());
        tvHot.setText("热度：" + bean.getHot());
        tvScore.setText("评分：" + bean.getScore());
        setLike(bean.isLike());
    }

    public void setLike(boolean isLike) {
        if (isLike) {
            tvLike.setText("已订阅");
            tvLike.setBackground(getResources().getDrawable(R.drawable.shape_bg_999999_4));
            tvLike.setTextColor(getResources().getColor(R.color.color_333333));
        } else {
            tvLike.setText("点击订阅");
            tvLike.setBackground(getResources().getDrawable(R.drawable.shape_bg_3853e8_4));
            tvLike.setTextColor(getResources().getColor(R.color.whilt));
        }
    }

    @OnClick({R.id.imgv_return, R.id.tv_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;
            case R.id.tv_like:
                if (bean.isLike()) { //已订阅，点击取消
                    ToastUtil.showToast(this, "取消订阅");
                    bean.setLike(false);
                } else { //未订阅
                    ToastUtil.showToast(this, "添加订阅");
                    bean.setLike(true);
                }
                setLike(bean.isLike());

                //发送广播，刷新数据
                EventBus.getDefault().post(new EventMessage(EventMessage.CLICK_LIKE, bean));
                break;
        }
    }
}
