package appewtc.masterung.myaccount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 1/22/15 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Account.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_REVENUE_TABLE = "create table revenueTABLE (_id integer primary key, Time text, Revenue double);";
    private static final String CREATE_EXPAND_TABLE = "create table extendTABLE (_id integer primary key, Time text, Expend double);";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REVENUE_TABLE);
        db.execSQL(CREATE_EXPAND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   // Main Class
