package com.zte.shawn.persistence;

import android.content.Context;
import android.os.Environment;

/**
 * Created by 10192984 on 2017/2/10.
 */
public class PersistPathManager {
    private Context context;
    private static PersistPathManager instance;

    private String privateExternalPath;
    private String publicExternalPath;
    private String externalCachePaht;


    private PersistPathManager(Context context) {
        this.context = context;
        privateExternalPath = context.getExternalFilesDir(null).getAbsolutePath();
        publicExternalPath= Environment.getExternalStoragePublicDirectory("lotteryCheck").getAbsolutePath();
        externalCachePaht = context.getExternalCacheDir().getAbsolutePath();
    }

    public static PersistPathManager  getInstance(Context context){
        if(instance ==null){
            synchronized (PersistPathManager.class){
                if(instance==null){
                    instance = new PersistPathManager(context);
                }
            }
        }
        return instance;
    }

    public String privateExternalPath(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return privateExternalPath;
        } else{
            return "";
        }
    }
}
