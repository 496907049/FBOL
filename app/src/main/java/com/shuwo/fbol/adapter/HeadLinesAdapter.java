package com.shuwo.fbol.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.DateUtil;
import com.shuwo.fbol.Util.RandomUtils;
import com.shuwo.fbol.Util.TextLengthUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.Util.Utils;
import com.shuwo.fbol.bean.HeadLines;
import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class HeadLinesAdapter extends BaseQuickAdapter<HeadLines, BaseViewHolder> {

    int[] img={R.drawable.zx01, R.drawable.zx02, R.drawable.zx03, R.drawable.zx04, R.drawable.zx05, R.drawable.zx06,
                R.drawable.zx07, R.drawable.zx08, R.drawable.zx09, R.drawable.zx10,
                R.drawable.zx11, R.drawable.zx12, R.drawable.zx13, R.drawable.zx14,
                R.drawable.zx15, R.drawable.zx16, R.drawable.zx17, R.drawable.zx18,
                R.drawable.zx19, R.drawable.zx20, R.drawable.zx21, R.drawable.zx22,
                R.drawable.zx23, R.drawable.zx24, R.drawable.zx25,
    };


    public HeadLinesAdapter(@LayoutRes int layoutResId, @Nullable List<HeadLines> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HeadLines item) {
        helper.setText(R.id.tv_info_title,TextLengthUtil.textLengthTo20(item.getInfo_title()));
        helper.setText(R.id.tv_type_name, item.getInfo_type_name());
        try {
            helper.setText(R.id.tv_crt_time,DateUtil.timedate2(item.getCrt_time()));
        }catch (Exception e){
            helper.setText(R.id.tv_crt_time,item.getInfo_time());
        }

        if(item.getInfo_pic()!=""){
            // 加载网络图片
            Glide.with(mContext).load(item.getInfo_pic())
                    .crossFade()
                    .into((ImageView) helper.getView(R.id.iv_info_pic)
                    );
        }else {
            Glide.with(mContext).load(img[RandomUtils.getRandom(0,img.length)])
                    .crossFade()
                    .into((ImageView) helper.getView(R.id.iv_info_pic)
                    );
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

