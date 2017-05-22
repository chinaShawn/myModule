package com.zte.shawn.lotterycheck;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.zte.shawn.lotterycheck.bean.DaoMaster;
import com.zte.shawn.lotterycheck.bean.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by 10192984 on 2016/10/29.
 */
public class MyApp extends Application {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "lottery-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        instance = this;
        Stetho.initializeWithDefaults(this);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static MyApp getMyApp(){
        return  instance;
    }


}