package appewtc.masterung.osptraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    // Search User
    public String[] searchUser(String strSearch) {

        try {

            String arrayDATA[] = null;
            Cursor objCursor = readSQlite.query(TABLE_USER, new String[]{COLUMN_ID_USER, COLUMN_USER, COLUMN_PASSWORD, COLUMN_NAME}, COLUMN_USER + "=?", new String[]{String.valueOf(strSearch)}, null, null, null, null);

            if (objCursor != null) {

                if (objCursor.moveToFirst()) {

                    arrayDATA = new String[objCursor.getColumnCount()];
                    arrayDATA[0] = objCursor.getString(0);
                    arrayDATA[1] = objCursor.getString(1);
                    arrayDATA[2] = objCursor.getString(2);
                    arrayDATA[3] = objCursor.getString(3);

                }   // if2

            }   // if1

            objCursor.close();
            return arrayDATA;

        } catch (Exception e) {
            return null;
        }

       // return new String[0];
    }   // searchUser




    //Update Record to SQLite
    public long updateUser(Context context, String strUser, String strPassword, String strName) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_NAME, strName);
        return writeSQlite.insert(TABLE_USER, null, objContentValues);

    }   // updateUser



}   // Main Class
