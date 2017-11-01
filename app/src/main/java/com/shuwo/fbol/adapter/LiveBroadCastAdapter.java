package com.shuwo.fbol.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.TextLengthUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.Util.Utils;
import com.shuwo.fbol.bean.HomeMatchs;
import com.shuwo.fbol.bean.LiveBroadCast;

import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class LiveBroadCastAdapter extends BaseQuickAdapter<LiveBroadCast, BaseViewHolder> {

    public LiveBroadCastAdapter(@LayoutRes int layoutResId, @Nullable List<LiveBroadCast> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveBroadCast item) {
        helper.setText(R.id.tv_home_team_name,item.getHomeTeamName());
        helper.setText(R.id.tv_away_team_name, item.getAwayTeamName());
        helper.setText(R.id.tv_league_name,item.getLeagueName());
        helper.setText(R.id.tv_time_string, item.getTimeString());
        helper.setText(R.id.tv_score, item.getHomeScore()+" : "+item.getAwayScore());
        // 加载网络图片
        Glide.with(mContext).load(item.getHomeTeamLogo()).crossFade().into((ImageView) helper.getView(R.id.iv_home_team_logo));
        Glide.with(mContext).load(item.getAwayTeamLogo()).crossFade().into((ImageView) helper.getView(R.id.iv_away_team_logo));
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            ToastUtils.showShortToast("事件触发了 landscapes and nedes");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Utils.getContext().getResources().getColor(R.color.clickspan_color));
            ds.setUnderlineText(true);
        }
    };
}

