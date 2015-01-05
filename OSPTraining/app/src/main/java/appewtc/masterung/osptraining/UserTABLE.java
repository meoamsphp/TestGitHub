package appewtc.masterung.osptraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/5/15 AD.
 */
public class UserTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQlite, readSQlite;

    public static final String TABLE_USER = "userTABLE";
    public static final String COLUMN_ID_USER = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NAME = "Name";

    public UserTABLE(Context context) {

        //Call SQLite
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQlite = objMyOpenHelper.getWritableDatabase();
        readSQlite = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    //Update Record to SQLite
    public long updateUser(Context context, String strUser, String strPassword, String strName) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_NAME, strName);
        return writeSQlite.insert(TABLE_USER, null, objContentValues);

    }   // updateUser



}   // Main Class
