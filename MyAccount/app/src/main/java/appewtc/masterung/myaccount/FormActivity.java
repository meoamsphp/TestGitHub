package appewtc.masterung.myaccount;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormActivity extends ActionBarActivity {

    private boolean bolClick;
    private TextView txtShowTitle, txtShowTime;
    private String strTime;
    private EditText edtType, edtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initialWidget();

        checkIntentClick();

        getTimeToShow();

    }   // onCreate

    private void getTimeToShow() {

        DateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date curentData = new Date();
        strTime = myDateFormat.format(curentData);
        txtShowTime.setText(strTime);

    }   // getTimeToShow

    private void initialWidget() {
        txtShowTitle = (TextView) findViewById(R.id.txtShowTitle);
        txtShowTime = (TextView) findViewById(R.id.txtShowTime);
        edtType = (EditText) findViewById(R.id.editText);
        edtData = (EditText) findViewById(R.id.editText2);
    }   // initialWidget

    private void checkIntentClick() {
        bolClick = getIntent().getExtras().getBoolean("bolClick");
        if (bolClick) {
            txtShowTitle.setText(FormActivity.this.getString(R.string.revenue));
            edtType.setHint(FormActivity.this.getString(R.string.revenue));
        } else {
            txtShowTitle.setText(FormActivity.this.getString(R.string.expend));
            edtType.setHint(FormActivity.this.getString(R.string.expend));
        }
    }   // checkIntentClick


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
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
