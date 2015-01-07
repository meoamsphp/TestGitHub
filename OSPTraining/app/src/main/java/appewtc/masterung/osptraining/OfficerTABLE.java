package appewtc.masterung.osptraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/5/15 AD.
 */
public class OfficerTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_OFFICER = "officerTABLE";
    public static final String COLUMN_ID_OFFICER = "_id";
    public static final String COLUMN_OFFICER = "Officer";
    public static final String COLUMN_POSITION = "Position";
    public static final String COLUMN_IMAGE = "Image";
    public static final String COLUMN_VIDEO = "Video";

    public OfficerTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    //Read All Data
    public Cursor readAllData() {

        Cursor objCursor = readSQLite.query(TABLE_OFFICER, new String[]{COLUMN_ID_OFFICER, COLUMN_OFFICER, COLUMN_POSITION, COLUMN_IMAGE, COLUMN_VIDEO}, null, null, null, null, null);

        if (objCursor != null) {
            objCursor.moveToFirst();
        }

        return objCursor;
    }   // readAllData



    public long updateOfficer(Context context, String strOfficer, String strPosition, String strImage, String strVideo) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        objContentValues.put(COLUMN_POSITION, strPosition);
        objContentValues.put(COLUMN_IMAGE, strImage);
        objContentValues.put(COLUMN_VIDEO, strVideo);

        return writeSQLite.insert(TABLE_OFFICER, null, objContentValues);
    }   //updateOfficer

}   // Main Class
