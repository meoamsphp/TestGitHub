package appewtc.masterung.downloadimageurl;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private String strMyImageURL = "https://farm6.staticflickr.com/5546/10306091065_1dd4531d50_o.jpg";
    private ImageView imvShowImage;
    private ProgressDialog objProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        imvShowImage = (ImageView) findViewById(R.id.imageView);

    }   // onCreate

    public void clickShowImage(View view) {

        //Execute DownloadImage AsyncTask
        new DownloadImage().execute(strMyImageURL);

    }   // clickShowImage

    //Create DownloadImage AsyncTask Class
    private class DownloadImage extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Create Progressdialog
            objProgressDialog = new ProgressDialog(MainActivity.this);
            objProgressDialog.setTitle("Download Image");
            objProgressDialog.setMessage("Load Image Wait Please");
            objProgressDialog.setIndeterminate(false);
            objProgressDialog.show();
        }   // onPreExecute

        @Override
        protected Bitmap doInBackground(String... params) {

            String strImageURL = params[0];
            Bitmap objBitmap = null;

            try {
                //Download Image from URL
                //InputStream objInputStream = new java.net.URL(strImageURL).openStream();
                InputStream objInputStream = new URL(strImageURL).openStream();
                //Decode Bitmap
                objBitmap = BitmapFactory.decodeStream(objInputStream);

            } catch (Exception e) {
                e.printStackTrace();
            }   // try

            return objBitmap;
        }   // doInBackground

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // super.onPostExecute(bitmap);
            //Set bitmap to ImageView
            imvShowImage.setImageBitmap(bitmap);
            objProgressDialog.dismiss();
        }   // onPOstExecute

    }   // DownloadImage Class

}   // Main Class
