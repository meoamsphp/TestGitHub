package appewtc.masterung.osptraining;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class FormPositionActivity extends ActionBarActivity {

    //Explicit
    private EditText edtPlace;
    private RadioGroup ragChoice;
    private RadioButton radHome, radOffice, radSchool;
    private TextView txtLat, txtLng;
    private Double douLat, douLng;
    private String strLat, strLng, strPlace;
    private int intIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_position);

        //Bind Widget
        bindWidget();

        //Get Value From Intent
        MyGetValueFromIntent();

        //Get Value From RadioButton
        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radHome:
                        intIcon = 1;
                        break;
                    case R.id.radOffice:
                        intIcon = 2;
                        break;
                    case R.id.radSchool:
                        intIcon = 3;
                        break;
                }   // switch
            }   // event
        });


    }   // onCreate

    private void MyGetValueFromIntent() {

        douLat = getIntent().getExtras().getDouble("Lat");
        douLng = getIntent().getExtras().getDouble("Lng");

//        strLat = String.valueOf(douLat);
//        strLng = String.valueOf(douLng);

        strLat = String.format("%.6f", douLat);
        strLng = String.format("%.6f", douLng);

        txtLat.setText("Lat = " + strLat);
        txtLng.setText("Lng = " + strLng);

    }   // MyGetValueFromIntent


    public void clickSave(View view) {

        strPlace = edtPlace.getText().toString().trim();
        if (strPlace.equals("")) {
            strPlace = "Not Thing";
        }   // if

        confirmSave();

    }   // clickSave

    private void confirmSave() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.icon_build);
        objAlert.setTitle("Confirm");
        objAlert.setMessage("Plate =" + strPlace + "\n"
                + "Icon = " + Integer.toString(intIcon)+ "\n"
                + "Lat = " + strLat +"\n"
                + "Lng = " + strLng);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                upValueToMySQL();
            }
        });
        objAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    }   // confirmSave

    private void upValueToMySQL() {

        //setUP Policy
        if (Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }   // if


        try {

            ArrayList<NameValuePair> objArrayList = new ArrayList<NameValuePair>();
            objArrayList.add(new BasicNameValuePair("isAdd", "true"));
            objArrayList.add(new BasicNameValuePair("Place", strPlace));
            objArrayList.add(new BasicNameValuePair("Lat", strLat));
            objArrayList.add(new BasicNameValuePair("Lng", strLng));
            objArrayList.add(new BasicNameValuePair("Icon", String.valueOf(intIcon)));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/osp/php_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objArrayList, "UTF-8"));
            objHttpClient.execute(objHttpPost);

        } catch (Exception e) {
            Log.d("osp", "Error from upValue ==> " + e.toString());
        }


    }   //upValueToMySQL


    private void bindWidget() {

        edtPlace = (EditText) findViewById(R.id.edtPlace);
        ragChoice = (RadioGroup) findViewById(R.id.ragChoice);
        radHome = (RadioButton) findViewById(R.id.radHome);
        radOffice = (RadioButton) findViewById(R.id.radOffice);
        radSchool = (RadioButton) findViewById(R.id.radSchool);
        txtLat = (TextView) findViewById(R.id.txtLat);
        txtLng = (TextView) findViewById(R.id.txtLng);

    }   // bindWidget


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_position, menu);
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
