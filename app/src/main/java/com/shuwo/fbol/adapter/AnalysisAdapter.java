package com.shuwo.fbol.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.TextLengthUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.Util.Utils;
import com.shuwo.fbol.bean.HomeMatchs;

import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class AnalysisAdapter extends BaseQuickAdapter<HomeMatchs, BaseViewHolder> {

    public AnalysisAdapter(@LayoutRes int layoutResId, @Nullable List<HomeMatchs> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMatchs item) {
        helper.setText(R.id.league_name,item.getLeague_name());
        helper.setText(R.id.match_time, item.getMatch_time());
        helper.setText(R.id.home_guest_team_name,TextLengthUtil.textLengthTo5(item.getHome_team_name()) + item.getHome_team_score()+" : "+ item.getGuest_team_score() + TextLengthUtil.textLengthTo5(item.getGuest_team_name()));
        helper.setText(R.id.match_result, item.getMatch_result());

//        if("胜".equals(item.getMatch_result())){
//            helper.setTextColor(R.id.match_result, R.color.win);
//        }else  if("平".equals(item.getMatch_result())){
//            helper.setTextColor(R.id.match_result, R.color.tie);
//        }else  if("输".equals(item.getMatch_result())){
//            helper.setTextColor(R.id.match_result, R.color.lose);
//        }
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

