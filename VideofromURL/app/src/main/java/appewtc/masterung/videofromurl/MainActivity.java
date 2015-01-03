package appewtc.masterung.videofromurl;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends ActionBarActivity {

    private VideoView myVideoView;
    private String strVideoUrl = "http://swiftcodingthai.com/test1/video/MasterUng.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        myVideoView = (VideoView) findViewById(R.id.videoView);

        //About Play Video
        MediaController objMediaController = new MediaController(this);
        objMediaController.setAnchorView(myVideoView);
        objMediaController.setMediaPlayer(myVideoView);
        Uri objUri = Uri.parse(strVideoUrl);
        myVideoView.setMediaController(objMediaController);
        myVideoView.setVideoURI(objUri);
        myVideoView.start();
    }   // onCreate


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
