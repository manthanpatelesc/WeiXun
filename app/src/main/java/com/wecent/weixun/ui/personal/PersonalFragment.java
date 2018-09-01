package com.wecent.weixun.ui.personal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wecent.weixun.R;
import com.wecent.weixun.component.ApplicationComponent;
import com.wecent.weixun.ui.base.BaseFragment;
import com.wecent.weixun.widget.Scroll.TranslucentScrollView;
import com.wecent.weixun.widget.ToolBar.ToolBarClickListener;
import com.wecent.weixun.widget.ToolBar.TranslucentToolBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * desc: 个人页面
 * author: wecent .
 * date: 2017/9/2 .
 */
public class PersonalFragment extends BaseFragment implements ToolBarClickListener, TranslucentScrollView.TranslucentChangedListener {

    @BindView(R.id.v_status)
    View vStatus;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.iv_weibo)
    ImageView ivWeibo;
    @BindView(R.id.tv_weibo)
    TextView tvWeibo;
    @BindView(R.id.iv_zhihu)
    ImageView ivZhihu;
    @BindView(R.id.tv_zhihu)
    TextView tvZhihu;
    @BindView(R.id.iv_ding)
    ImageView ivDing;
    @BindView(R.id.tv_ding)
    TextView tvDing;
    @BindView(R.id.iv_taobao)
    ImageView ivTaobao;
    @BindView(R.id.tv_taobao)
    TextView tvTaobao;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.iv_sketch)
    ImageView ivSketch;
    @BindView(R.id.tv_ie)
    TextView tvIe;
    @BindView(R.id.iv_google)
    ImageView ivGoogle;
    @BindView(R.id.tv_google)
    TextView tvGoogle;
    @BindView(R.id.iv_facebook)
    ImageView ivFacebook;
    @BindView(R.id.tv_facebook)
    TextView tvFacebook;
    @BindView(R.id.iv_dropbox)
    ImageView ivDropbox;
    @BindView(R.id.tv_dropbox)
    TextView tvDropbox;
    @BindView(R.id.iv_linkedin)
    ImageView ivLinkedin;
    @BindView(R.id.tv_linkedin)
    TextView tvLinkedin;
    @BindView(R.id.iv_reddit)
    ImageView ivReddit;
    @BindView(R.id.tv_reddit)
    TextView tvReddit;
    @BindView(R.id.scrollTrans)
    TranslucentScrollView scrollTrans;
    @BindView(R.id.toolTrans)
    TranslucentToolBar toolTrans;
    Unbinder unbinder;

    public static PersonalFragment newInstance() {
        Bundle args = new Bundle();
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        //初始actionBar
        toolTrans.setData("wecent", 0, null, 0, null, null);
        //开启渐变
        toolTrans.setNeedTranslucent();
        //设置状态栏高度
        toolTrans.setStatusBarHeight(getStatusBarHeight());
        //设置透明度变化监听
        scrollTrans.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        scrollTrans.setTransView(toolTrans);
        //设置ActionBar键渐变颜色
        scrollTrans.setTransColor(getResources().getColor(R.color.app_color_blue));
        //关联伸缩的视图
        scrollTrans.setPullZoomView(llHeader);
    }

    @Override
    public void initData() {

    }

//    @OnClick({R.id.tvUrl, R.id.tvGithubUrl})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tvUrl:
//                toWeb(getResources().getString(R.string.willUrl));
//                break;
//            case R.id.tvGithubUrl:
//                toWeb(getResources().getString(R.string.githubUrl));
//                break;
//
//        }
//    }

    private void toWeb(String url) {
        Uri weburl = Uri.parse(url);
        Intent web_Intent = new Intent(Intent.ACTION_VIEW, weburl);
        getActivity().startActivity(web_Intent);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        toolTrans.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }
}
