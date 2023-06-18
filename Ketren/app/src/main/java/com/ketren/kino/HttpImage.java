package com.ketren.kino;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpImage {


    public HttpImage(){

    }
    /***
     * 从网络中获取图片信息，以流的形式返回
     */
      public static InputStream getImageViewInputStream(String u)throws IOException{
        InputStream inputStream = null;
		URL url = new URL(u);
		if(url != null){
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setConnectTimeout(3000);//设置连接超时时间
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setDoInput(true);
			int response_code = httpURLConnection.getResponseCode();
			if(response_code == 200){
				inputStream = httpURLConnection.getInputStream();
			}
		}


        return inputStream;
    }

    /***
     * 从网络中获取图片信息，以字节数组的形式返回
     */
    public static byte[] getImageViewArray(String u){
        byte[] data = null;
        InputStream inputStream = null;
        //ByteArrayOutputStream不需要关闭的输出流，直接写入到内存中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            URL url = new URL(u);
            if(url != null){
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setConnectTimeout(3000);//设置连接超时时间
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                int response_code = httpURLConnection.getResponseCode();
                int len = 0;
                byte[] b_data = new byte[1024];
                if(response_code == 200){
                    inputStream = httpURLConnection.getInputStream();
                    while((len = inputStream.read(b_data)) != -1){
                        outputStream.write(b_data,0,len);
                    }
                    data = outputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return data;
    }
}
