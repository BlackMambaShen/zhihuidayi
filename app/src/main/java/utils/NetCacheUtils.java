package utils;

import android.os.AsyncTask;
import android.widget.ImageView;

public class NetCacheUtils {
    public void getBitmapFromNet(ImageView imageView,String url){
        //AsyncTask异步封装工具，可以实现异步请求及主界面更新（线程池+handler的封装）
    }

    class BitmapTask extends AsyncTask<Void,Void,Void>{

        //正在加载，运行在子线程(核心方法),可以直接异步请求
        protected Void doInBackground(Void... voids) {
            return null;
        }

        //加载结束，运行在主线程(核心方法),可以直接更新UI
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        //预加载,主线程
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //进度更新，运行在主线程
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
