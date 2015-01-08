package appewtc.masterung.uploadftp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnButton;
    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "ftp.swiftcodingthai.com";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "osp@swiftcodingthai.com";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="Abc12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnButton = (Button) findViewById(R.id.button);
        btnButton.setOnClickListener(this);

    } // onCreate


    @Override
    public void onClick(View v) {

        /********** Pick file from sdcard *******/
        File f = new File("/sdcard/master.rtf");

        // Upload sdcard file
        uploadFile(f);

    }   // onClick

    public void uploadFile(File fileName){


        FTPClient client = new FTPClient();

        try {

            client.connect(FTP_HOST,21);
            client.login(FTP_USER, FTP_PASS);
            client.setType(FTPClient.TYPE_BINARY);
            client.changeDirectory("/master/");

            client.upload(fileName, new MyTransferListener());

        } catch (Exception e) {
            e.printStackTrace();
            try {
                client.disconnect(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public class MyTransferListener implements FTPDataTransferListener {

        public void started() {

            btnButton.setVisibility(View.GONE);
            // Transfer started
            Toast.makeText(getBaseContext(), " Upload Started ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" Upload Started ...");
        }

        public void transferred(int length) {

            // Yet other length bytes has been transferred since the last time this
            // method was called
            Toast.makeText(getBaseContext(), " transferred ..." + length, Toast.LENGTH_SHORT).show();
            //System.out.println(" transferred ..." + length);
        }

        public void completed() {

            btnButton.setVisibility(View.VISIBLE);
            // Transfer completed

            Toast.makeText(getBaseContext(), " completed ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" completed ..." );
        }

        public void aborted() {

            btnButton.setVisibility(View.VISIBLE);
            // Transfer aborted
            Toast.makeText(getBaseContext()," transfer aborted ,please try again...", Toast.LENGTH_SHORT).show();
            //System.out.println(" aborted ..." );
        }

        public void failed() {

            btnButton.setVisibility(View.VISIBLE);
            // Transfer failed
            System.out.println(" failed ..." );
        }

    }


}   // Main Class
