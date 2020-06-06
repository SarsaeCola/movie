package com.example.movie.demo.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.shop.mylibrary.base.BaseFragment;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.DialogUtil;
import com.app.shop.mylibrary.utils.SharedPreferencesUtil;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.widgts.CustomDialog;
import com.example.movie.demo.R;
import com.example.movie.demo.activitys.LoginActivity;
import com.example.movie.demo.activitys.MyLikeListActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.headImg)
    SimpleDraweeView headImg;
    @BindView(R.id.tv_username_mine)
    TextView tvUsernameMine;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    private String dialog_title = "退出登录";
    private String dialog_content = "是否确定退出登录？";
    private CustomDialog customDialog;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        registerEventBus();
        setSelfView();
        return view;
    }

    private void setSelfView() {
        String name = SharedPreferencesUtil.getData(getActivity(), "user", "user_name", "");
        int user_photo = SharedPreferencesUtil.getData(getActivity(), "user", "user_photo", 0);

        if (StringUtil.isEmpty(name)) {
            headImg.setActualImageResource(R.mipmap.icon_photo_default);
            tvUsernameMine.setText("登录/注册");
            tvLogout.setText("点击登录");

        } else {
            headImg.setActualImageResource(user_photo);
            tvUsernameMine.setText(name);
            tvLogout.setText("退出登录");
        }
    }

    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        if (msg.getMessageType() == EventMessage.LOGIN) {

        } else if (msg.getMessageType() == EventMessage.LOGOUT) {
            SharedPreferencesUtil.removeAll(getContext(), "user");
        }
        setSelfView();
    }

    @OnClick({R.id.tv_logout, R.id.rela_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_logout:
                if (StringUtil.isEmpty(SharedPreferencesUtil.getData(getActivity(), "user", "user_name", ""))) {
                    skipActivity(getActivity(), LoginActivity.class);
                } else {
                    Logout();
                }
                break;

            case R.id.rela_list:

                if (StringUtil.isEmpty(SharedPreferencesUtil.getData(getActivity(), "user", "user_name", ""))) {
                    ToastUtil.showToast(getActivity(), "请先登录");
                    return;
                }
                skipActivity(getActivity(), MyLikeListActivity.class);
                break;
        }

    }

    private void Logout() {
        customDialog = DialogUtil.showDialog(getActivity(), customDialog, 2, dialog_title, dialog_content, "取消", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                EventBus.getDefault().post(new EventMessage(EventMessage.LOGOUT));
            }
        });

        if (customDialog != null && !customDialog.isShowing()) {
            customDialog.show();
        }


        customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                customDialog = null;
            }
        });
    }
}
