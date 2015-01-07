package appewtc.masterung.osptraining;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoActivity extends ActionBarActivity {

    //Explicit
    private VideoView myVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //Initial Widget
        myVideoView = (VideoView) findViewById(R.id.videoView);

        //Create Video View
        String strVideoURL = getIntent().getExtras().getString("Video");
        MediaController objMediaController = new MediaController(this);
        objMediaController.setAnchorView(myVideoView);
        objMediaController.setMediaPlayer(myVideoView);

        //Change String to URL
        Uri objUri = Uri.parse(strVideoURL);
        myVideoView.setMediaController(objMediaController);
        myVideoView.setVideoURI(objUri);
        myVideoView.start();

    }   // onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemReadAll:
                Intent readIntent = new Intent(VideoActivity.this, OfficerListView.class);
                startActivity(readIntent);
                finish();
                break;
            case R.id.itemSearchFromVideo:
                Intent searchIntent = new Intent(VideoActivity.this, SearchOfficerActivity.class);
                startActivity(searchIntent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
