package appewtc.masterung.thaicreate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {


    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final AlertDialog.Builder ad = new AlertDialog.Builder(this);


        // txtSDCard
        final EditText txtSDCard = (EditText)findViewById(R.id.txtSDCard);

        // btnUpload
        Button btnUpload = (Button)findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated stub

                String strSDPath = txtSDCard.getText().toString();
               // String strUrlServer = "http://www.smicb.com/Uploadfile.php";
                String strUrlServer = "http://swiftcodingthai.com/osp/Upload.php";
                String resServer = uploadFiletoServer(strSDPath,strUrlServer);

                /** Get result from Server (Return the JSON Code)
                 * StatusID = ? [0=Failed,1=Complete]
                 * Error	= ?	[On case error return custom error message]
                 *
                 * Eg Upload Failed = {"StatusID":"0","Error":"Cannot Upload file!"}
                 * Eg Upload Complete = {"StatusID":"1","Error":""}
                 */

                /*** Default Value ***/
                String strStatusID = "0";
                String strError = "Unknow Status!";

                try {
                    JSONObject c = new JSONObject(resServer);
                    strStatusID = c.getString("StatusID");
                    strError = c.getString("Error");
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if(strStatusID.equals("0"))
                {
                    ad.setTitle("Error!");
                    ad.setIcon(android.R.drawable.btn_star_big_on);
                    ad.setMessage(strError);
                    ad.setPositiveButton("Close", null);
                    ad.show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Upload file Successfully", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public String uploadFiletoServer(String strSDPath, String strUrlServer)
    {

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        int resCode = 0;
        String resMessage = "";

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";

        try {
            /** Check file on SD Card ***/
            File file = new File(strSDPath);
            if(!file.exists())
            {
                return "{\"StatusID\":\"0\",\"Error\":\"Please check path on SD Card\"}";
            }

            FileInputStream fileInputStream = new FileInputStream(new File(strSDPath));

            URL url = new URL(strUrlServer);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);

            DataOutputStream outputStream = new DataOutputStream(conn
                    .getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream
                    .writeBytes("Content-Disposition: form-data; name=\"filUpload\";filename=\""
                            + strSDPath + "\"" + lineEnd);
            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // Read file
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Response Code and  Message
            resCode = conn.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK)
            {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                int read = 0;
                while ((read = is.read()) != -1) {
                    bos.write(read);
                }
                byte[] result = bos.toByteArray();
                bos.close();

                resMessage = new String(result);

            }

            Log.d("resCode=",Integer.toString(resCode));
            Log.d("resMessage=",resMessage.toString());

            fileInputStream.close();
            outputStream.flush();
            outputStream.close();

            return resMessage.toString();

        } catch (Exception ex) {
            // Exception handling
            return null;
        }
    }




}
