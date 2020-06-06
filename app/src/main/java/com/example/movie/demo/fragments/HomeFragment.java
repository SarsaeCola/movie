package com.example.movie.demo.fragments;


import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.shop.mylibrary.MyWebActivity;
import com.app.shop.mylibrary.base.BaseFragment;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.utils.UserManager;
import com.app.shop.mylibrary.widgts.ScrollGridView;
import com.example.movie.demo.R;
import com.example.movie.demo.activitys.MovieDetailActivity;
import com.example.movie.demo.adapters.GridAdapter;
import com.example.movie.demo.beans.MovieBean;
import com.example.movie.demo.beans.UserBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_now)
    TextView tvNow;
    @BindView(R.id.gv)
    ScrollGridView gv;

    GridAdapter adapter;
    List<MovieBean> list = new ArrayList();

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        gv.setFocusable(false);
        gv.setFocusableInTouchMode(false);
        registerEventBus();
        initBanner();
        initList();
        return view;
    }

    //列表
    private void initList() {
        list = DataSupport.findAll(MovieBean.class);
        if (list != null && list.size() > 0) {
            Logger.e("-----------数据库取数据--list：" + list.size());
            list = DataSupport.findAll(MovieBean.class);
        } else {
            initData();
        }
        adapter = new GridAdapter(getContext(), (ArrayList) list, R.layout.item_gv);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (UserManager.isLogin(getActivity())) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", list.get(position));
                    skipActivity(getActivity(), MovieDetailActivity.class, bundle);
                } else {
                    ToastUtil.showToast(getActivity(), "请先登录");
                }
            }
        });
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
        Logger.e("-----------本地造数据--list：" + list.size());
        DataSupport.saveAll(list);
    }

    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        if (msg.getMessageType() == EventMessage.CLICK_LIKE) {
            MovieBean bean = (MovieBean) msg.getmObject();
            //更新数据库
            ContentValues values = new ContentValues();
            values.put("isLike", bean.isLike());
            DataSupport.updateAll(MovieBean.class, values, "id = ?", bean.getId() + "");
            //刷新列表
            for (int i = 0; i < list.size(); i++) {
                if (bean.getId() == list.get(i).getId()) {
                    list.get(i).setLike(bean.isLike());
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 轮播图
     */
    private void initBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new FresscoImageLoader());
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置允许手势滑动
        banner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        List list_banner = new ArrayList();
        list_banner.add(R.mipmap.pic_yewen);
        list_banner.add(R.mipmap.pic_chubukeji);
        list_banner.add(R.mipmap.pic_fuchouzhe);
        list_banner.add(R.mipmap.pic_haishanggangqinshi);
        list_banner.add(R.mipmap.pic_wusha);
        list_banner.add(R.mipmap.pic_zhongguojizhang);
        banner.setImages(list_banner);

        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //测试存数据

            }
        });
    }

    @OnClick(R.id.tv_now)
    public void onViewClicked() {

    }

    public class FresscoImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            int url = (int) path;
            imageView.setImageResource(url);
        }

        //提供createImageView 方法，方便fresco自定义ImageView
        @Override
        public ImageView createImageView(Context context) {

            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) getLayoutInflater().inflate(R.layout.layout_banner_imageview, null);
            return simpleDraweeView;
        }
    }

    public void skipWebActivity(String web_url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("url", web_url);
        bundle.putString("title", title);
        skipActivity(getActivity(), MyWebActivity.class, bundle);
    }
}
