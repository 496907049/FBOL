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
import com.shuwo.fbol.bean.NBA;

import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class NBAAdapter extends BaseQuickAdapter<NBA, BaseViewHolder> {

    public NBAAdapter(@LayoutRes int layoutResId, @Nullable List<NBA> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NBA item) {
        helper.setText(R.id.tv_info_title,TextLengthUtil.textLengthTo10(item.getNote_title()));
        helper.setText(R.id.tv_type_name, item.getChannel_name());
        helper.setText(R.id.tv_crt_time,item.getCrt_time());
        // 加载网络图片
        Glide.with(mContext).load(item.getShare_pic()).crossFade().into((ImageView) helper.getView(R.id.iv_info_pic));
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

