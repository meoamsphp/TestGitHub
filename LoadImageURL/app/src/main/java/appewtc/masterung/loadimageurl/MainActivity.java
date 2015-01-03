package appewtc.masterung.loadimageurl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private ImageView imvShow;

    public String strImageURL = "https://farm6.staticflickr.com/5542/10495237345_b51704a91d_z.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        imvShow = (ImageView) findViewById(R.id.imageView);

        GetXMLTask objGetXMLTask = new GetXMLTask();
        objGetXMLTask.execute(new String[] {strImageURL});

    }   // onCreate

    private class GetXMLTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap objBitmap = null;
            for (String strUrl : params) {
                objBitmap = downloadImage(strUrl);
            }   // for

            return objBitmap;
        }   // doInBackground

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imvShow.setImageBitmap(bitmap);
        }   // onPostExecute

        private Bitmap downloadImage(String strUrl) {

            Bitmap objBitmap = null;
            InputStream objInputStream = null;
            BitmapFactory.Options objBitmapFactory = new BitmapFactory.Options();
            objBitmapFactory.inSampleSize = 1;

            try {

                objInputStream = getHttpConnection(strUrl);
                objBitmap = BitmapFactory.decodeStream(objInputStream, null, objBitmapFactory);
                objInputStream.close();

            } catch (Exception e) {
                Log.d("test", "downloadImage ==> " + e.toString());
            }

            return objBitmap;
        }   // downloadImage

        private InputStream getHttpConnection(String strUrl) throws IOException {

            InputStream objInputStream = null;
            URL objURL = new URL(strUrl);
            URLConnection objURLConnection = objURL.openConnection();

            try {

                HttpURLConnection objHttpURLConnection = (HttpURLConnection) objURLConnection;
                objHttpURLConnection.setRequestMethod("GET");
                objHttpURLConnection.connect();

                if (objHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    objInputStream = objHttpURLConnection.getInputStream();
                }   // if

            } catch (Exception e) {
                Log.d("test", "getHttpConnection ==> " + e.toString());
            }

            return objInputStream;
        }   // getHttpConnection


    }   // GetXMLTask Clsaa


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
