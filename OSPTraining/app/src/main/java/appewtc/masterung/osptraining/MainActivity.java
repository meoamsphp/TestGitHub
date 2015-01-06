package appewtc.masterung.osptraining;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText edtUser, edtPassword;
    private String strUserChoose, strPasswordChoose, strPasswordTrue, strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Call Data
        objUserTABLE = new UserTABLE(this);
        objOfficerTABLE = new OfficerTABLE(this);
        objLocationTABLE = new LocationTABLE(this);

        //Tester
        //testerAddData();

        //Delete All Data
        deleteAllData();

        //Syn JSON to SQLite
        synJSONtoSQLite();


    }   // onCreate

    private void bindWidget() {
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }   // bindWidget


    //Event Click Login Button
    public void clickLogin(View view) {

        //Check Zero
        strUserChoose = edtUser.getText().toString().trim();
        strPasswordChoose = edtPassword.getText().toString().trim();
        if (strUserChoose.equals("") || strPasswordChoose.equals("") ) {
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.nagativeDialog(MainActivity.this, "มีช่องว่าง", "กรุณา กรอกทุกช่อง");
        } else {

            //Check User
            checkUser();

        }   // Check Zero

    }   // clickLogin

    private void checkUser() {

        try {

            String arrayDATA[] = objUserTABLE.searchUser(strUserChoose);

            strPasswordTrue = arrayDATA[2];
            strName = arrayDATA[3];

            Log.d("osp", "Name ==> " + strName);

            //Check Password
            checkPassword();

        } catch (Exception e) {
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.nagativeDialog(MainActivity.this, "No This User", "No " + strUserChoose + " in my Database");
        }

    }   // checkUser

    private void checkPassword() {



    }   //checkPassword


    private void deleteAllData() {

        SQLiteDatabase objSQLite = openOrCreateDatabase("Osp.db", MODE_PRIVATE, null);
        objSQLite.delete("userTABLE", null, null);

    }   // deleteAllData

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

        switch (item.getItemId()) {

            case R.id.itemSynJSON:
                deleteAllData();
                synJSONtoSQLite();
                Toast.makeText(MainActivity.this, "ซิ้งโครไนซ์ เรียบร้อยแล้วคะ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.itemCheckInternet:
                checkInternet();
                break;

            case R.id.itemHelpMe:
                Intent objIntent = new Intent(MainActivity.this, HelpMeActivity.class);
                startActivity(objIntent);
                break;

            case R.id.itemAboutMe:
                Intent myWebView = new Intent(Intent.ACTION_VIEW);
                myWebView.setData(Uri.parse("http://androidthai.in.th/about-me.html"));
                startActivity(myWebView);
                break;

        }   // switch

        return super.onOptionsItemSelected(item);
    }

    private void checkInternet() {

        ConnectivityManager objConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo objNetworkInfo = objConnectivityManager.getActiveNetworkInfo();

        if (objNetworkInfo != null && objNetworkInfo.isConnected()) {
            Toast.makeText(MainActivity.this, "Internet OK", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Cannot Connected Internet", Toast.LENGTH_SHORT).show();
        }

    }   // checkInternet
}   // Main Class









