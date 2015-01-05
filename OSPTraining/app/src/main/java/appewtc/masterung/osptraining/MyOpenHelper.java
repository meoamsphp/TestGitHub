package appewtc.masterung.osptraining;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 1/5/15 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    //Explicit
    private static final String DATABASE_NAME = "Osp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_USER = "create table userTABLE (_id integer primary key, " + " User text, Password text, Name text);";
    private static final String CREATE_TABLE_OFFICER = "create table officerTABLE (_id integer primary key, " + " Officer text, Position text, Image text, Video text);";
    private static final String CREATE_TABLE_LOCATION = "create table locationTABLE (_id integer primary key, " + " Place text, Lat double, Lng double, Icon integer);";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_OFFICER);
        db.execSQL(CREATE_TABLE_LOCATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   // Main Class
