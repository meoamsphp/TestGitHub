package appewtc.masterung.osptraining;

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


public class MainActivity extends ActionBarActivity {

    //Explicit
    private UserTABLE objUserTABLE;
    private OfficerTABLE objOfficerTABLE;
    private LocationTABLE objLocationTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call Data
        objUserTABLE = new UserTABLE(this);
        objOfficerTABLE = new OfficerTABLE(this);
        objLocationTABLE = new LocationTABLE(this);

        //Tester
        testerAddData();

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
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/osp/php_get_data_master.php");
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
                String strUser = objJSONObject.getString("User");
                String strPassword = objJSONObject.getString("Password");
                String strName = objJSONObject.getString("Name");

                long addUser = objUserTABLE.updateUser(MainActivity.this, strUser, strPassword, strName);

            }   // for

        } catch (Exception e) {
            Log.d("osp", "Error from Up Data ==> " + e.toString());
        }   // up Data



    }   // synJSONtoSQLite

    private void testerAddData() {

        objUserTABLE.updateUser(MainActivity.this, "testUser", "testPassword", "testName");
        objOfficerTABLE.updateOfficer(MainActivity.this, "testOfficer", "testPosition", "testImage", "testVideo");
        objLocationTABLE.updataLocation(MainActivity.this, "testPlace", 123.456, 654.321, 5);

    }   // testerAddData


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
