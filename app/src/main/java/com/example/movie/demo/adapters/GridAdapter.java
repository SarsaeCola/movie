package com.example.movie.demo.adapters;

import android.content.Context;
import android.widget.TextView;

import com.app.shop.mylibrary.base.CommonAdapter;
import com.app.shop.mylibrary.base.ViewHolder;

import com.example.movie.demo.R;
import com.example.movie.demo.beans.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


public class GridAdapter extends CommonAdapter {

    public GridAdapter(Context context, ArrayList datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void setView(ViewHolder holder, Object o, int position) {
        MovieBean bean = (MovieBean) o;
        SimpleDraweeView imgv_Pic = holder.getView(R.id.imgv_home_gv);
        TextView textView = holder.getView(R.id.tv_home_gv);
        TextView tv_score = holder.getView(R.id.tv_score);
        TextView tv_status = holder.getView(R.id.tv_status);

        imgv_Pic.setActualImageResource(bean.getPic());
        textView.setText(bean.getName());
        tv_score.setText("评分" + bean.getScore());
        if (bean.isLike()) {
            tv_status.setTextColor(getAdapterContext().getResources().getColor(R.color.color_3853e8));
            tv_status.setText("已订阅");
        } else {
            tv_status.setTextColor(getAdapterContext().getResources().getColor(R.color.color_333333));
            tv_status.setText("未订阅");
        }
    }
}
