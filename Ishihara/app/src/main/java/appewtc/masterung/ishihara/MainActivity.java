package appewtc.masterung.ishihara;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView txtQuestion;
    private ImageView imvIshihara;
    private RadioGroup ragChoice;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnAnswer;
    private String strURL = "http://swiftcodingthai.com/test1/test.mp3";
    private MediaPlayer objMediaPlayer;
    private int intRadioButton, intIndex, intUserChoose[], intAnswerTrue[], intScore;
    private MyModel objMyModel;
    private boolean bolStatus = false;
    private String strChoice[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initial Widget
        initialWidget();

        //About MyModel
        aboutMyModel();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //setArray
        setArray();


    }   // onCreate

    private void setArray() {

        intUserChoose = new int[10];
        intAnswerTrue = new int[10];
        intAnswerTrue[0] = 1;
        intAnswerTrue[1] = 2;
        intAnswerTrue[2] = 3;
        intAnswerTrue[3] = 1;
        intAnswerTrue[4] = 2;
        intAnswerTrue[5] = 3;
        intAnswerTrue[6] = 1;
        intAnswerTrue[7] = 2;
        intAnswerTrue[8] = 4;
        intAnswerTrue[9] = 4;

    }   // setArray

    private void aboutMyModel() {

        objMyModel = new MyModel();

        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                //ShowLog
                Log.i("lsp", "You Choose ==> " + String.valueOf(myModel.getIntRadio()));

                //Change View
                switch (myModel.getIntButton()) {

                    case 2:
                        imvIshihara.setImageResource(R.drawable.ishihara_02);
                        strChoice = getResources().getStringArray(R.array.time2);
                        setupChoice();
                        break;
                    case 3:
                        imvIshihara.setImageResource(R.drawable.ishihara_03);
                        strChoice = getResources().getStringArray(R.array.time3);
                        setupChoice();
                        break;
                    case 4:
                        imvIshihara.setImageResource(R.drawable.ishihara_04);
                        strChoice = getResources().getStringArray(R.array.time4);
                        setupChoice();
                        break;
                    case 5:
                        imvIshihara.setImageResource(R.drawable.ishihara_05);
                        strChoice = getResources().getStringArray(R.array.time5);
                        setupChoice();
                        break;
                    case 6:
                        imvIshihara.setImageResource(R.drawable.ishihara_06);
                        strChoice = getResources().getStringArray(R.array.time6);
                        setupChoice();
                        break;
                    case 7:
                        imvIshihara.setImageResource(R.drawable.ishihara_07);
                        strChoice = getResources().getStringArray(R.array.time7);
                        setupChoice();
                        break;
                    case 8:
                        imvIshihara.setImageResource(R.drawable.ishihara_08);
                        strChoice = getResources().getStringArray(R.array.time8);
                        setupChoice();
                        break;
                    case 9:
                        imvIshihara.setImageResource(R.drawable.ishihara_09);
                        strChoice = getResources().getStringArray(R.array.time9);
                        setupChoice();
                        break;
                    case 10:
                        imvIshihara.setImageResource(R.drawable.ishihara_10);
                        strChoice = getResources().getStringArray(R.array.time10);
                        setupChoice();
                        break;

                }   // switch


            }   // event
        });


    }   // aboutMyModel

    private void setupChoice() {

        radChoice1.setText(strChoice[0]);
        radChoice2.setText(strChoice[1]);
        radChoice3.setText(strChoice[2]);
        radChoice4.setText(strChoice[3]);

    }   // setupChoice

    private void backgroundSound() throws IOException {

        //Inherited
        objMediaPlayer = new MediaPlayer();
        objMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        objMediaPlayer.setDataSource(strURL);
        objMediaPlayer.prepare();

        if (bolStatus) {
            objMediaPlayer.start();
        }

    }   // backgroundSound

    private void radioController() {

        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Sound Effect
                MediaPlayer soundRadio = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                soundRadio.start();

                //setup intRadioButton
                switch (checkedId) {

                    case R.id.radioButton:
                        intRadioButton = 1;
                        break;
                    case R.id.radioButton2:
                        intRadioButton = 2;
                        break;
                    case R.id.radioButton3:
                        intRadioButton = 3;
                        break;
                    case R.id.radioButton4:
                        intRadioButton = 4;
                        break;
                    default:
                        intRadioButton = 0;
                        break;

                }   // switch

                //Sent Value to Model
                objMyModel.setIntRadio(intRadioButton);

            }   //event
        });

    }   //radioController

    private void buttonController() {

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sound Effect
                MediaPlayer soundButton = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                soundButton.start();

                //Check Zero
                if (intRadioButton == 0) {

                    MyAlertDialog objMyAlert = new MyAlertDialog();
                    objMyAlert.haveSpace(MainActivity.this, "กรุณาตอบคำถาม", "กรุณาตอบ คำถาม ด้วยคะ");

                } else {

                    //Check Score
                    checkScore();

                    //Check Times
                    if (intIndex == 9) {

                        //Intent
                        Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
                        objIntent.putExtra("Score", intScore);
                        startActivity(objIntent);
                        finish();

                    } else {

                        //Show Controller Call View
                        txtQuestion.setText(String.valueOf(intIndex + 2) + ". What is this ?");

                        //Sent Value to Madel
                        objMyModel.setIntButton(intIndex + 2);

                        //Increase intIndex
                        intIndex += 1;

                        //Clear Check
                        ragChoice.clearCheck();

                    }   // Check Time

                }   // Check Zero


            }   // event
        });

    }   // buttonController

    private void checkScore() {

        intUserChoose[intIndex] = intRadioButton;

        if (intUserChoose[intIndex] == intAnswerTrue[intIndex]) {
            intScore += 1;
        }

    }   // checkScore

    private void initialWidget() {

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        imvIshihara = (ImageView) findViewById(R.id.imageView);
        ragChoice = (RadioGroup) findViewById(R.id.ragChoice);
        radChoice1 = (RadioButton) findViewById(R.id.radioButton);
        radChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        radChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        radChoice4 = (RadioButton) findViewById(R.id.radioButton4);
        btnAnswer = (Button) findViewById(R.id.button);

    }   // initialWidget


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.itmePlay:
                bolStatus = true;
                try {
                    backgroundSound();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.itmeStop:
                bolStatus = false;
                objMediaPlayer.stop();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}   //Main Class
