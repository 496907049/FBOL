package com.shuwo.fbol.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.shuwo.fbol.R;
import com.shuwo.fbol.activity.VideoViewActivity;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.widget.MyTextSliderView;
import com.shuwo.fbol.widget.VideoTextSliderView;

import java.util.ArrayList;
import java.util.List;

import static com.shuwo.fbol.R.mipmap.lunbo1;
import static com.shuwo.fbol.R.mipmap.lunbo2;

/**
 * Created by asus01 on 2017/10/20.
 */

public class ImageSliderUtil{

    private static  View imageSliderView;
    private static SliderLayout sliderLayout;
    private static PagerIndicator indicator;

    public static View initImageSlider(Context context, List<Integer> imageUrlList, List<String> titleList) {
         imageSliderView = View.inflate(context, R.layout.fragment_home_imge_slider, null);
         sliderLayout = (SliderLayout) imageSliderView.findViewById(R.id.slider);
         indicator = (PagerIndicator) imageSliderView.findViewById(R.id.custom_indicator);
        //准备好要显示的数据
        List<Integer> imageUrls = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        for (Integer i : imageUrlList) {
            imageUrls.add(i);
        }
        for (String tile : titleList) {
            descriptions.add("" + tile);
        }
        for (int i = 0; i < imageUrls.size(); i++) {
            //新建三个展示View，并且添加到SliderLayout
            MyTextSliderView tsv = new MyTextSliderView(context);
            tsv.image(imageUrls.get(i)).description(descriptions.get(i));
            final int finalI = i;
            tsv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
//                    Toast.makeText(getActivity(), descriptions.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            sliderLayout.addSlider(tsv);
        }

        //对SliderLayout进行一些自定义的配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setDuration(3000);
        //      sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomIndicator(indicator);

        return imageSliderView;
    }

    public static View initImageSlider2(final Activity activity, Context context, final List<HighLights> beanList) {
         imageSliderView = View.inflate(context, R.layout.fragment_home_imge_slider, null);
        ImageView videoGo = (ImageView) imageSliderView.findViewById(R.id.iv_slider_video_go);
        videoGo.setVisibility(View.VISIBLE);
         sliderLayout = (SliderLayout) imageSliderView.findViewById(R.id.slider);
         indicator = (PagerIndicator) imageSliderView.findViewById(R.id.custom_indicator);
        //准备好要显示的数据
        List<String> imageUrls = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            try {
                imageUrls.add(beanList.get(i).getVideos().get(0).getThumb());
                descriptions.add(beanList.get(i).getTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < imageUrls.size(); i++) {
            //新建三个展示View，并且添加到SliderLayout
            VideoTextSliderView tsv = new VideoTextSliderView(context);
            tsv.image(imageUrls.get(i)).description(descriptions.get(i));
            final int finalI = i;
            tsv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(activity, VideoViewActivity.class);
                    intent.putExtra("url", beanList.get(finalI).getVideos().get(0).getUrl());
                    activity.startActivity(intent);
                }
            });

            sliderLayout.addSlider(tsv);
        }

        //对SliderLayout进行一些自定义的配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setDuration(3000);
        //      sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomIndicator(indicator);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) sliderLayout.getLayoutParams();
        params.height = 400;
        imageSliderView.setLayoutParams(params);

        return imageSliderView;
    }


}
