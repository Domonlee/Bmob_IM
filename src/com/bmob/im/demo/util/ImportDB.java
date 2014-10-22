package com.bmob.im.demo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class ImportDB{
    private final int BUFFER_SIZE = 10000;
    public static final String FILE_NAME = "test"; //保存的数据库文件名
//    public static final String PACKAGE_NAME = "com.huacheng.sqllist";//工程包名
    public static final String FILE_PATH = "/sdcard";  //在手机里存放数据库的位置
    private Context context;
 
   public ImportDB(Context context) {
        this.context = context;
    }
   
    public void copyDatabase() {
             String dbfile=FILE_PATH + "/" + FILE_NAME ;
        try {
           //执行数据库导入
                InputStream is = this.context.getResources().getAssets().open("test.jpg"); //欲导入的文件
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }              
                fos.close();//关闭输出流
                is.close();//关闭输入流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
