package appewtc.masterung.osptraining;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


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



    }   // onCreate

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
