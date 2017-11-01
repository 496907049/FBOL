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
import com.shuwo.fbol.Util.DateUtil;
import com.shuwo.fbol.Util.TextLengthUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.Util.Utils;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.ScoreItem;

import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class ScoreItemAdapter extends BaseQuickAdapter<ScoreItem, BaseViewHolder> {

    public ScoreItemAdapter(@LayoutRes int layoutResId, @Nullable List<ScoreItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScoreItem item) {
        helper.setText(R.id.league_name,item.getLeague_name());
        helper.setText(R.id.match_time, item.getMatch_time());
        helper.setText(R.id.home_team_name,TextLengthUtil.textLengthTo5(item.getHome_team_name()));
        helper.setText(R.id.home_team_score,item.getHome_team_score());
        helper.setText(R.id.guest_team_score,item.getGuest_team_score());
        helper.setText(R.id.guest_team_name,TextLengthUtil.textLengthTo5(item.getGuest_team_name()));

        if (checkIsNull(item.getHome_red_card())){
            helper.setText(R.id.home_red_card,item.getHome_red_card());
            helper.setVisible(R.id.home_red_card,true);
        }else {
            helper.setVisible(R.id.home_red_card,false);
        }

        if (checkIsNull(item.getGuest_red_card())){
            helper.setText(R.id.guest_red_card,item.getGuest_red_card());
            helper.setVisible(R.id.guest_red_card,true);
        }else {
            helper.setVisible(R.id.guest_red_card,false);
        }

        if (checkIsNull(item.getH_yellow())){
            helper.setText(R.id.h_yellow,item.getH_yellow());
            helper.setVisible(R.id.h_yellow,true);
        }else {
            helper.setVisible(R.id.h_yellow,false);
        }

        if (checkIsNull(item.getG_yellow())){
            helper.setText(R.id.g_yellow,item.getG_yellow());
            helper.setVisible(R.id.g_yellow,true);
        }else {
            helper.setVisible(R.id.g_yellow,false);
        }

        try{
            helper.setText(R.id.asian_odds,item.getAsian_odds());
        }catch (Exception e){
            helper.setVisible(R.id.asian_odds,false);
        }

        try{
            helper.setText(R.id.europe_odds,item.getEurope_odds());
        }catch (Exception e){
            helper.setVisible(R.id.europe_odds,false);
        }


    }

    public boolean checkIsNull(String a){
        if(a==null ||"0".equals(a)){
            return false;
        }else {
            return true;
        }
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

