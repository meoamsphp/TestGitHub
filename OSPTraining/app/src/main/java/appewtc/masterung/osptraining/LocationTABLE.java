package appewtc.masterung.osptraining;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/5/15 AD.
 */
public class LocationTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_LOCATION = "locationTABLE";
    public static final String COLUMN_ID_LOCATION = "_id";
    public static final String COLUMN_PLACE = "Place";
    public static final String COLUMN_LAT = "Lat";
    public static final String COLUMN_LNG = "Lng";
    public static final String COLUMN_ICON = "Icon";

    public LocationTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public long updataLocation(Context context, String strPlace, double douLat, double douLng, int intIcon) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_PLACE, strPlace);
        objContentValues.put(COLUMN_LAT, douLat);
        objContentValues.put(COLUMN_LNG, douLng);
        objContentValues.put(COLUMN_ICON, intIcon);
        return writeSQLite.insert(TABLE_LOCATION, null, objContentValues);
    }   // updateLocation

}   // Main Class
