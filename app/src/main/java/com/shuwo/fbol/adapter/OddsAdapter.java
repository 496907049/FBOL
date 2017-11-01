package com.shuwo.fbol.adapter;

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
import com.shuwo.fbol.bean.Odds;

import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class OddsAdapter extends BaseQuickAdapter<Odds, BaseViewHolder> {

    public OddsAdapter(@LayoutRes int layoutResId, @Nullable List<Odds> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Odds item) {
        helper.setText(R.id.company_name,item.getCompany_name());
        helper.setText(R.id.first_even_odds,item.getFirst_even_odds());
        helper.setText(R.id.first_lost_odds,item.getFirst_lost_odds());
        helper.setText(R.id.first_win_odds,item.getFirst_win_odds());
        helper.setText(R.id.lost_odds,item.getLost_odds());
        helper.setText(R.id.lost_odds_chg,""+item.getLost_odds_chg());
        helper.setText(R.id.win_odds,item.getWin_odds());
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

