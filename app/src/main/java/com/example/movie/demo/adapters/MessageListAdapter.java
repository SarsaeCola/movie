package com.example.movie.demo.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.app.shop.mylibrary.base.CommonAdapter;
import com.app.shop.mylibrary.base.ViewHolder;
import com.example.movie.demo.R;
import com.example.movie.demo.beans.MessageBean;
import com.example.movie.demo.beans.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;



public class MessageListAdapter extends CommonAdapter {

    public MessageListAdapter(Context context, ArrayList datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void setView(ViewHolder holder, Object o, int position) {
        MessageBean bean = (MessageBean) o;
        TextView text_name = holder.getView(R.id.tv_list_item);
        TextView tv_status = holder.getView(R.id.tv_status);

        text_name.setText(bean.getContent());
        tv_status.setText("new");
        if (bean.isNew()) {
            tv_status.setVisibility(View.VISIBLE);
        } else {
            tv_status.setVisibility(View.GONE);
        }
    }
}
