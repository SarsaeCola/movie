package com.example.movie.demo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.SharedPreferencesUtil;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.example.movie.demo.R;
import com.example.movie.demo.beans.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.inputMobile)
    EditText inputMobile;
    @BindView(R.id.inputpwd)
    EditText inputpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        tvTitle.setText("登录");
    }

    @OnClick({R.id.imgv_return, R.id.toLogin, R.id.rigister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;
            case R.id.toLogin:
                if (StringUtil.isEmpty(inputMobile.getText().toString())) {
                    ToastUtil.showToast(this, "请输入账号");
                    return;
                }

                if (StringUtil.isEmpty(inputpwd.getText().toString())) {
                    ToastUtil.showToast(this, "请输入密码");
                    return;
                }

                UserBean userBean = new UserBean();
                userBean.setUser_name(inputMobile.getText().toString());
                userBean.setUser_photo(R.mipmap.pic_zhongguojizhang);
                SharedPreferencesUtil.saveDataBean(this, userBean, "user");
                EventBus.getDefault().post(new EventMessage(EventMessage.LOGIN));
                ToastUtil.showToast(this, "登录成功");
                finish();
                break;
            case R.id.rigister:
                showActivity(this, RigisterActivity.class);
                break;
        }
    }
}
