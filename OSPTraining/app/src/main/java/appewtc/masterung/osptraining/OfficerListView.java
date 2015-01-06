package appewtc.masterung.osptraining;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class OfficerListView extends ActionBarActivity {

    //Explicit
    private OfficerTABLE objOfficerTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_list_view);

        //Call Data
        objOfficerTABLE = new OfficerTABLE(this);

        //Delete All Data
        deleteAllData();

        //Syn JSON to SQLite
        synJSONtoSQLite();

    }   // onCreate

    private void synJSONtoSQLite() {

        //setUP Policy
        if (Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }   // if


        InputStream objInputStream = null;
        String strJSON = "";

        //Create InputStream
        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/osp/php_get_data_master_officer.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("osp", "Error from InputStream ==>  " + e.toString());
        }   // Create InputStream


        //Create strJSON
        try {

            BufferedReader objBufferedRader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferedRader.readLine()) != null) {

                objStringBuilder.append(strLine);

            }   // while

            objInputStream.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {
            Log.d("osp", "Error from strJSON ==> " + e.toString());
        }   //Create strJSON


        //Up New Data to SQLite
        try {

            final JSONArray objJSONArray = new JSONArray(strJSON);

            for (int i = 0; i < objJSONArray.length(); i++) {

                JSONObject objJSONObject = objJSONArray.getJSONObject(i);

                String strOfficer = objJSONObject.getString("Officer");
                String strPosition = objJSONObject.getString("Position");
                String strImage = objJSONObject.getString("Image");
                String strVideo = objJSONObject.getString("Video");

                long addValue = objOfficerTABLE.updateOfficer(OfficerListView.this, strOfficer, strPosition, strImage, strVideo);

            }   // for

        } catch (Exception e) {
            Log.d("osp", "Error from Up Data ==> " + e.toString());
        }   // up Data




    }   // synJSONtoSQLite

    private void deleteAllData() {

        SQLiteDatabase objSQLite = openOrCreateDatabase("Osp.db", MODE_PRIVATE, null);
        objSQLite.delete("officerTABLE", null, null);

    }  // deleteAllData


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_officer_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
