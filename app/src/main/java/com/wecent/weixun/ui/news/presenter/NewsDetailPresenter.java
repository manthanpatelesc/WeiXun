package com.wecent.weixun.ui.news.presenter;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wecent.weixun.model.entity.NewsDetail;
import com.wecent.weixun.model.response.CommentResponse;
import com.wecent.weixun.model.response.ResultResponse;
import com.wecent.weixun.network.BaseObserver;
import com.wecent.weixun.network.RxSchedulers;
import com.wecent.weixun.network.WeiXunApiManager;
import com.wecent.weixun.ui.base.BasePresenter;
import com.wecent.weixun.ui.news.contract.NewsDetailContract;

import javax.inject.Inject;

/**
 * desc:
 * author: wecent
 * date: 2018/9/19
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailContract.View> implements NewsDetailContract.Presenter {

    WeiXunApiManager mWeiXunApi;

    @Inject
    public NewsDetailPresenter(WeiXunApiManager mWeiXunApi) {
        this.mWeiXunApi = mWeiXunApi;
    }

    @Override
    public void getNewsData(String url) {
        mWeiXunApi.getNewsDetail(url)
                .compose(RxSchedulers.<ResultResponse<NewsDetail>>applySchedulers())
                .compose(mView.<ResultResponse<NewsDetail>>bindToLife())
                .subscribe(new BaseObserver<ResultResponse<NewsDetail>>() {
                    @Override
                    public void onSuccess(ResultResponse<NewsDetail> response) {
                        Logger.e(new Gson().toJson(response));
                        mView.loadNewsData(response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Logger.e(e.getLocalizedMessage());
                        mView.loadNewsData(null);
                    }
                });
    }

    @Override
    public void getCommentData(String groupId, String itemId, int pageNow) {
        int offset = (pageNow - 1) * 20;
        mWeiXunApi.getCommentList(groupId, itemId,String.valueOf(offset), "20")
                .compose(RxSchedulers.<CommentResponse>applySchedulers())
                .compose(mView.<CommentResponse>bindToLife())
                .subscribe(new BaseObserver<CommentResponse>() {
                    @Override
                    public void onSuccess(CommentResponse response) {
                        Logger.e(new Gson().toJson(response));
                        mView.loadCommentData(response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Logger.e(e.getLocalizedMessage());
                        mView.loadCommentData(null);
                    }
                });
    }
}
