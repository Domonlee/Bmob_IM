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
    public static final String FILE_NAME = "test"; //��������ݿ��ļ���
//    public static final String PACKAGE_NAME = "com.huacheng.sqllist";//���̰���
    public static final String FILE_PATH = "/sdcard";  //���ֻ��������ݿ��λ��
    private Context context;
 
   public ImportDB(Context context) {
        this.context = context;
    }
   
    public void copyDatabase() {
             String dbfile=FILE_PATH + "/" + FILE_NAME ;
        try {
           //ִ�����ݿ⵼��
                InputStream is = this.context.getResources().getAssets().open("test.jpg"); //��������ļ�
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }              
                fos.close();//�ر������
                is.close();//�ر�������
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
