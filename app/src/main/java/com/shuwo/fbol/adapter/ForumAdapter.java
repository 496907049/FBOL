package com.shuwo.fbol.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.DateUtil;
import com.shuwo.fbol.Util.TextLengthUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.Util.Utils;
import com.shuwo.fbol.bean.Forum;

import java.util.List;

/**
 * Created by asus01 on 2017/9/22.
 */

public class ForumAdapter extends BaseQuickAdapter<Forum, BaseViewHolder> {

    public ForumAdapter(@LayoutRes int layoutResId, @Nullable List<Forum> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Forum item) {
        helper.setText(R.id.tv1, DateUtil.timedate(String.valueOf(item.getCreation())));
        helper.setText(R.id.tv2, item.getTitle());
        helper.setText(R.id.tv3, ""+item.getLike()+"人点赞");
        helper.setText(R.id.tv4,  ""+item.getCommentCount()+"人回复");
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

