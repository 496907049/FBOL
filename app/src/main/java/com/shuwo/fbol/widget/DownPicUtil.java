package com.shuwo.fbol.widget;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by asus01 on 2017/9/29.
 */

public class DownPicUtil {

    public static void downPic(String url,DownFinishListener downFinishListener){
        // 获取存储卡的目录
        String filePath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filePath+File.separator+"webViewCache");
        if(!file.exists()){
            file.mkdir();
        }

        loadPic(file.getPath(),url,downFinishListener);

    }

    private static void loadPic(final String filePath, final String url, final DownFinishListener downFinishListener) {
        new AsyncTask<Void,Void,String>(){
            String fileName;
            InputStream is;
            OutputStream out;

            @Override
            protected String doInBackground(Void... voids) {

                // 下载文件的名称
                String[] split = url.split("/");
                fileName = split[split.length - 1];
                // 创建目标文件,不是文件夹
                File picFile = new File(filePath + File.separator + fileName);
                if(picFile.exists()){
                    return  picFile.getPath();
                }

                try {
                    URL picUrl = null;
                    try {
                        picUrl = new URL(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    //通过图片的链接打开输入流
                    is = picUrl.openStream();
                    if(is==null){
                        return null;
                    }
                    out = new FileOutputStream(picFile);
                    byte[] b=new byte[1024];
                    int end ;
                    while ((end=is.read(b))!=-1){
                        out.write(b,0,end);
                    }

                    if(is!=null){
                        is.close();
                    }

                    if(out!=null){
                        out.close();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return picFile.getPath();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s!=null){
                    downFinishListener.getDownPath(s);
                }
            }
        }.execute();
    }
    //下载完成回调的接口
    public interface DownFinishListener{
        void getDownPath(String s);
    }

}
