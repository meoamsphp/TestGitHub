package appewtc.masterung.simpleftp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;
import java.io.FileInputStream;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }   // onCreate

    public void clickButton(View view) {

        try {


            SimpleFTP ftp = new SimpleFTP();

            // Connect to an FTP server on port 21.
            ftp.connect("ftp.swiftcodingthai.com", 21, "osp@swiftcodingthai.com", "Abc12345");

            // Set binary mode.
            ftp.bin();

            // Change to a new working directory on the FTP server.
            ftp.cwd("/master/");

            // Upload some files.
            ftp.stor(new File("/storage/extSdCard/master.rtf"));
            //ftp.stor(new File("comicbot-latest.png"));

            // You can also upload from an InputStream, e.g.
            ftp.stor(new FileInputStream(new File("master.rtf")), "master.rtf");
          //  ftp.stor(someSocket.getInputStream(), "blah.dat");

            // Quit from the FTP server.
            ftp.disconnect();


        } catch (Exception e) {
            Log.d("ftp", "Error ==> " + e.toString());
        }

    }   // clickButton

}   // Main Class
