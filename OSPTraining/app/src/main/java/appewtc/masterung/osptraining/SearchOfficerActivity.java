package appewtc.masterung.osptraining;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class SearchOfficerActivity extends ActionBarActivity {

    //Explicit
    private EditText edtSearchOfficer;
    private String strOfficerChoose, strPosition, strImage, strVideo;
    private OfficerTABLE objOfficerTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_officer);

        //Initial Widget
        edtSearchOfficer = (EditText) findViewById(R.id.edtSearchOfficer);

    }   // onCreate


    public void clickSearchOfficer(View view) {

        strOfficerChoose = edtSearchOfficer.getText().toString().trim();

        //Check Zero
        if (strOfficerChoose.equals("")) {
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.nagativeDialog(SearchOfficerActivity.this, "Have Space", "Please Fill in the Blank");
        } else {

            searchResult();

        }   //Check Zero

    }   // clickSearchOfficer

    private void searchResult() {

        try {

            objOfficerTABLE = new OfficerTABLE(this);
            String resultARRAY[] = objOfficerTABLE.searchOfficer(strOfficerChoose);
            strPosition = resultARRAY[2];
            strImage = resultARRAY[3];
            strVideo = resultARRAY[4];

            //Intent to DetailActivity
            Intent objIntent = new Intent(SearchOfficerActivity.this, DetailActivity.class);
            objIntent.putExtra("strOfficer", strOfficerChoose);
            objIntent.putExtra("strPosition", strPosition);
            objIntent.putExtra("strImage", strImage);
            objIntent.putExtra("strVideo", strVideo);
            startActivity(objIntent);

        } catch (Exception e) {
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.nagativeDialog(SearchOfficerActivity.this, "No Officer", "No " + strOfficerChoose + " in my Database" + "\n" + "Please Try Agains");
            edtSearchOfficer.setText("");
        }

    }   // searchResult


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_officer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemListAll:
                Intent objIntent = new Intent(SearchOfficerActivity.this, OfficerListView.class);
                startActivity(objIntent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
