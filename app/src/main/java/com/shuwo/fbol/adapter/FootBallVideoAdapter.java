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
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.DateUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.Util.Utils;
import com.shuwo.fbol.bean.FootBallVideo;
import com.shuwo.fbol.bean.HighLights;

import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class FootBallVideoAdapter extends BaseQuickAdapter<FootBallVideo, BaseViewHolder> {

    public FootBallVideoAdapter(@LayoutRes int layoutResId, @Nullable List<FootBallVideo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FootBallVideo item) {
        helper.setText(R.id.tv_video_title,item.getTitle());
        helper.setVisible(R.id.tv_video_name,false);
        helper.setText(R.id.tv_video_time,item.getCreateTime());
        // 加载网络图片
        Glide.with(mContext).load(Contants.FOOTBALL_VIDEO_PIC_URL+item.getPicUrl()).crossFade().into((ImageView) helper.getView(R.id.icon));
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

