package com.shuwo.fbol.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by asus01 on 2017/10/24.
 */

public class BackLooks implements Parcelable {

    private String webUrl;
    private int time;
    private String title;
    private String image;
    private String playTimes;
    private String url;
    private String from;
    private boolean isWeb;
    private String hash;
    private String playType;

    protected BackLooks(Parcel in) {
        webUrl = in.readString();
        time = in.readInt();
        title = in.readString();
        image = in.readString();
        playTimes = in.readString();
        url = in.readString();
        from = in.readString();
        isWeb = in.readByte() != 0;
        hash = in.readString();
        playType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(webUrl);
        dest.writeInt(time);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(playTimes);
        dest.writeString(url);
        dest.writeString(from);
        dest.writeByte((byte) (isWeb ? 1 : 0));
        dest.writeString(hash);
        dest.writeString(playType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BackLooks> CREATOR = new Creator<BackLooks>() {
        @Override
        public BackLooks createFromParcel(Parcel in) {
            return new BackLooks(in);
        }

        @Override
        public BackLooks[] newArray(int size) {
            return new BackLooks[size];
        }
    };

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    public String getWebUrl() {
        return webUrl;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public int getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setPlayTimes(String playTimes) {
        this.playTimes = playTimes;
    }
    public String getPlayTimes() {
        return playTimes;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public String getFrom() {
        return from;
    }

    public void setIsWeb(boolean isWeb) {
        this.isWeb = isWeb;
    }
    public boolean getIsWeb() {
        return isWeb;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    public String getHash() {
        return hash;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }
    public String getPlayType() {
        return playType;
    }

}
