package appewtc.masterung.osptraining;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/5/15 AD.
 */
public class UserTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQlite, readSQlite;

    public UserTABLE(Context context) {

        //Call SQLite
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQlite = objMyOpenHelper.getWritableDatabase();
        readSQlite = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

}   // Main Class
