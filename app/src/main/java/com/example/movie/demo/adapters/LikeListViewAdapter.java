package com.example.movie.demo.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.app.shop.mylibrary.base.CommonAdapter;
import com.app.shop.mylibrary.base.ViewHolder;
import com.app.shop.mylibrary.beans.EventMessage;
import com.example.movie.demo.R;
import com.example.movie.demo.beans.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;




public class LikeListViewAdapter extends CommonAdapter {

    public LikeListViewAdapter(Context context, ArrayList datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void setView(ViewHolder holder, Object o, int position) {
        MovieBean bean = (MovieBean) o;
        SimpleDraweeView imgv_Pic = holder.getView(R.id.imgv_list);
        TextView text_name = holder.getView(R.id.tv_list_item);
        TextView tv_status = holder.getView(R.id.tv_status);

        imgv_Pic.setActualImageResource(bean.getPic());
        text_name.setText(bean.getName());
        if (bean.isLike()) {
            tv_status.setTextColor(getAdapterContext().getResources().getColor(R.color.whilt));
            tv_status.setText("未订阅");
            tv_status.setBackgroundDrawable(getAdapterContext().getResources().getDrawable(R.drawable.shape_bg_3853e8_4));
        } else {
            tv_status.setTextColor(getAdapterContext().getResources().getColor(R.color.color_333333));
            tv_status.setText("已订阅");
            tv_status.setBackgroundDrawable(getAdapterContext().getResources().getDrawable(R.drawable.shape_bg_999999_4));
        }

        tv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isLike()) {
                    bean.setLike(false);
                    tv_status.setTextColor(getAdapterContext().getResources().getColor(R.color.color_333333));
                    tv_status.setText("未订阅");
                    tv_status.setBackgroundDrawable(getAdapterContext().getResources().getDrawable(R.drawable.shape_bg_999999_4));
                } else {
                    bean.setLike(true);
                    tv_status.setTextColor(getAdapterContext().getResources().getColor(R.color.whilt));
                    tv_status.setText("已订阅");
                    tv_status.setBackgroundDrawable(getAdapterContext().getResources().getDrawable(R.drawable.shape_bg_999999_4));
                }
                EventBus.getDefault().post(new EventMessage(EventMessage.CLICK_LIKE, bean));
            }
        });
    }
}
