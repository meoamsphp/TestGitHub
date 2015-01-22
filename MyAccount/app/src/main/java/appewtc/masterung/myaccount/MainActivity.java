package appewtc.masterung.myaccount;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private RevenueTABLE objRevenueTABLE;
    private ExpendTABLE objExpendTABLE;
    private Boolean bolClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectedMyDatabase();

    }   // onCreate

    public void clickRevenue(View view) {
        bolClick = true;
        myIntentToForm();
    }   // clickRevenue

    private void myIntentToForm() {

        Intent objIntent = new Intent(MainActivity.this, FormActivity.class);
        objIntent.putExtra("bolClick", bolClick);
        startActivity(objIntent);

    }   // myIntentToForm

    public void clickExpend(View view) {
        bolClick = false;
        myIntentToForm();
    }   // clickExpend

    private void connectedMyDatabase() {

        objExpendTABLE = new ExpendTABLE(this);
        objRevenueTABLE = new RevenueTABLE(this);

    }   // connectedMyDatabase


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
