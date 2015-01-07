package appewtc.masterung.osptraining;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


public class DetailActivity extends ActionBarActivity {

    //Explicit
    private TextView txtShowOfficer, txtShowPosition;
    private ImageView imvShowOfficer;
    private String strOfficer, strPosition, strImageURL, strVideoURL;
    private ProgressDialog objPrograssDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Initial Widget
        initialWidget();

        //Get Value from Intent
        myGetValueFromIntent();

        //Set Up TextView
        setUpTextView();

        //Download Image From URL
        downloadImageFromURL();


    }   // onCreate


    //Create AsyncTask Class
    private class DownLoadImage extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {

            String strImageURL = params[0];
            Bitmap objBitmap = null;

            try {

                //Down Image form URL
                //new java.net.URL ==>
                InputStream objInputStream = new URL(strImageURL).openStream();
                //Decode Bitmap
                objBitmap = BitmapFactory.decodeStream(objInputStream);

            } catch (Exception e) {
                Log.d("osp", "Create AsyncTask ==> " + e.toString());
            }   // try

            return objBitmap;
        }   // doInBackground


        //Event Load Image


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Create ProgressDialog
            objPrograssDialog = new ProgressDialog(DetailActivity.this);
            objPrograssDialog.setTitle("Download Image");
            objPrograssDialog.setMessage("Please Wait few times");
            objPrograssDialog.setIndeterminate(false);
            objPrograssDialog.show();

        }   // onPreExecute


        //Finish Download ??


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //super.onPostExecute(bitmap);

            imvShowOfficer.setImageBitmap(bitmap);
            objPrograssDialog.dismiss();

        }   // onPost

    }   //DownLoadImage Class




    private void downloadImageFromURL() {

        new DownLoadImage().execute(strImageURL);

    }   // downloadImageFromURL

    private void setUpTextView() {

        txtShowOfficer.setText(strOfficer);
        txtShowPosition.setText(strPosition);

    }   // setUpTextView

    private void myGetValueFromIntent() {

        strOfficer = getIntent().getExtras().getString("strOfficer");
        strPosition = getIntent().getExtras().getString("strPosition");
        strImageURL = getIntent().getExtras().getString("strImage");
        strVideoURL = getIntent().getExtras().getString("strVideo");

    }   //myGetValueFromIntent

    private void initialWidget() {

        txtShowOfficer = (TextView) findViewById(R.id.txtShowOfficer);
        txtShowPosition = (TextView) findViewById(R.id.txtShowPosition);
        imvShowOfficer = (ImageView) findViewById(R.id.imvShowImage);

    }   //initialWidget

    public void clickVideo(View view) {

    }   // clickvideo

    public void clickReadAll(View view) {

        Intent objIntent = new Intent(DetailActivity.this, OfficerListView.class);
        startActivity(objIntent);
        finish();

    }   // clickReadAll

    public void clickSearchData(View view) {

    }   // clickSearchData


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
