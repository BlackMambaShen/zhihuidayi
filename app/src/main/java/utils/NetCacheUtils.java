package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetCacheUtils {
    public void getBitmapFromNet(ImageView imageView,String url){
        //AsyncTask异步封装工具，可以实现异步请求及主界面更新（线程池+handler的封装）
        new BitmapTask().execute(imageView,url);//启动AsyncTask
    }

    /*
    3个泛型：
    第一个：代表doInBackground里的参数类型
    第二个：onProgressUpdate里的参数类型
    第三个：onPostExecute里的参数类型及doInBackground的返回类型
     */
    class BitmapTask extends AsyncTask<Object,Integer,Bitmap>{

        private ImageView imageView;
        private String url;

        //正在加载，运行在子线程(核心方法),可以直接异步请求
        protected Bitmap doInBackground(Object... objects) {
             imageView = (ImageView) objects[0];
              url = (String) objects[1];
            imageView.setTag(url);//打标记，绑定在一起
            //开始下载图片
            Bitmap bitmap=download(url);
            return bitmap;
        }

        //加载结束，运行在主线程(核心方法),可以直接更新UI
        protected void onPostExecute(Bitmap result) {
            if (result!=null){
                String url = (String) imageView.getTag();
                if (url.equals(this.url)){
                    imageView.setImageBitmap(result);
                }
            }
            super.onPostExecute(result);
        }

        //预加载,主线程
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //进度更新，运行在主线程
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);
        }
    }

    private Bitmap download(String url) {
        HttpURLConnection connection=null;
        try {
             connection = (HttpURLConnection) new URL(url).openConnection();
             connection.setRequestMethod("GET");
             connection.setReadTimeout(5000);
             connection.setConnectTimeout(5000);
             connection.connect();
            int code = connection.getResponseCode();
            if (code==200){
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.disconnect();
            }
        }
        return null;
    }
}
